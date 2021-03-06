package br.furb.consultor.autenticacao;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.DatatypeConverter;

import org.joda.time.LocalDateTime;

import controle.exceptions.UsuarioException;
import br.furb.consultor.entities.TokenDTO;
import br.furb.consultor.entities.UsuarioDTO;
import br.furb.consultor.usuario.BOUsuario;


public final class BOAutenticacao {
	
	private BOAutenticacao(){
		
	}
	
	public static TokenDTO logon(String userName, String password){
		try{
			UsuarioDTO usuario = BOUsuario.getUsuario(userName, password);
			return createJWT(usuario.getId(), userName, 1800000l);
		}catch(UsuarioException e){
			ResponseBuilder builder = null;
			builder = Response.status(Response.Status.UNAUTHORIZED).entity(e.getError());
			throw new WebApplicationException(builder.build());
		}
		
	}

	public static TokenDTO createJWT(Integer id, String nome, long ttlMillis) {

		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		APIKey apiKey = new APIKey();

		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecretKey());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		//Let's set the JWT Claims
		String jwt = Jwts.builder().setId(UUID.randomUUID().toString()).compact();

		JwtBuilder builder = Jwts.builder().setId(jwt)
				.setIssuedAt(now)
				.claim("id", id)
				.claim("nome", nome)
				.signWith(signatureAlgorithm, signingKey);

		//if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setToken(builder.compact());
		tokenDTO.setIdUsuario(id.toString());
		tokenDTO.setNomeUsuario(nome);

		//Builds the JWT and serializes it to a compact, URL-safe string
		return tokenDTO;
	}
	
	//Sample method to validate and read the JWT
	public static Response parseJWT(String jwt) {
		APIKey apiKey = new APIKey();
		//This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims;        
		try{
			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecretKey()))
					.parseClaimsJws(jwt).getBody();
			LocalDateTime dataHoraExpiracao = new LocalDateTime(claims.getExpiration().getTime());
			if (dataHoraExpiracao.isBefore(new LocalDateTime())) {
				return Response.status(401).type(MediaType.APPLICATION_JSON).entity(new Exception("O token informado est� expirado")).build();
			}
			
		}catch(MalformedJwtException e){
			return Response.status(401).type(MediaType.APPLICATION_JSON).entity(new Exception("O token informado � inv�lido")).build();
		}
		
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setIdUsuario(((Integer)claims.get("id")).toString());
		tokenDTO.setNomeUsuario((String)claims.get("nome"));
		
		return Response.ok(tokenDTO).build();
	}

}

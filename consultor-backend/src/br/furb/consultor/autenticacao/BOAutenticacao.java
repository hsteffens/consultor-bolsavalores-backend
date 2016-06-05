package br.furb.consultor.autenticacao;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.security.Key;

import io.jsonwebtoken.*;

import java.util.Date;    


public final class BOAutenticacao {
	
	private BOAutenticacao(){
		
	}

	public static Response createJWT(String id, String issuer, String subject, long ttlMillis) {

		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		APIKey apiKey = new APIKey();

		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecretKey());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id)
				.setIssuedAt(now)
				.setSubject(subject)
				.setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		//if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		//Builds the JWT and serializes it to a compact, URL-safe string
		return Response.ok(builder.compact()).build();
	}
	
	//Sample method to validate and read the JWT
	public static Response parseJWT(String jwt) {
		APIKey apiKey = new APIKey();
		//This line will throw an exception if it is not a signed JWS (as expected)
		try{
			Claims claims = Jwts.parser()         
					.setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecretKey()))
					.parseClaimsJws(jwt).getBody();
			LocalDateTime dataHoraExpiracao = new LocalDateTime(claims.getExpiration().getTime());
			if (dataHoraExpiracao.isBefore(new LocalDateTime())) {
				return Response.status(401).type(MediaType.APPLICATION_JSON).entity(new Exception("O formato token informado está expirado")).build();
			}
			
			if (!"consultor-bolsa-valores".equals(claims.getId())) {
				return Response.status(401).type(MediaType.APPLICATION_JSON).entity(new Exception("A aplicação informada é inválida")).build();
			}
			
			if (!"helinton.pereira".equals(claims.getIssuer()) || !"123456".equals(claims.getSubject())){
				return Response.status(401).type(MediaType.APPLICATION_JSON).entity(new Exception("Usuário/Senha inválida")).build();
			}
			
			
		}catch(MalformedJwtException e){
			return Response.status(401).type(MediaType.APPLICATION_JSON).entity(new Exception("O token informado é inválido")).build();
		}
		
		return Response.ok().build();
	}

}

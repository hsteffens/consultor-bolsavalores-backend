package br.furb.consultor.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.furb.consultor.autenticacao.BOAutenticacao;

@Path("/auth")
public class ServicoAutenticacao {
	 
	@POST
	@Path("id/{id}/user/{username}/passoword/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logon(@PathParam("id") String id, @PathParam("username") String username, @PathParam("password") String password){
		return BOAutenticacao.createJWT(id, username, password, 1800000l);
	}
	
	@POST
	@Path("token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logon(@PathParam("token") String token){
		return BOAutenticacao.parseJWT(token);
	}

}

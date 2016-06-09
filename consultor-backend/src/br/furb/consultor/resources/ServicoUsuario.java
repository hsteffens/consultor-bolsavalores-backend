package br.furb.consultor.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import br.furb.consultor.entities.StatusRespostaDTO;
import br.furb.consultor.entities.UsuarioDTO;

@Path("/user")
public class ServicoUsuario {


	@Context
	private HttpHeaders httpRequest;
	
	@GET
	@Path("/usuario-logado")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioDTO getUsuarioLogada(){
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUserName(httpRequest.getRequestHeader("userName").get(0));
		return usuarioDTO;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StatusRespostaDTO alterarUsuario(UsuarioDTO usuario, @QueryParam("username") String userName){
		StatusRespostaDTO resposta = new StatusRespostaDTO();
		resposta.setStatus("OK");
		resposta.setMensagem("Usuário inserido com sucesso!");
		
		return resposta;
	}
	
}

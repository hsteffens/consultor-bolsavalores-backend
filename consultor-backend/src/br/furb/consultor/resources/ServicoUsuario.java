package br.furb.consultor.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import br.furb.consultor.entities.StatusRespostaDTO;
import br.furb.consultor.entities.UsuarioDTO;
import br.furb.consultor.usuario.BOUsuario;

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
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StatusRespostaDTO inserirUsuario(UsuarioDTO usuarioDTO){
		return BOUsuario.inserirUsuario(usuarioDTO);
	}

	@PUT
	@Path("/alter")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StatusRespostaDTO alterarUsuario(UsuarioDTO usuario){
		if (usuario != null && !usuario.getUserName().isEmpty() && !usuario.getEmail().isEmpty() && !usuario.getNome().isEmpty()) {
			StatusRespostaDTO resposta = new StatusRespostaDTO();
			resposta.setStatus("OK");
			resposta.setMensagem("Usuário inserido com sucesso!");
			
			return resposta;
		}
		StatusRespostaDTO resposta = new StatusRespostaDTO();
		resposta.setStatus("Erro");
		resposta.setMensagem("Usuário não inserido a campos obrigatórios não preenchidos!");
		
		return resposta;
	}
	
}

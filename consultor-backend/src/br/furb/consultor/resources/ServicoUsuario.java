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
		String nomeUsuario = httpRequest.getRequestHeader("userName").get(0);
		int id = Integer.parseInt(httpRequest.getRequestHeader("idUsuarioLogado").get(0));
		UsuarioDTO usuarioLogado = BOUsuario.getUsuarioLogado(id);
		usuarioLogado.setUserName(nomeUsuario);
		return usuarioLogado;
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
		String idUsuario = httpRequest.getRequestHeader("idUsuarioLogado").get(0);
		return BOUsuario.alterarUsuario(Integer.parseInt(idUsuario),usuario);
	}
	
}

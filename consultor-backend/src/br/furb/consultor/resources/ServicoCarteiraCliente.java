package br.furb.consultor.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import br.furb.consultor.carteira.FacadeCarteiraCliente;
import br.furb.consultor.entities.CarteiraClienteDTO;
import br.furb.consultor.entities.StatusRespostaDTO;

@Path("/carteira-cliente")
public class ServicoCarteiraCliente {
	
	@Context
	private HttpHeaders httpRequest;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CarteiraClienteDTO getCarteiraCliente(){
		int id = Integer.parseInt(httpRequest.getRequestHeader("idUsuarioLogado").get(0));
		return FacadeCarteiraCliente.getCarteiraCliente(id);
	}


	@POST
	@Path("/acao/{acao}/quantidade/{quantidade}/preco/{preco}")
	@Produces(MediaType.APPLICATION_JSON)
	public StatusRespostaDTO incluirAcao(@PathParam("acao") String acao, @PathParam("quantidade") Integer quantidade, @PathParam("preco") Float preco){
		int id = Integer.parseInt(httpRequest.getRequestHeader("idUsuarioLogado").get(0));
		return FacadeCarteiraCliente.inserirAcaoCarteiraCliente(id, acao, quantidade, preco);
	}
}
 
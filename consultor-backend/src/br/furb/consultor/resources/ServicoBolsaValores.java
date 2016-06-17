package br.furb.consultor.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import br.furb.consultor.carteira.FacadeCarteiraCliente;
import br.furb.consultor.carteiracliente.BOCarteiraCliente;
import br.furb.consultor.compra.FacadeCompraAcao;
import br.furb.consultor.entities.AcoesDTO;
import br.furb.consultor.entities.StatusRespostaDTO;
import br.furb.consultor.venda.FacadeVendaAcao;

@Path("/bolsavalores")
public class ServicoBolsaValores {

	@Context
	private HttpHeaders httpRequest;
	
	
	@GET
	@Path("/compra/melhor-opcao")
	@Produces(MediaType.APPLICATION_JSON)
	public AcoesDTO getMelhorOpcaoCompra(){
		return FacadeCompraAcao.getMelhoresOpcoesCompras();
	}
	
	@GET
	@Path("/compra/melhor-opcao/bolsa/{bolsa}")
	@Produces(MediaType.APPLICATION_JSON)
	public AcoesDTO getMelhorOpcaoCompraBolsa(@PathParam("bolsa") Integer bolsa){
		return FacadeCompraAcao.getMelhoresOpcoesComprasPorBolsa(bolsa);
	}

	@GET
	@Path("/compra/melhor-opcao/usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public AcoesDTO getMelhorOpcaoCompraUsuario(){
		int usuario = Integer.parseInt(httpRequest.getRequestHeader("idUsuarioLogado").get(0));
		return FacadeCompraAcao.getMelhoresOpcoesUsuario(usuario);
	}

	@GET
	@Path("/venda/melhor-opcao")
	@Produces(MediaType.APPLICATION_JSON)
	public AcoesDTO getMelhorOpcaoVenda(){
		return FacadeVendaAcao.getMelhoresOpcoesVendas();
	}
	
	@GET
	@Path("/venda/melhor-opcao/bolsa/{bolsa}")
	@Produces(MediaType.APPLICATION_JSON)
	public AcoesDTO getMelhorOpcaoVendaBolsa(@PathParam("bolsa") Long bolsa){
		return FacadeVendaAcao.getMelhoresOpcoesVendasBolsa(bolsa);
	}
	
	@GET
	@Path("/venda/melhor-opcao/usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public AcoesDTO getMelhorOpcaoVendaUsuario(){
		int usuario = Integer.parseInt(httpRequest.getRequestHeader("idUsuarioLogado").get(0));
		return FacadeVendaAcao.getMelhoresOpcoesVendasUsuario(usuario);
	}
	
	@DELETE
	@Path("/venda/melhor-opcao/usuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StatusRespostaDTO venderAcoes(AcoesDTO acoesVender){
		int usuario = Integer.parseInt(httpRequest.getRequestHeader("idUsuarioLogado").get(0));
		return FacadeCarteiraCliente.removerAcaoCarteiraCliente(usuario, acoesVender);
	}
	
}

package br.furb.consultor.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.furb.consultor.compra.FacadeCompraAcao;
import br.furb.consultor.entities.AcoesDTO;
import br.furb.consultor.venda.FacadeVendaAcao;

@Path("/bolsavalores")
public class ServicoBolsaValores {

	
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
	@Path("/compra/melhor-opcao/usuario/{usuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public AcoesDTO getMelhorOpcaoCompraUsuario(@PathParam("usuario") Integer usuario){
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
	@Path("/venda/melhor-opcao/usuario/{usuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public AcoesDTO getMelhorOpcaoVendaUsuario(@PathParam("usuario") Long usuario){
		return FacadeVendaAcao.getMelhoresOpcoesVendasUsuario(usuario);
	}
	
}

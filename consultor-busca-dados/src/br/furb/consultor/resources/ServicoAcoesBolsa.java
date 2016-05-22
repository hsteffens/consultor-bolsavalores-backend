package br.furb.consultor.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.furb.consultor.buscadados.FacadeAcaoBolsa;
import br.furb.consultor.entities.AcaoBolsaDTO;
import br.furb.consultor.entities.PapelDTO;

@Path("/acaobolsa")
public class ServicoAcoesBolsa {

	@GET
	@Path("{acao}")
	@Produces(MediaType.APPLICATION_JSON)
	public AcaoBolsaDTO getAcaoBolsaValor(@PathParam("acao") String codigoAcao, @QueryParam("time") Long time){
		return FacadeAcaoBolsa.getAcaoBolsaValores(codigoAcao, time == null ? 0 : time);
	}
	
	@GET
	@Path("/melhoropcao/venda")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PapelDTO> getMelhorOpcoesVenda(){
		return FacadeAcaoBolsa.getMelhoresOpcoesVenda();
	}

	@GET
	@Path("/melhoropcao/compra")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PapelDTO> getMelhorOpcoesCompra(){
		return FacadeAcaoBolsa.getMelhoresOpcoesCompra();
	}
}

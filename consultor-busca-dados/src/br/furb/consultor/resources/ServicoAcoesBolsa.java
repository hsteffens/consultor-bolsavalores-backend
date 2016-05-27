package br.furb.consultor.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.joda.time.LocalDateTime;

import br.furb.consultor.buscadados.FacadeAcaoBolsa;
import br.furb.consultor.entities.AcaoBolsaDTO;
import br.furb.consultor.entities.PapelDTO;

@Path("/acaobolsa")
public class ServicoAcoesBolsa {

	@GET
	@Path("{acao}")
	@Produces(MediaType.APPLICATION_JSON)
	public AcaoBolsaDTO getAcaoBolsaValor(@PathParam("acao") String codigoAcao,@QueryParam("inicio") Long inicio, @QueryParam("expira") Long expira){
		return FacadeAcaoBolsa.getAcaoBolsaValores(codigoAcao, new LocalDateTime(inicio), expira == null ? 0 : expira);
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

package br.furb.consultor.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.furb.consultor.cotacao.FacadeCotacao;
import br.furb.consultor.entities.AcoesCotacaoDTO;

@Path("/cotacao")
public class ServicoCotacao {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public AcoesCotacaoDTO getCotacoes(){
		return FacadeCotacao.getCotacoes();
	}
}

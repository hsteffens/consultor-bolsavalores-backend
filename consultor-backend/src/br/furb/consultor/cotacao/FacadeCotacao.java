package br.furb.consultor.cotacao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import persistencia.Cotacao;
import br.furb.consultor.entities.AcoesCotacaoDTO;
import br.furb.consultor.entities.CotacaoDTO;

public final class FacadeCotacao {
	
	private FacadeCotacao(){
		
	}
	
	public static AcoesCotacaoDTO getCotacoes(){
		List<Cotacao> cotacoes = BOCotacao.getCotacoes();
		
		AcoesCotacaoDTO acoesCotacaoDTO = new AcoesCotacaoDTO();
		ArrayList<CotacaoDTO> cotacoesDTO = new ArrayList<CotacaoDTO>();
		CotacaoDTO cotacaoDTO = null;
		for (Cotacao cotacao : cotacoes) {
			cotacaoDTO = new CotacaoDTO();
			
			cotacaoDTO.setCodigo(cotacao.getDsCodigo());
			cotacaoDTO.setData(new LocalDate(cotacao.getDtDia()));
			cotacaoDTO.setHora(formatTime(cotacao.getCdHora().doubleValue()));
			cotacaoDTO.setValorAbertura(cotacao.getVlAbertura().doubleValue());
			cotacaoDTO.setValorAtual(cotacao.getVlPreco().doubleValue());
			cotacaoDTO.setVariacaoPercentual(cotacao.getVlPercentual().doubleValue());
			
			cotacoesDTO.add(cotacaoDTO);
		}
		
		acoesCotacaoDTO.setResult(cotacoesDTO);
		
		return acoesCotacaoDTO;
	}
	
	private static String formatTime(Double minutes){
		Double time = minutes / 60;
		String[] timeStr = time.toString().split("\\.");
		String hora = timeStr[0];
		Integer min = (Integer.parseInt(timeStr[1]) * 60) / 100;
		String minutos = min.toString();
		
		return hora + ":" + minutos;
	}
	
}

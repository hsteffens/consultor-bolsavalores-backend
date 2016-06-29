package br.furb.consultor.cotacao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import persistencia.Cotacao;
import persistencia.HistoricoCotacao;
import br.furb.consultor.entities.AcoesCotacaoDTO;
import br.furb.consultor.entities.CotacaoDTO;
import br.furb.consultor.entities.HistoricoCotacaoDTO;
import br.furb.consultor.entities.HistoricosCotacaoDTO;
import br.furb.consultor.historico.BOHistorico;

public final class FacadeCotacao {
	
	private FacadeCotacao(){
		
	}
	
	public static AcoesCotacaoDTO getCotacoes(){
		List<Cotacao> cotacoes = BOCotacao.getCotacoes();
		
		AcoesCotacaoDTO acoesCotacaoDTO = new AcoesCotacaoDTO();
		List<CotacaoDTO> cotacoesDTO = new ArrayList<CotacaoDTO>();
		CotacaoDTO cotacaoDTO = null;
		for (Cotacao cotacao : cotacoes) {
			cotacaoDTO = new CotacaoDTO();
			
			cotacaoDTO.setCodigo(cotacao.getDsCodigo());
			cotacaoDTO.setData(new LocalDate(cotacao.getDtDia()));
			cotacaoDTO.setHora(formatTime(cotacao.getCdHora().doubleValue()));
			cotacaoDTO.setValorAbertura(cotacao.getVlAbertura().doubleValue());
			cotacaoDTO.setValorAtual(cotacao.getVlPreco().doubleValue());
			cotacaoDTO.setVariacaoPercentual(cotacao.getVlPercentual().doubleValue());
			cotacaoDTO.setBaixa(cotacao.getVlBaixa().doubleValue());
			cotacaoDTO.setAlta(cotacao.getVlAlta().doubleValue());
			
			cotacoesDTO.add(cotacaoDTO);
		}
		
		acoesCotacaoDTO.setResult(cotacoesDTO);
		
		return acoesCotacaoDTO;
	}
	
	public static HistoricosCotacaoDTO getHistoricosCotacao(){
		List<HistoricoCotacao> historicoCotacoes = BOHistorico.getHistoricoCotacoes();
		
		HistoricosCotacaoDTO historicosCotacaoDTO = new HistoricosCotacaoDTO();
		List<HistoricoCotacaoDTO> historicos = new ArrayList<HistoricoCotacaoDTO>();
		HistoricoCotacaoDTO historicoCotacaoDTO = null;
		for (HistoricoCotacao historico : historicoCotacoes) {
			historicoCotacaoDTO = new HistoricoCotacaoDTO();
			
			historicoCotacaoDTO.setCodigo(historico.getDsCodigo());
			historicoCotacaoDTO.setDia(new LocalDate(historico.getDtDia()));
			historicoCotacaoDTO.setHora(formatTime(historico.getCdHora().doubleValue()));
			historicoCotacaoDTO.setPreco(historico.getVlPreco().doubleValue());
			
			historicos.add(historicoCotacaoDTO);
		}
		
		historicosCotacaoDTO.setResult(historicos);
		
		return historicosCotacaoDTO;
	}
	
	private static String formatTime(Double minutes){
		String startTime = "00:00";
		int h = minutes.intValue() / 60 + Integer.parseInt(startTime.substring(0,1));
		int m = minutes.intValue() % 60 + Integer.parseInt(startTime.substring(3,4));
		
		return h+":"+m;
	}
	
}

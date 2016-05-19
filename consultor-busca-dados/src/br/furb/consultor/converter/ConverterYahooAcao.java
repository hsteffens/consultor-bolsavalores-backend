package br.furb.consultor.converter;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;

import br.furb.consultor.entities.AcaoBolsaDTO;
import br.furb.consultor.yahoo.AcaoBolsaYahoo;

public final class ConverterYahooAcao {

	private ConverterYahooAcao(){
		
	}
	
	public static List<AcaoBolsaDTO> converterYahooToAcaoBolsaDTO(List<AcaoBolsaYahoo> acoesYahoo){
		List<AcaoBolsaDTO> acoesDTO = new ArrayList<AcaoBolsaDTO>();
		for (AcaoBolsaYahoo acaoYahoo : acoesYahoo) {
			acoesDTO.add(converterYahooToAcaoBolsaDTO(acaoYahoo));
		}
		
		return acoesDTO;
	}
	
	public static AcaoBolsaDTO converterYahooToAcaoBolsaDTO(AcaoBolsaYahoo acaosYahoo){
		AcaoBolsaDTO acaoBolsaDTO = new AcaoBolsaDTO();
		acaoBolsaDTO.setCodigo(acaosYahoo.getSymbol());
		acaoBolsaDTO.setNomeAcao(acaosYahoo.getName());
		acaoBolsaDTO.setValorAtual(acaosYahoo.getAsk());
		acaoBolsaDTO.setUltimoValorTransacao(acaosYahoo.getLastTradePriceOnly());
		acaoBolsaDTO.setDataUltimaNegociacao(acaosYahoo.getLastTradeDate());
		acaoBolsaDTO.setHoraUltimaNegociacao(acaosYahoo.getLastTradeTime());
		acaoBolsaDTO.setMaiorValorDia(acaosYahoo.getDaysHigh());
		acaoBolsaDTO.setMenorValorDia(acaosYahoo.getDaysLow());
		acaoBolsaDTO.setMaiorValorAno(acaosYahoo.getYearHigh());
		acaoBolsaDTO.setMenorValorAno(acaosYahoo.getYearLow());
		acaoBolsaDTO.setCapitalMercado(acaosYahoo.getMarketCapitalization());
		acaoBolsaDTO.setValorAbertura(acaosYahoo.getOpen());
		acaoBolsaDTO.setVariacaoPercentual(acaosYahoo.getPercentChange());
		acaoBolsaDTO.setVolumeMedioDiario(acaosYahoo.getAverageDailyVolume());
		acaoBolsaDTO.setEBITDA(acaosYahoo.getEBITDA());
		acaoBolsaDTO.setExchange(acaosYahoo.getStockExchange());
		return acaoBolsaDTO;
	}
	
}

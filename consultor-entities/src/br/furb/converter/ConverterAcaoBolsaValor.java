package br.furb.converter;

import br.furb.acao.AcaoBolsaDTO;
import entities.AcaoBolsaValores;

public final class ConverterAcaoBolsaValor {

	private ConverterAcaoBolsaValor(){
		
	}
	
	public static AcaoBolsaValores converterBolsaValores(AcaoBolsaDTO acaoBolsaDTO){
		AcaoBolsaValores acao = new AcaoBolsaValores();
		acao.setCodigo(acaoBolsaDTO.getCodigo());
		acao.setNomeAcao(acaoBolsaDTO.getNomeAcao());
		acao.setCapitalMercado(acaoBolsaDTO.getCapitalMercado());
		acao.setDataUltimaNegociacao(acaoBolsaDTO.getDataUltimaNegociacao());
		acao.setEBITDA(acaoBolsaDTO.getEBITDA());
		acao.setExchange(acaoBolsaDTO.getExchange());
		acao.setHoraUltimaNegociacao(formatTime(acaoBolsaDTO.getHoraUltimaNegociacao()));
		acao.setMaiorValorAno(acaoBolsaDTO.getMaiorValorAno());
		acao.setMaiorValorDia(acaoBolsaDTO.getMaiorValorDia());
		acao.setMenorValorAno(acaoBolsaDTO.getMenorValorAno());
		acao.setMenorValorDia(acaoBolsaDTO.getMenorValorDia());
		acao.setPercentual(Float.parseFloat(acaoBolsaDTO.getVariacaoPercentual().replace("%", "")));
		acao.setVariacaoValor(acaoBolsaDTO.getVariacaoValor());
		acao.setVolumeMedioDiario(acaoBolsaDTO.getVolumeMedioDiario());
		return acao;
	}
	
	private static int formatTime(String horaString){
		String[] particionamento = horaString.split(":");
		int hora = 0;
		if (particionamento[1].contains("pm")) {
			 hora = 12 + Integer.parseInt(particionamento[0]);
		}else{
			hora = Integer.parseInt(particionamento[0]);
		}
			int minutos = Integer.parseInt(particionamento[1].replace("am", "").replace("pm", ""));
		return (hora * 60) + minutos;
	}
	
}

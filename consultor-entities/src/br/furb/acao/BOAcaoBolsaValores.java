package br.furb.acao;

import java.util.ArrayList;
import java.util.List;

import entities.AcaoBolsaValores;
import entities.EnTipoMoeda;

public class BOAcaoBolsaValores {

private static List<AcaoBolsaValores> lAcoes;
	
	static{
		lAcoes = new ArrayList<AcaoBolsaValores>();
		
		AcaoBolsaValores petrobras = new AcaoBolsaValores();
		petrobras.setCodigo("PETR4");
		petrobras.setNomeAcao("Petrobras");
		petrobras.setMoeda(EnTipoMoeda.REAL);
		petrobras.setValorAbertura(9.48);
		petrobras.setPercentual(-0.60);
		
		AcaoBolsaValores google = new AcaoBolsaValores();
		google.setCodigo("GOOG");
		google.setNomeAcao("Alphabet Inc Class C");
		google.setMoeda(EnTipoMoeda.DOLAR);
		google.setValorAbertura(712.00);
		google.setPercentual(1.78);
		
		lAcoes.add(petrobras);
		lAcoes.add(google);
		
		
	}
	
	public static AcaoBolsaValores getAcaoBolsaValores(String codigoAcao){
		for (AcaoBolsaValores acaoBolsaValores : lAcoes) {
			if (acaoBolsaValores.getCodigo().equals(codigoAcao)) {
				return acaoBolsaValores;
			}
		}

		return new AcaoBolsaValores();
	}
}

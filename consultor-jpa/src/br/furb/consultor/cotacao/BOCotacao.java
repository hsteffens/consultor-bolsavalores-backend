package br.furb.consultor.cotacao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.LocalDate;

import br.furb.consultor.historico.BOHistorico;
import controle.AcaoJpaController;
import controle.BolsaValoresJpaController;
import controle.CotacaoJpaController;
import controle.exceptions.CotacaoException;
import persistencia.Acao;
import persistencia.Cotacao;
import provider.EntityManager;

public final class BOCotacao {

	private BOCotacao(){
		
	}
	
	public static void createCotacao(String codigoAcao, LocalDate data, Integer hora, Double valorAbertura, Double valor, Double percentual){
		EntityManagerFactory factory = EntityManager.getFactory();
		
		Cotacao cotacao = new Cotacao();
		cotacao.setDsCodigo(codigoAcao);
		cotacao.setDtDia(data.toDate());
		cotacao.setCdHora(hora);
		cotacao.setVlAbertura(new BigDecimal(valorAbertura));
		cotacao.setVlPreco(new BigDecimal(valor));
		cotacao.setVlPercentual(new BigDecimal(percentual));
		
		AcaoJpaController acaoJpaController = new AcaoJpaController(factory);
		Acao acaoBolsa = acaoJpaController.findAcao(codigoAcao);
		if (acaoBolsa == null) {
			acaoBolsa = new Acao();
			acaoBolsa.setDsCodigo(codigoAcao);
			
			BolsaValoresJpaController bolsaValoresJpaController = new BolsaValoresJpaController(factory);
			acaoBolsa.setCdBolsavalores(bolsaValoresJpaController.findBolsaValores(1));
			
			acaoBolsa = acaoJpaController.create(acaoBolsa);
		}
		
		CotacaoJpaController cotacaoJpaController = new CotacaoJpaController(factory);
		try{
			cotacaoJpaController.destroy(codigoAcao);
			cotacaoJpaController.create(cotacao);
			
			BOHistorico.registryHistoryAcao(codigoAcao, valor, data, hora);
			
		}catch (PersistenceException ex) {
			if (ex.getCause() instanceof ConstraintViolationException) {
        		throw new CotacaoException("Acão já registrada neste instante!");
			}
        	throw new CotacaoException(ex.getMessage());
        }  catch (Exception ex) {
			throw new CotacaoException(ex.getMessage());
		}
		
	}
	
	public static List<Cotacao> getCotacoes(){
		EntityManagerFactory factory = EntityManager.getFactory();
		
		CotacaoJpaController cotacaoJpaController = new CotacaoJpaController(factory);
		return cotacaoJpaController.findAllCotacao();
	}
	
}

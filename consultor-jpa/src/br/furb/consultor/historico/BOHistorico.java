package br.furb.consultor.historico;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.LocalDate;

import persistencia.Cotacao;
import persistencia.HistoricoCotacao;
import provider.EntityManager;
import controle.CotacaoJpaController;
import controle.HistoricoCotacaoJpaController;
import controle.exceptions.CotacaoException;

public final class BOHistorico {

	private BOHistorico(){
		
	}
	
	public static void registryHistoryAcao(String codigoAcao, Double valorAcao, LocalDate dia, Integer hora){
		EntityManagerFactory factory = EntityManager.getFactory();
		
		HistoricoCotacao historicoCotacao = new HistoricoCotacao();
		historicoCotacao.setDsCodigo(codigoAcao);
		historicoCotacao.setVlPreco(BigDecimal.valueOf(valorAcao));
		historicoCotacao.setDtDia(dia.toDate());
		historicoCotacao.setCdHora(hora);
		
		HistoricoCotacaoJpaController historicoCotacaoJpaController = new HistoricoCotacaoJpaController(factory);
		try{
			historicoCotacaoJpaController.create(historicoCotacao);
		}catch (PersistenceException ex) {
			if (ex.getCause() instanceof ConstraintViolationException) {
        		throw new CotacaoException("Acão já registrada neste instante!");
			}
        	throw new CotacaoException(ex.getMessage());
        }  catch (Exception ex) {
			throw new CotacaoException(ex.getMessage());
		}
	}
	
	public static List<HistoricoCotacao> getHistoricoCotacoes(){
		EntityManagerFactory factory = EntityManager.getFactory();
		
		HistoricoCotacaoJpaController historicoCotacaoJpaController = new HistoricoCotacaoJpaController(factory);
		return historicoCotacaoJpaController.findHistoricoCotacaoEntities();
	}
	
}

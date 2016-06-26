package br.furb.consultor.historico;

import java.math.BigDecimal;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.LocalDate;

import persistencia.HistoricoCotacao;
import provider.EntityManager;
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
        		throw new CotacaoException("Ac�o j� registrada neste instante!");
			}
        	throw new CotacaoException(ex.getMessage());
        }  catch (Exception ex) {
			throw new CotacaoException(ex.getMessage());
		}
	}
	
}

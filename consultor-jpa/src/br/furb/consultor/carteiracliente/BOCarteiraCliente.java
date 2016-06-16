package br.furb.consultor.carteiracliente;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import persistencia.Acao;
import persistencia.CarteiraCliente;
import provider.EntityManager;
import controle.AcaoJpaController;
import controle.BolsaValoresJpaController;
import controle.CarteiraClienteJpaController;
import controle.UsuarioJpaController;
import controle.exceptions.UsuarioException;

public final class BOCarteiraCliente {
 
	private BOCarteiraCliente(){
		
	}
	
	public static void inserirAcaoCarteiraCliente(Integer idUsuario, String acao, Integer quantidade, Float valor){
		EntityManagerFactory factory = EntityManager.getFactory();
		
		CarteiraCliente carteiraCliente = new CarteiraCliente();
		carteiraCliente.setVlPreco(BigDecimal.valueOf(valor));
		carteiraCliente.setVlQuantidade(quantidade);
		
		if (idUsuario != null) {
			UsuarioJpaController usuarioJpaController = new UsuarioJpaController(factory);
			carteiraCliente.setCdUsuario(usuarioJpaController.findUsuario(idUsuario));
		}
		
		if (acao != null && !acao.isEmpty()) {
			AcaoJpaController acaoJpaController = new AcaoJpaController(factory);
			Acao acaoBolsa = acaoJpaController.findAcao(acao);
			if (acaoBolsa == null) {
				acaoBolsa = new Acao();
				acaoBolsa.setDsCodigo(acao);
				
				BolsaValoresJpaController bolsaValoresJpaController = new BolsaValoresJpaController(factory);
				acaoBolsa.setCdBolsavalores(bolsaValoresJpaController.findBolsaValores(1));
				
				acaoBolsa = acaoJpaController.create(acaoBolsa);
			}
			carteiraCliente.setDsCodigo(acaoBolsa);
		}
		
		CarteiraClienteJpaController carteiraClienteJpaController = new CarteiraClienteJpaController(factory);
		
		try{
			carteiraClienteJpaController.create(carteiraCliente);
		}catch (PersistenceException ex) {
        	if (ex.getCause() instanceof ConstraintViolationException) {
        		throw new UsuarioException("Já existe está ação cadastrada para este usuário!");
			}
        	throw new UsuarioException(ex.getMessage());
        }  catch (Exception ex) {
			throw new UsuarioException(ex.getMessage());
		}
        
	}

	public static List<CarteiraCliente> getCarteiraCliente(int id) {
		EntityManagerFactory factory = EntityManager.getFactory();
		CarteiraClienteJpaController carteiraClienteJpaController = new CarteiraClienteJpaController(factory);
		return carteiraClienteJpaController.findCarteiraClienteEntities();
	}
}

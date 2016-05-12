package br.furb.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

import entities.AcaoBolsa;
import entities.Usuario;

/**
 * Classe responsável por implementar as ações disponiveis do sistema.
 *  
 * @author helinton.steffens
 * 
 */
public class ConsultorVendas extends UnicastRemoteObject implements IConsultorVendas{

	public ConsultorVendas()  throws RemoteException{
		super();
	}

	public List<String> getMelhoresOpcoesVendas() {
		List<String> acoes = new ArrayList<String>();
		acoes.add("PETR4");
		acoes.add("GOOGL");
		return acoes;
	}

	public List<String> getMelhoresOpcoesVendasPorBolsa(long codigoBolsa) {
		List<String> acoes = new ArrayList<String>();
		try {
			acoes.add(AcaoBolsa.getBolsaValores("PETR4").getNomeAcao());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acoes;
	}

	public List<String> getMelhoresOpcoesVendasPorCliente(long codigoUsuario) {
		List<String> acoes = new ArrayList<String>();
		try {
			Usuario usuario = AcaoBolsa.getUsuario(codigoUsuario);
			if (usuario != null) {
				if (usuario.getTipoInvestidor() == 1) {
					LocalDateTime dataHora = LocalDateTime.now().minusDays(1);
					acoes.add(AcaoBolsa.getAcaoBolsaDentroValidade("GOOG", dataHora).getNomeAcao());
				}else if (usuario.getTipoInvestidor() == 2) {
					LocalDateTime dataHora = LocalDateTime.now().minusHours(1);
					acoes.add(AcaoBolsa.getAcaoBolsaDentroValidade("GOOG", dataHora).getNomeAcao());
				} else{
					LocalDateTime dataHora = LocalDateTime.now();
					acoes.add(AcaoBolsa.getAcaoBolsaDentroValidade("GOOG", dataHora).getNomeAcao());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acoes;
	}

}

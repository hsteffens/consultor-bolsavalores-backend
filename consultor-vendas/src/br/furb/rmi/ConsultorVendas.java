package br.furb.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
		acoes.add("PETR4");
		return acoes;
	}

	public List<String> getMelhoresOpcoesVendasPorCliente(long codigoUsuario) {
		List<String> acoes = new ArrayList<String>();
		acoes.add("GOOGL");
		return acoes;
	}

}

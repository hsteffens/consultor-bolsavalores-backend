package br.furb.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IConsultorVendas extends Remote{
	
	List<String> getMelhoresOpcoesVendas() throws RemoteException;
	
	List<String> getMelhoresOpcoesVendasPorBolsa(long codigoBolsa) throws RemoteException;
	
	List<String> getMelhoresOpcoesVendasPorCliente(long codigoUsuario) throws RemoteException;
	
}

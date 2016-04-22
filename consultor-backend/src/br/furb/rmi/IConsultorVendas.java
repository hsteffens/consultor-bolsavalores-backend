package br.furb.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface responsável por disponibilizar 
 * 
 * @author helinton.steffens
 */
public interface IConsultorVendas extends Remote{
	
	/**
	 * Responsável por retornar uma lista contendo o codigo das ações sugeridas para se vender.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	List<String> getMelhoresOpcoesVendas() throws RemoteException;
	
	/**
	 * Responsável por retornar uma lista contendo o codigo das ações sugeridas para se vender, 
	 * está sugestão é por bolsa de valores.<p>
	 * Ex: BM&FBOVESPA aqui representada pelo o codigo 1 e NYSE NASDAQ representada pelo o codigo 2
	 * 
	 * @param codigoBolsa
	 * @return
	 * @throws RemoteException
	 */
	List<String> getMelhoresOpcoesVendasPorBolsa(long codigoBolsa) throws RemoteException;
	
	/**
	 * Responsável por retornar uma lista contendo o codigo das ações sugeridas para se vender, 
	 * está sugestão é baseada na carteira virtual de cada usuário.
	 * 
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	List<String> getMelhoresOpcoesVendasPorCliente(long codigoUsuario) throws RemoteException;
	
}

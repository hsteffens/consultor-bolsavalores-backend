package br.furb.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface respons�vel por disponibilizar 
 * 
 * @author helinton.steffens
 */
public interface IConsultorVendas extends Remote{
	
	/**
	 * Respons�vel por retornar uma lista contendo o codigo das a��es sugeridas para se vender.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	List<String> getMelhoresOpcoesVendas() throws RemoteException;
	
	/**
	 * Respons�vel por retornar uma lista contendo o codigo das a��es sugeridas para se vender, 
	 * est� sugest�o � por bolsa de valores.<p>
	 * Ex: BM&FBOVESPA aqui representada pelo o codigo 1 e NYSE NASDAQ representada pelo o codigo 2
	 * 
	 * @param codigoBolsa
	 * @return
	 * @throws RemoteException
	 */
	List<String> getMelhoresOpcoesVendasPorBolsa(long codigoBolsa) throws RemoteException;
	
	/**
	 * Respons�vel por retornar uma lista contendo o codigo das a��es sugeridas para se vender, 
	 * est� sugest�o � baseada na carteira virtual de cada usu�rio.
	 * 
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	List<String> getMelhoresOpcoesVendasPorCliente(long codigoUsuario) throws RemoteException;
	
}

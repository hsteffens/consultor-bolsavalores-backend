package br.furb.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe de servidor RMI da aplicação de vendas.
 * 
 * @author helinton.steffens
 *
 */
 class Server {

	public static void main(String[] args) {
		try {
			ConsultorVendas consultorVendas = new ConsultorVendas();
			LocateRegistry.createRegistry(1099);
	        Registry registry = LocateRegistry.getRegistry(1099);
	        registry.rebind("//localhost/ConsultorVendas", consultorVendas);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("Server running ...");
	}
}

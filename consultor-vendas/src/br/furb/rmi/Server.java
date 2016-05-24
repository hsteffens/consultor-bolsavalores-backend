package br.furb.rmi;

import java.io.IOException;
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
//			String absolutePath = System.getProperty("user.dir");
//			Runtime.getRuntime().exec("cmd /c start "+ absolutePath + "\\src\\br\\furb\\rmi\\start.bat");
			
			ConsultorVendas consultorVendas = new ConsultorVendas();
			LocateRegistry.createRegistry(1099);
	        Registry registry = LocateRegistry.getRegistry(1099);
	        registry.rebind("//localhost/ConsultorVendas", consultorVendas);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		System.out.println("Server running ...");
	}
}

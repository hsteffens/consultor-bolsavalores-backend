package br.furb.rmi.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import br.furb.rmi.ConsultorVendas;

/**
 * Classe de servidor RMI da aplicação de vendas.
 * 
 * @author helinton.steffens
 *
 */
 class Server {

	public static void main(String[] args) {
		try {
			String absolutePath = System.getProperty("user.dir");
			Runtime.getRuntime().exec("cmd /c start "+ absolutePath + "\\src\\br\\furb\\rmi\\server\\start.bat");
			
			ConsultorVendas consultorVendas = new ConsultorVendas();
			Naming.rebind("//localhost/ConsultorVendas", consultorVendas);
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		System.out.println("Server running ...");
	}
}

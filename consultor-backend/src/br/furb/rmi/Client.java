package br.furb.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe de client RMI da aplicação de vendas.
 * 
 * @author helinton.steffens
 *
 */
public class Client {
	public static void main(String[] args) {
		  try {
			  Registry registry = LocateRegistry.getRegistry(1099);
		      String server = "//localhost/ConsultorVendas";	
		      IConsultorVendas consultor =  (IConsultorVendas)registry.lookup(server);
		      System.out.println("The result is: " + consultor.getMelhoresOpcoesVendasPorCliente(1));
		    }
		    catch(Exception e) {
		      System.out.println("Exception: " + e);
		    }
	}
}

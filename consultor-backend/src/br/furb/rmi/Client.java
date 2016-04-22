package br.furb.rmi;

import java.rmi.Naming;

/**
 * Classe de client RMI da aplicação de vendas.
 * 
 * @author helinton.steffens
 *
 */
public class Client {
	public static void main(String[] args) {
		  try {
		      String server = "//localhost/ConsultorVendas";	
		      IConsultorVendas consultor =  (IConsultorVendas)Naming.lookup(server);
		      System.out.println("The result is: " + consultor.getMelhoresOpcoesVendasPorCliente(1));
		    }
		    catch(Exception e) {
		      System.out.println("Exception: " + e);
		    }
	}
}

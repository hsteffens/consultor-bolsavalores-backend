package br.furb.consultor.venda;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.furb.rmi.IConsultorVendas;

public final class BOVendaAcao {

	private BOVendaAcao(){
		
	}
	
	public static IConsultorVendas getVenda(){
		  try {
			  Registry registry = LocateRegistry.getRegistry(1099);
		      String server = "//localhost/ConsultorVendas";	
		      return  (IConsultorVendas)registry.lookup(server);
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		return null;
	}
}

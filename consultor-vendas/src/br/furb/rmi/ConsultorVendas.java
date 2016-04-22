package br.furb.rmi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
	}
}

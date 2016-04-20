package br.furb.corbaclient.compras;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import Compras.AcoesCodHolder;
import Compras.Compra;
import Compras.CompraHelper;

public class ClientCorbaCompras {
	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			Compra compra = (Compra) CompraHelper.narrow(ncRef.resolve_str("ConsultoriaCompras"));
			AcoesCodHolder codigosAcoes = new AcoesCodHolder();
			compra.getMelhoresOpcoesCompras(codigosAcoes);
			
			System.out.println("The result is : " + codigosAcoes.value[0]);
			System.out.println("-----------------------------------");
		} catch (Exception e) {
			System.out.println("Client exception: " + e);
			e.printStackTrace();
		}
	}
}

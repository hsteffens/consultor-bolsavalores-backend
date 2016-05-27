package br.furb.consultor.compra;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import Compras.Compra;
import Compras.CompraHelper;

public final class BOCompraAcao {
	
	private BOCompraAcao(){
		
	}
	
	public static Compra getCompra(){
		try {
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			
			ORB orb = ORB.init(new String[]{}, props);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			return (Compra) CompraHelper.narrow(ncRef.resolve_str("ConsultoriaCompras"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

package br.furb.client.corba.compras;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import Compras.AcoesCodHolder;
import Compras.Compra;
import Compras.CompraHelper;

/**
 * Classe de client CORBA da aplicação de compras.
 * 
 * @author helinton.steffens
 *
 */
public class Client {
	public static void main(String[] args) {
		try {
			
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			
			ORB orb = ORB.init(args, props);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			Compra compra = (Compra) CompraHelper.narrow(ncRef.resolve_str("ConsultoriaCompras"));
			AcoesCodHolder acoesHolder = new AcoesCodHolder();
			compra.getMelhoresOpcoesComprasPorCliente(1, acoesHolder);
			
			for (String codigoAcao : acoesHolder.value) {
				System.out.println("The result is : " + codigoAcao);
			}
			System.out.println("-----------------------------------");
		} catch (Exception e) {
			System.out.println("Client exception: " + e);
			e.printStackTrace();
		}
	}
}

package br.furb.corba.server;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import Compras.Compra;
import Compras.CompraHelper;
import Compras.CompraObj;

/**
 * Classe de servidor CORBA da aplicação de compras.
 * 
 * @author helinton.steffens
 *
 */
public class Server {

	public static void main(String[] args) {
		try {
			// create and initialize the ORB //// get reference to rootpoa &amp;
			// activate the POAManager
			String absolutePath = System.getProperty("user.dir");
			Runtime.getRuntime().exec("cmd /c start "+ absolutePath + "\\src\\br\\furb\\corba\\server\\start.bat");

			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			ORB orb = ORB.init(args, props);
			
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			CompraObj compra = new CompraObj();
			compra.setORB(orb);

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(compra);
			Compra href = CompraHelper.narrow(ref);

			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			NameComponent path[] = ncRef.to_name("ConsultoriaCompras");
			ncRef.rebind(path, href);

			System.out.println("Addition Server ready and waiting ...");

			// wait for invocations from clients
			for (;;) {
				orb.run();
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("Server running ...");

	}
}

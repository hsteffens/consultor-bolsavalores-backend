package br.furb.consultor.time;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClockServer {

	public static void main(String[] args) {
		try {
			IClockSystem clock = new ClockSystem();
			LocateRegistry.createRegistry(2000);
	        Registry registry = LocateRegistry.getRegistry(2000);
	        registry.rebind("//localhost/Clock", clock);
		}  catch (IOException e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		System.out.println("Server running ...");
	}
}

package br.furb.consultor.time;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

public final class BOClock {

	private BOClock(){

	}

	public static LocalDateTime getUTCTimeServer(){
		try {
			DateTime t0 = new DateTime();

			IClockSystem clockSystem = getClockClient();

			DateTime t1 = new DateTime();
			long tempoMensagem = (t1.getMillis() - t0.getMillis()) / 2;
			Long utcTimeInMillis = clockSystem.getUTCTimeInMillis();
			DateTime dateUTC = new DateTime(utcTimeInMillis + tempoMensagem);

			return new LocalDateTime(dateUTC.getMillis());
		}
		catch(Exception e) {
			System.out.println("Exception: " + e);
		}

		return LocalDateTime.now();
	}
	
	public static long getTimeExternalServer(String ip){
		try {
			DateTime t0 = new DateTime();

			IClockSystem clockSystem = getClockClient();

			DateTime t1 = new DateTime();
			long tempoMensagem = (t1.getMillis() - t0.getMillis()) / 2;

			return clockSystem.getDelayConnection(ip) + tempoMensagem;
		}
		catch(Exception e) {
			System.out.println("Exception: " + e);
		}
		return 0;

	}

	private static IClockSystem getClockClient() throws NotBoundException,
			MalformedURLException, RemoteException {
		Registry registry = LocateRegistry.getRegistry(2000);
		String server = "//localhost/Clock";	
		IClockSystem clockSystem =  (IClockSystem)registry.lookup(server);
		return clockSystem;
	}

}


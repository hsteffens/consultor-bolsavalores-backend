package br.furb.consultor.time;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class ClockSystem extends UnicastRemoteObject implements IClockSystem {
	
	ClockSystem() throws RemoteException{
		super();
	}

	public Long getUTCTimeInMillis() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.setTime(new Date());
		return cal.getTimeInMillis();
	}

	public Long getDelayConnection(String ip) {
		NTPUDPClient client = new NTPUDPClient();
		// We want to timeout if a response takes longer than 10 seconds
		client.setDefaultTimeout(10000);
		try {
			client.open();
				try {
					InetAddress hostAddr = InetAddress.getByName(ip);
					System.out.println("> " + hostAddr.getHostName() + "/" + hostAddr.getHostAddress());
					TimeInfo info = client.getTime(hostAddr);
					info.computeDetails(); 
					Long offsetValue = info.getOffset();
					Long delayValue = info.getDelay();
					String delay = (delayValue == null) ? "N/A" : delayValue.toString();
					String offset = (offsetValue == null) ? "N/A" : offsetValue.toString();

					System.out.println(" Roundtrip delay(ms)=" + delay
							+ ", clock offset(ms)=" + offset); // offset in ms
					
					
					return offsetValue;
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0l;
	}

}

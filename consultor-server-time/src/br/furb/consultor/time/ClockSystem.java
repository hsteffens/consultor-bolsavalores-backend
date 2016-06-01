package br.furb.consultor.time;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.joda.time.LocalDate;

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
				TimeInfo info = client.getTime(hostAddr);
				info.computeDetails(); 
				Long offsetValue = info.getOffset();

				return offsetValue;
			} catch (SocketTimeoutException e) {
				System.out.println("Timeout connection for the ip:"+ ip+" at "+ new LocalDate(getUTCTimeInMillis()));
			}catch (IOException ioe) {
				ioe.printStackTrace();
			} 
		} catch (SocketException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0l;
	}

}

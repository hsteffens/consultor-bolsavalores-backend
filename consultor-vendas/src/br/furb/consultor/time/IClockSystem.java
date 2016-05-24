package br.furb.consultor.time;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClockSystem extends Remote{

	Long getUTCTimeInMillis()  throws RemoteException;
	
	Long getDelayConnection(String ip) throws RemoteException;
}

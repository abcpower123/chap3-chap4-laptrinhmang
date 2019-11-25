package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import mInterface.TaskInterface;

public class TaskImplement extends UnicastRemoteObject implements TaskInterface {

	public TaskImplement() throws RemoteException {
		super();
	}

	@Override
	public String Hello(String inputName) throws RemoteException {
		return "Server hello "+inputName;
	}

	@Override
	public float Rectangle(float w, float h) throws RemoteException {
		//Tinh chu vi
		return (w+h)*2;
	}

	@Override
	public float Circle(float r) throws RemoteException {
		return r*2*3.14f;
	}

}

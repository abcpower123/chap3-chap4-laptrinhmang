package server2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import mInterface.TaskInterface;

public class TaskImplement extends UnicastRemoteObject implements TaskInterface {

	public TaskImplement() throws RemoteException {
		super();
	}

	@Override
	public String Hello(String inputName) throws RemoteException {
		return "Server2 hello "+inputName;
	}

	@Override
	public float Rectangle(float w, float h) throws RemoteException {
		//Tinh dientich
		return w*h;
	}

	@Override
	public float Circle(float r) throws RemoteException {
		return r*r*3.14f;
	}

}

package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import mInterface.TaskInterface;

public class TaskImplement extends UnicastRemoteObject implements TaskInterface {

	public TaskImplement() throws RemoteException {
		super();
		
	}

	@Override
	public int calculator(char operator, int arg1, int arg2) throws RemoteException {
		switch (operator) {
		case '+':
			return arg1+arg2;
		case '-':
			return arg1-arg2;
		case '*':
			return arg1*arg2;
		case '/':
			return arg1/arg2;
		}
		return 0;
	}

	@Override
	public String CorrectName(String inputName) throws RemoteException {
		
		String result="";
		for (String ele : inputName.split(" ")) {
			if(!ele.isEmpty())
			result=result+Character.toUpperCase(ele.charAt(0))+ele.substring(1)+" ";
		}
		return result.trim();
	}

	@Override
	public float PRectangle(float w, float h) throws RemoteException {
		
		return (w+h)*2;
	}

	@Override
	public float SRectangle(float w, float h) throws RemoteException {
		return w*h;
	}

	@Override
	public float SCircle(float r) throws RemoteException {
		return 3.14f*r*r;
	}

}

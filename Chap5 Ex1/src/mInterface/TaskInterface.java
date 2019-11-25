package mInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaskInterface extends Remote {
public int calculator(char operator, int arg1, int arg2)throws RemoteException;
public String CorrectName(String inputName) throws RemoteException;
public float PRectangle(float w, float h) throws RemoteException;
public float SRectangle(float w, float h) throws RemoteException;
public float SCircle(float r) throws RemoteException;
}

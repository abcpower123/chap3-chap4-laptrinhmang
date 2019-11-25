package mInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaskInterface extends Remote {

public String Hello(String inputName) throws RemoteException;
public float Rectangle(float w, float h) throws RemoteException;
public float Circle(float r) throws RemoteException;
}

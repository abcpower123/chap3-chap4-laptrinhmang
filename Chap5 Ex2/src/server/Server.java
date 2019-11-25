package server;

import server.TaskImplement;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	private static final int PORT = 1234;
	   private static Registry registry;
	public static void main(String[] args) {
		   try {
			   //Tao may chu rmi
			registry =  LocateRegistry.createRegistry(PORT);
			//dang ki phuong thuc
			registry.bind("my RMI", new TaskImplement());
			System.out.println("Server started! Task ready for call!");
			
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
		   
	}

}

package server2;

import server2.TaskImplement;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server2 {
	private static final int PORT = 12345;
	   private static Registry registry;
	public static void main(String[] args) {
		   try {
			   //Tao may chu rmi
			registry =  LocateRegistry.createRegistry(PORT);
			//dang ki phuong thuc
			registry.bind("my RMI2", new TaskImplement());
			System.out.println("Server started! Task ready for call!");
			
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
		   
	}

}

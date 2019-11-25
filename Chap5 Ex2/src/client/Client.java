package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import mInterface.TaskInterface;

public class Client {
	private static final String HOST = "localhost";
	private static final int PORT1 = 1234;
	private static final int PORT2 = 12345;
	private static Registry registry,registry2;
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			// find and get rmi server at host/port...
			registry = LocateRegistry.getRegistry(HOST, PORT1);
			registry2 = LocateRegistry.getRegistry(HOST, PORT2);
			// find and get method on server
			//method from server1
			TaskInterface o = (TaskInterface) registry.lookup("my RMI");
			//method from server2
			TaskInterface o2 = (TaskInterface) registry2.lookup("my RMI2");

			boolean check = true;
			while (check) {
				menu();
				String choice = sc.nextLine();
				switch (choice) {
				case "1":
					Hello1(o);
					break;
				case "2":
					Prec(o);
					break;
				case "3":
					Pcir(o);
					break;
				case "4":
					Hello2(o2);
					break;
				case "5":
					Srec(o2);
					break;
				case "6":
					Scircle(o2);
					break;
				case "7":
					check = false;
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	



	private static void Scircle(TaskInterface o2)throws RemoteException {
		System.out.print("Input r: ");
		float a=sc.nextFloat();
		
		System.out.println("S of circle: "+o2.Circle(a));		
	}





	private static void Srec(TaskInterface o2)throws RemoteException {
		System.out.print("Input width: ");
		float a=sc.nextFloat();
		System.out.print("Input height: ");
		float b=sc.nextFloat();
		
		System.out.println("S of rec: "+o2.Rectangle(a, b));
		
	}



	private static void Hello2(TaskInterface o2) throws RemoteException{
		System.out.print("Your name: ");
		String s=sc.nextLine();
		System.out.println("Result: "+o2.Hello(s));
	}



	private static void Pcir(TaskInterface o)throws RemoteException {
		System.out.print("Input r: ");
		float a=sc.nextFloat();
		
		System.out.println("P of circle: "+o.Circle(a));		
	}



	private static void Prec(TaskInterface o)throws RemoteException {
		System.out.print("Input width: ");
		float a=sc.nextFloat();
		System.out.print("Input height: ");
		float b=sc.nextFloat();
		
		System.out.println("P of rec: "+o.Rectangle(a, b));
		
	}



	private static void Hello1(TaskInterface o) throws RemoteException {
		System.out.print("Your name: ");
		String s=sc.nextLine();
		System.out.println("Result: "+o.Hello(s));
	}



	private static void menu() {
		System.out.println("\n---------Menu for server1---------");
		System.out.println("1. Hello");
		System.out.println("2. P rectangle");
		System.out.println("3. P circle");
		System.out.println("\n---------Menu for server2---------");
		System.out.println("4. Hello");
		System.out.println("5. S rectangle");
		System.out.println("6. S cirlce");
		System.out.println("7. Exit");
		System.out.print("Your choice: ");
	}

}


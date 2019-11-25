package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import mInterface.TaskInterface;

public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 123;
	private static Registry registry;
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			// find and get rmi server at host/port...
			registry = LocateRegistry.getRegistry(HOST, PORT);
			// find and get method on server
			TaskInterface o = (TaskInterface) registry.lookup("my RMI");
			boolean check = true;
			while (check) {
				menu();
				String choice = sc.nextLine();
				switch (choice) {
				case "1":
					Calculator(o);
					break;
				case "2":
					Standardized(o);
					break;
				case "3":
					PRec(o);
					break;
				case "4":
					SRec(o);
					break;
				case "5":
					SCircle(o);
					break;
				case "6":
					check = false;
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void SRec(TaskInterface o) throws RemoteException {
		System.out.print("Input width: ");
		float a=sc.nextFloat();
		System.out.print("Input height: ");
		float b=sc.nextFloat();
		
		System.out.println("P of rec: "+o.SRectangle(a, b));
	}

	private static void SCircle(TaskInterface o) throws RemoteException {
		System.out.print("Input r: ");
		float a=sc.nextFloat();
		
		System.out.println("S of circle: "+o.SCircle(a));
	}

	private static void PRec(TaskInterface o) throws RemoteException {
		System.out.print("Input width: ");
		float a=sc.nextFloat();
		System.out.print("Input height: ");
		float b=sc.nextFloat();
		
		System.out.println("P of rec: "+o.PRectangle(a, b));
	}

	private static void Standardized(TaskInterface o) throws RemoteException {
		System.out.print("Input string name: ");
		String name=sc.nextLine();
		System.out.println("Result: "+o.CorrectName(name));
	}

	private static void Calculator(TaskInterface o) throws RemoteException {
		
		System.out.print("Input operator (+ - * /): ");
		String s=sc.nextLine();
		System.out.print("Input num1: ");
		int a=sc.nextInt();
		System.out.print("Input num2: ");
		int b=sc.nextInt();
		
		char c=s.charAt(0);
		System.out.println("Result: "+o.calculator(c, a, b));
	}

	private static void menu() {
		System.out.println("\n---------Menu---------");
		System.out.println("1. Calculator");
		System.out.println("2. Standardized Name");
		System.out.println("3. Get P of rectangle");
		System.out.println("4. Get S of rectangle");
		System.out.println("5. Get S of circle");
		System.out.println("6. Exit");
		System.out.print("Your choice: ");
	}

}

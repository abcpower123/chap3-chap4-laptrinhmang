package ex4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {

	public final static int port = 1134;
	public final static String hostname = "localhost";
	public static Scanner sc;
	public static void main(String args[]){
		sc=new Scanner(System.in);
		
		
		
		while (true) {
			System.out.print("Choose action (1:login; 2:register;3: exit) :");
		String input=sc.nextLine();
		
		switch(input) {
		case "1":
			login();
			break;
		case "2":
			register();
			break;
		}
		if (input.equals("3")) break;
		}
		
		System.out.print("Program exited!");
		
	}
	private static void register() {
		System.out.println("-------Register---------");
		System.out.print("Input your username: ");
		String username=sc.nextLine();
		System.out.print("Input your password: ");
		String password=sc.nextLine();
		
		boolean result= sendData("r-"+username+"-"+password);
		if(result==true) {
			System.out.println("Register succesfully");
		}
		else {
			System.out.println("Register failed, try another username!");
		}
		
	}
	private static boolean sendData(String result) {
		try {
			Socket s = new Socket("localhost",1134);		// Kết nối đến Server
			
			DataOutputStream os = new DataOutputStream(s.getOutputStream());
			DataInputStream is=new DataInputStream(s.getInputStream());
			
			os.writeUTF(result);
			
			String feedback=is.readUTF();
			
			s.close();
			if(feedback.equals("T")) return true;
			else return false;
		} catch(Exception ie){
			
			ie.printStackTrace();
			return false;
		}
		
	}
	private static void login() {
		System.out.println("-------Register---------");
		System.out.print("Input your username: ");
		String username=sc.nextLine();
		System.out.print("Input your password: ");
		String password=sc.nextLine();
		
		boolean result= sendData("l-"+username+"-"+password);
		if(result==true) {
			System.out.println("Login succesfully");
		}
		else {
			System.out.println("Login failed, please try again!");
		}
		
	}
	
}

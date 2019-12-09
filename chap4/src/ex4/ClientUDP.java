package ex4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientUDP {

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
			//gui request l-abc-123
			 DatagramSocket datagramSocket = new DatagramSocket();		// Kết nối đến Server
			 InetAddress address = InetAddress.getByName("localhost");
			 byte[] b = result.getBytes();
             DatagramPacket packetGui = new DatagramPacket(b, b.length, address, port);
             datagramSocket.send(packetGui);
             //nhan kq tu server la T / F
             byte[] buffer = new byte[50000];
             DatagramPacket packetNhan = new DatagramPacket(buffer, buffer.length);
             datagramSocket.receive(packetNhan);
             String feedback = new String(packetNhan.getData(), 0, packetNhan.getLength());
            
			if(feedback.equals("T")) return true;
			else return false;
		} catch(Exception ie){
			
			ie.printStackTrace();
			return false;
		}
		
	}
	private static void login() {
		System.out.println("-------Login---------");
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

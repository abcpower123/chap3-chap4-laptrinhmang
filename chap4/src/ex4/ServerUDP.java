package ex4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import ex1.DocSo;

public class ServerUDP {

	public final static int port = 1134;
	public static ArrayList<Account> listAccout=new ArrayList<>();
	public static void main(String[] args) {
		try {
			  DatagramSocket datagramSocket = new DatagramSocket(port);
			 
		
			System.out.println("Server is listening on port " + port);

			while (true) {
				byte[] buffer = new byte[50000];
				  DatagramPacket packetNhan = new DatagramPacket(buffer,buffer.length);
	                datagramSocket.receive(packetNhan);
	                String recived = new String(packetNhan.getData(),0,packetNhan.getLength());

				System.out.println("Received request from client: " + recived);
				String sent="";
				switch(recived.charAt(0)) {
				case 'l':
					sent=ProcessLogin(recived);
					break;
				case 'r':
					sent= ProcessRegister(recived);
					break;
				default:
					sent="F";
					break;
				}
				System.out.println(sent);
	                Scanner scanner = new Scanner(System.in);
	                buffer = sent.getBytes();
	                DatagramPacket packetGui = new DatagramPacket(buffer,buffer.length,packetNhan.getAddress(),packetNhan.getPort());
	                datagramSocket.send(packetGui);
	                

				System.out.println("-----------Activity done!---------");


			}

		} catch (Exception e) {
			System.err.println(" Server Creation Error:" + e);
		}
	}

	private static String ProcessRegister(String recived) {
		String username=recived.substring(2,recived.lastIndexOf('-'));
		String password=recived.substring(recived.lastIndexOf('-')+1,recived.length());
		System.out.println("Client register with username: "+username+", password: "+password );
		for (Account account : listAccout) {
			if(account.getUsername().equals(username)) {
				System.out.println("Failed because there is a same username already registered");
				return "F";
			}
		}
		listAccout.add(new Account(username, password));
		System.out.println("Succesfull!");
		return "T";
	}

	private static String ProcessLogin(String recived) {
		String username=recived.substring(2,recived.lastIndexOf('-'));
		String password=recived.substring(recived.lastIndexOf('-')+1,recived.length());
		System.out.println("Client login with username: "+username+", password: "+password );
		for (Account account : listAccout) {
			if(account.getUsername().equals(username)&&account.getPassword().equals(password)) {
				System.out.println("Succesfull!");
				return "T";
				
			}
		}
		System.out.println("Failed due to incorrect username or password!");
		return "F";
	}

	
}

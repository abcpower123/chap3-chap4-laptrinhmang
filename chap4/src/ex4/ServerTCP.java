package ex4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ex1.DocSo;

public class ServerTCP {

	public final static int port = 1134;
	public static ArrayList<Account> listAccout=new ArrayList<>();
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server is listening on port " + port);

			while (true) {

				Socket s = ss.accept();
				System.out.println("---------Accept client!------------");

				DataOutputStream os = new DataOutputStream(s.getOutputStream());
				DataInputStream is = new DataInputStream(s.getInputStream());

				String recived = is.readUTF();

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

				os.writeUTF(sent);

				System.out.println("-----------Client exited!---------");

				s.close();

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

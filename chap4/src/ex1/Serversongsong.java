package ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serversongsong {
	public final static int port = 1134;
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server is listening on port "+port);
			while(true) {
				Socket s = ss.accept();
				System.out.println("Accept client!");
				Threadxulysongsong t=new Threadxulysongsong(s);
				t.start();				
			}
			
					
								
		} catch (Exception e) {
			System.err.println(" Server Creation Error:"+e);
		}
	}
	

}

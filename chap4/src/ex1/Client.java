package ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		String input=sc.nextLine();
		try {
			Socket s = new Socket("localhost",1134);		// Kết nối đến Server
			
			DataOutputStream os = new DataOutputStream(s.getOutputStream());
			DataInputStream is=new DataInputStream(s.getInputStream());
			
			os.writeUTF(input);
			
			System.out.println("Send success!");
			
			System.out.println("Output from server:");
			
			System.out.println(is.readUTF());
			sc.close();
		} catch(Exception ie){
			
			ie.printStackTrace();
		}	
	}	


}

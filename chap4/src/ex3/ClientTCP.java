package ex3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {

	public final static int port = 1002;
	
	public static void main(String args[]){
		
		Scanner sc=new Scanner(System.in);
		try {
			Socket s = new Socket("localhost",port);		// Kết nối đến Server
			
			DataOutputStream os = new DataOutputStream(s.getOutputStream());
			DataInputStream is=new DataInputStream(s.getInputStream());
			
			//thread allway listen
			Thread t=new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							System.out.println("Partner: "+is.readUTF());
						} catch (IOException e) {
							System.out.println("Server exit!");
							break;
						}
					}
				}
			});
			t.start();
			
			//send my messagge
			while(true) {
				
				String message=sc.nextLine();
				if(message.equals("/pp")) {
					t.stop();
					s.close();
					break;
				}
				else
				os.writeUTF(message);
				
			}
			
			
			
		} catch(Exception ie){
			
			System.out.println("Program exit");
		}	
		System.out.println("Program exit");
	}	


}

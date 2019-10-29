package ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servertuantu {

	public final static int port = 1134;
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server is listening on port "+port);
			
			while (true) {
				
					Socket s = ss.accept();
					System.out.println("Accept client!");

					DataOutputStream os = new DataOutputStream(s.getOutputStream());
					DataInputStream is=new DataInputStream(s.getInputStream());
					
                    String recived = is.readUTF();
                    					
					System.out.println("Recived input from client: "+recived);
					String result= process(recived);
					System.out.println("Ouput: "+result);
					
					os.writeUTF(result);
					
					System.out.println("Client exited!");
					
					s.close();
					
					}
			
		} catch (Exception e) {
			System.err.println(" Server Creation Error:"+e);
		}
	}
	private static String process(String recived) {
		//check correct input
		for(char a: recived.toCharArray()) {
			if(a<'0'||a>'9') return "Không phải là số nguyên";
		}
		return DocSo.ChuyenSangChu(recived);
	}

}

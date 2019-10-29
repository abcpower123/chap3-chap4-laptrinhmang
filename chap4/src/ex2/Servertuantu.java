package ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ex1.DocSo;

public class Servertuantu {

	public final static int port = 1134;

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server is listening on port " + port);

			while (true) {

				Socket s = ss.accept();
				System.out.println("Accept client!");

				DataOutputStream os = new DataOutputStream(s.getOutputStream());
				DataInputStream is = new DataInputStream(s.getInputStream());

				String recived = is.readUTF();

				System.out.println("Recived input from client: " + recived);
				String result = process(recived);
				System.out.println("Ouput: " + result);

				os.writeUTF(result);

				System.out.println("Client exited!");

				s.close();

			}

		} catch (Exception e) {
			System.err.println(" Server Creation Error:" + e);
		}
	}

	private static String process(String recived) {
		char operator=recived.charAt(0);
		int num1=Integer.parseInt(recived.substring(2,recived.lastIndexOf(' ')));
		int num2=Integer.parseInt(recived.substring(recived.lastIndexOf(' ')+1,recived.length()-1));
		int result=0;
		switch (operator) {
		case '+':
			result=num1+num2;
			break;
		case '-':
			result=num1-num2;
			break;
		case '*':
			result=num1*num2;
			break;
		default:
			result=num1/num2;
			break;
		}
		return ""+result;
	}

}

package ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import ex1.DocSo;

public class Threadxulysongsong extends Thread {
	Socket s;

	public Threadxulysongsong(Socket s) {
		super();
		this.s = s;
	}

	@Override
	public void run() {
		try {
			DataOutputStream os = new DataOutputStream(s.getOutputStream());
			DataInputStream is = new DataInputStream(s.getInputStream());

			String recived = is.readUTF();

			System.out.println("Recived input from client: " + recived);
			String result = process(recived);
			System.out.println("Ouput: " + result);

			os.writeUTF(result);

			System.out.println("Client exited!");

			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String process(String recived) {
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

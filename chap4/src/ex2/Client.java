package ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public final static int port = 1134;
	public static void main(String args[]){
		System.out.println("Input an operation: ");
		Scanner sc=new Scanner(System.in);
		String input=sc.nextLine();
		String formatedInput=FormatInput(input);
		try {
			Socket s = new Socket("localhost",1134);		// Kết nối đến Server
			
			DataOutputStream os = new DataOutputStream(s.getOutputStream());
			DataInputStream is=new DataInputStream(s.getInputStream());
			
			os.writeUTF(formatedInput);
			
			System.out.println("Send success!");
			
			System.out.println("Output from server:");
			
			System.out.println(is.readUTF());
			sc.close();
		} catch(Exception ie){
			
			ie.printStackTrace();
		}
	}
	private static String FormatInput(String input) {
		//remove all white space
		input = input.replaceAll("\\s","");
		int indexO=-1;
		//find index of operator
		for(int i=0;i<input.length();i++) {
			if(input.charAt(i)<'0'||input.charAt(i)>'9') {
				indexO=i;
				break;
			}
		}
		if(indexO==-1) {
			System.out.println("Input is not the correct format!");
		}
		String result=input.charAt(indexO)+" "+input.substring(0,indexO)+" "+input.substring(indexO+1, input.length())+"\n";
		return result;
	}
		
}

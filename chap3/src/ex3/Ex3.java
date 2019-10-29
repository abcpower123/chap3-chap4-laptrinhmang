package ex3;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.Scanner;

public class Ex3 {

	public static void main(String[] args) {
		System.out.println("---------Ex3 start-------");
		String address;
		System.out.print("Input address: ");
		Scanner sc=new Scanner(System.in);
		address=sc.nextLine();
		try {
			InetAddress inputAddress= InetAddress.getByName(address);
			InetAddress localAddress= InetAddress.getLocalHost();
			
			System.out.println("Input host name: "+inputAddress.getHostName());
			System.out.println("IP input Host: "+inputAddress.getHostAddress());
			System.out.println("------------------------------");
			System.out.println("Localhost name: "+localAddress.getHostName());
			System.out.println("Localhost ip: "+localAddress.getHostAddress());
			
		} catch (IOException e) {
			System.out.println("URL not vallid");
			e.printStackTrace();;
		}
		System.out.println("---------Ex3 exitted-------");
	}
}

package ex1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Ex1 {

	public static void main(String[] args) {
		String url;
		System.out.print("Input url: ");
		Scanner sc=new Scanner(System.in);
		url=sc.nextLine();
		try {
			URL u=new URL(url);
			System.out.println("File name: "+u.getFile());
			System.out.println("Host name: "+u.getHost());
			System.out.println("Port num: "+u.getPort());
			System.out.println("Protocol: "+u.getProtocol());
		} catch (MalformedURLException e) {
			System.err.println("URL not vallid");
		}
	}

}

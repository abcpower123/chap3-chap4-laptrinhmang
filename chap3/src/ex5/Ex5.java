package ex5;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sun.net.util.IPAddressUtil;

public class Ex5 {
	static ArrayList<InetAddress> listBlock=null;
	
	public static void main(String[] args) {
		System.out.println("---------Ex5 start-------");
		String input;
		Scanner sc=new Scanner(System.in);
		input=sc.nextLine();
		try {
			
			InetAddress addr = InetAddress.getByName(input);
			initListBlock();
			
			 URL url = new URL("http://"+addr.getHostAddress());
			 
			 if(!checkAddress(addr)) {
				System.err.println("The website is blocked!");
				
			 }
			 else {
			 //read and show content
			 InputStream is=(InputStream) url.getContent();
			 Scanner s = new Scanner(is);
			 while (s.hasNext()) {
					System.out.println(s.nextLine());
				}
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("---------Ex5 exitted-------");
	}

	private static boolean checkAddress(InetAddress ia) {
		for (InetAddress i : listBlock) {
			if(ia.getHostAddress().equals(i.getHostAddress())) 
				return false;
		}
		return true;
	}

	private static void initListBlock() throws UnknownHostException {
		listBlock =new ArrayList<>();
		listBlock.add(InetAddress.getByName("facebook.com"));
		listBlock.add(InetAddress.getByName("youtube.com"));
		listBlock.add(InetAddress.getByName("y8.com"));
	}
}

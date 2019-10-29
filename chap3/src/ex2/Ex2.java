package ex2;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;



public class Ex2 {
	public static void main(String[] args) {
		System.out.println("---------Ex2 start-------");
		String url;
		System.out.print("Input url: ");
		Scanner sc=new Scanner(System.in);
		url=sc.nextLine();
		try {
			URL u=new URL(url);
			InputStream is=(InputStream) u.getContent();
		
			Scanner s = new Scanner(is);
			while (s.hasNext()) {
				System.out.println(s.nextLine());
			}
			
		} catch (IOException e) {
			System.out.println("URL not vallid");
		}
		System.out.println("---------Ex2 exitted-------");
	}
}

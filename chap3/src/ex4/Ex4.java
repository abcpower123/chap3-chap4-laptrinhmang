package ex4;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;

public class Ex4 {
	public static void main(String[] args) {
		System.out.println("---------Ex4 start-------");
		String url;
		System.out.print("Input url: ");
		Scanner sc=new Scanner(System.in);
		url=sc.nextLine();
		try {
			URL u=new URL(url);
			URLConnection uc=u.openConnection();
			System.out.println("Length: "+uc.getContentLength());
			System.out.println("Date created: "+new Date(uc.getDate()));
			System.out.println("Last modified: "+new Date(uc.getLastModified()));
			System.out.println("Expried: "+new Date(uc.getExpiration()));
			
		} catch (IOException e) {
			System.out.println("URL not vallid");
		}
		System.out.println("---------Ex4 exitted-------");
	}
}

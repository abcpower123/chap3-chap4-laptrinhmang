package ex6;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Ex6 {
	static Scanner sc=new Scanner(System.in);
	static ArrayList<HistoryWeb> history=new ArrayList<>();
	public static void main(String[] args) {
		while(true) {
			System.out.println("Choose acction: '1' is accessing website, '2' is show history,'3' exit");
			System.out.print("Choose acction (1/2/3):");
			String choice=sc.nextLine();
			if(choice.equals("1")) {
				goWeb();
			}
			else if(choice.equals("2")) {
				showHistory();
			}
			else if(choice.equals("3")) {
				break;
			}
			else System.out.println("You not choose 1/2 or 3, please try again.");
		}
		System.out.println("Program exitted!");
	}
	private static void updateHistory(InetAddress ia) {
		boolean checkExist=false;
		for (HistoryWeb historyWeb : history) {
			if(historyWeb.getAddress().getHostAddress().equals(ia.getHostAddress())) {
				historyWeb.setLastVisit(new Date());
				checkExist=true;
				return;
			}
		}
		if (checkExist==false) {
			history.add(new HistoryWeb(ia, new Date()));
		}
	}
	private static void showHistory() {
		System.out.println("************************History*******************************");
		for (HistoryWeb historyWeb : history) {
			System.out.println("-------------------------------");
			System.out.println("Host name: "+historyWeb.getAddress().getHostName());
			System.out.println("Host address: "+historyWeb.getAddress().getHostAddress());
			System.out.println("Last visited: "+historyWeb.getLastVisit());
			System.out.println("-------------------------------");
		}
	}
	private static void goWeb() {
		String url;
		System.out.print("Input url: ");
		
		url=sc.nextLine();
		try {
			URL u=new URL(url);
			InetAddress ia=InetAddress.getByName(u.getHost());
			
			updateHistory(ia);
			//read and show content
			 InputStream is=(InputStream) u.getContent();
			 Scanner s = new Scanner(is);
			 while (s.hasNext()) {
					System.out.println(s.nextLine());
				}
			
			
		} catch (IOException e) {
			System.err.println("URL not vallid");
		}
	}
}

package Control;

import java.util.Scanner;

import ex1.Ex1;
import ex2.Ex2;
import ex3.Ex3;
import ex4.Ex4;
import ex5.Ex5;
import ex6.Ex6;

public class Control {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String choice;
		while(true) {
			System.out.println("-------------Excersice Chapter3---------------");
			System.out.print("Excersice number (1..6; 7: mean exit): ");
			choice=sc.nextLine();
			switch (choice) {
			case "1":
				Ex1.main(null);
				break;
			case "2":
				Ex2.main(null);
				break;
			case "3":
				Ex3.main(null);
				break;
			case "4":
				Ex4.main(null);
				break;
			case "5":
				Ex5.main(null);
				break;
			case "6":
				Ex6.main(null);
				break;
			
			default:
				break;
			}
			if(choice.equals("7")) break;
		}
	}

}

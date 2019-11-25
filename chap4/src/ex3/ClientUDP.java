package ex3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientUDP {
	public final static int port = 1007;
	public static void main(String[] args) {
		  try {
	            DatagramSocket datagramSocket = new DatagramSocket();
	        
	            InetAddress address = InetAddress.getByName("localhost");
	            while (true) {
	            	 //sent
	                Scanner scanner = new Scanner(System.in);
	                System.out.print("Me : ");
	                String str = scanner.nextLine();
	                byte[] b = str.getBytes();
	               
	                DatagramPacket packetGui = new DatagramPacket(b, b.length, address, port);
	                datagramSocket.send(packetGui);
	                if (str.equals("/pp")) {
	                    System.out.println("Dong ket noi");
	                    datagramSocket.close();
	                }
	                //recive
	                byte[] buffer = new byte[50000];
	                DatagramPacket packetNhan = new DatagramPacket(buffer, buffer.length);
	                datagramSocket.receive(packetNhan);
	                String message = new String(packetNhan.getData(), 0, packetNhan.getLength());
	                System.out.println("Partner: " + message);
	                if (str.equals("/pp")) {
	                    System.out.println("Dong ket noi");
	                    datagramSocket.close();
	                    break;
	                }
	            }
	        }catch (Exception e){
	               System.out.println("Program exit");
	        }
	}

}

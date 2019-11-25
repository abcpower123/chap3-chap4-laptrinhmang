package ex3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerUDP {
	public final static int port = 1007;
	public static void main(String[] args) {
		try {
			
            DatagramSocket datagramSocket = new DatagramSocket(port);
            System.out.println("Server is listening on port "+port);
           
            while (true){
            	//recieve
            	 byte[] buffer = new byte[50000];
                DatagramPacket packetNhan = new DatagramPacket(buffer,buffer.length);
                datagramSocket.receive(packetNhan);
                String message = new String(packetNhan.getData(),0,packetNhan.getLength());
                System.out.println("Partner: "+message);
                if(message.equals("/pp")){
                    System.out.println("Dong ket noi");
                    datagramSocket.close();
                }
                //sent
                System.out.print("Me: ");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                buffer = str.getBytes();
                DatagramPacket packetGui = new DatagramPacket(buffer,buffer.length,packetNhan.getAddress(),packetNhan.getPort());
                datagramSocket.send(packetGui);
                if(message.equals("/pp")){
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

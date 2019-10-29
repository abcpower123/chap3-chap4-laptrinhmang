package ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Threadxulysongsong extends Thread{
Socket s;

public Threadxulysongsong(Socket s) {
	super();
	this.s = s;
}

@Override
	public void run() {
	try {
	DataOutputStream os = new DataOutputStream(s.getOutputStream());
	DataInputStream is=new DataInputStream(s.getInputStream());
	
    String recived = is.readUTF();
    					
	System.out.println("Recived input from client: "+recived);
	String result= process(recived);
	System.out.println("Ouput: "+result);
	
	os.writeUTF(result);
	
	System.out.println("Client exited!");
	
	s.close();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	}

private String process(String recived) {
	//check correct input
	for(char a: recived.toCharArray()) {
		if(a<'0'||a>'9') return "Không phải là số nguyên";
	}
	return DocSo.ChuyenSangChu(recived);
	}
}
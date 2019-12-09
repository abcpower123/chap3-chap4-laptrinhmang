package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.CustomListModel;

public class TCPServcices extends Thread {
	private int port;
	public MainUI ui;
	private ServerSocket ss;
	public ArrayList<ClientModel> listClient;
	
	public TCPServcices(int port, MainUI ui) {
		super();
		this.port = port;
		this.ui = ui;
		listClient=new ArrayList<>();
	}
	
	public void disconnectClient(String clName) {
		
		
		 ui.txtLog.setText(ui.txtLog.getText()+"Client "+clName+" disconnect!\n");
		 ui.clm.remove(clName);
		 ui.listClient.updateUI();
		 
		 for (ClientModel clientModel : listClient) {
			
			if(clientModel.getClientName().equals(clName)) {
				
				clientModel.sayGoodbye();
				listClient.remove(clientModel);
				
			}
				
		}
		 
	}
	public void disconnectAll() {
		 ui.txtLog.setText(ui.txtLog.getText()+listClient.size());
			for (ClientModel clientModel : listClient) {
				 ui.txtLog.setText(ui.txtLog.getText()+"Client "+clientModel.getClientName()+" disconnect!\n");
				 ui.clm.remove(clientModel.getClientName());
				 ui.listClient.updateUI();
				clientModel.sayGoodbye();
		}
			listClient.removeAll(listClient);
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.stop();
	}
	@Override
	public void run() {
		try {
			
				 ss = new ServerSocket(port);
				ui.txtLog.setText(ui.txtLog.getText()+"Server started succes!\n");
				
				while(true) {
					Socket s = ss.accept();
					ui.txtLog.setText(ui.txtLog.getText()+"A client connected!\n");
					DataInputStream is = new DataInputStream(s.getInputStream());
					
					String clientName=is.readUTF();
					ui.txtLog.setText(ui.txtLog.getText()+"Client: "+clientName +"\n");
					ClientModel m=new ClientModel(clientName, s,TCPServcices.this);
					listClient.add(m);
					ui.clm.add(clientName);
					ui.listClient.updateUI();
					m.start();				
			
						
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendFileWithClient(String client) {
		for (ClientModel clientModel : listClient) {
			if(clientModel.getClientName().equals(client)) {
				clientModel.sendFile();
				break;
			}
		}
		
	}
}

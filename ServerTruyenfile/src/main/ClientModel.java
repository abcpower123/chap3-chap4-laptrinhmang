package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import client.ClientUI;
import model.FileInfo;

public class ClientModel extends Thread{
	private String clientName;
	private Socket s;
	private TCPServcices services;
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	public void sayGoodbye() {
		
		try {
			FileInfo f=new FileInfo();
			f.setStatus("end");
			new ObjectOutputStream(s.getOutputStream()).writeObject(f);
			this.stop();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while (true) {
			 FileInfo f= (FileInfo) new ObjectInputStream(s.getInputStream()).readObject();
			 if(f!=null) {
				 //end
				 if(f.getStatus().equals("end")) {
					 services.ui.txtLog.setText(services.ui.txtLog.getText()+"Client "+clientName+" disconnect!\n");
					 services.ui.clm.remove(clientName);
					 services.ui.listClient.updateUI();
					 services.listClient.remove(ClientModel.this);
					 break;
				 }
				//file
				 if(f.getStatus().equals("file")) {
					 services.ui.txtLog.setText(services.ui.txtLog.getText()+"Client "+clientName+" send a file!\n");
					
					
					 Object [] options= {"Yes","No"};
					int a=JOptionPane.showConfirmDialog(services.ui, "Recived from "+this.clientName+"?", "A file comming", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
					 //0: yes, 1:no
					if(a==1) continue;
					//pick where to save
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");   
					 fileChooser.setSelectedFile(new File(f.getFilename()));
					int userSelection = fileChooser.showSaveDialog(services.ui);
					 
					if (userSelection == JFileChooser.APPROVE_OPTION) {
					    File fileToSave = fileChooser.getSelectedFile();
					    f.setDestinationDirectory(fileChooser.getSelectedFile().getAbsolutePath());
					    services.ui.txtLog.setText(services.ui.txtLog.getText()+"Save to" + fileChooser.getSelectedFile().getAbsolutePath()+"\n");
					    services.ui.txtLog.setText(services.ui.txtLog.getText()+"Finish reicived file\n");
					    createFile(f);
					}
					
				 }
			 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 private boolean createFile(FileInfo fileInfo) {
	        BufferedOutputStream bos = null;
	         
	        try {
	            if (fileInfo != null) {
	                File fileReceive = new File(fileInfo.getDestinationDirectory()) ;
	                bos = new BufferedOutputStream(
	                        new FileOutputStream(fileReceive));
	                // write file content
	                bos.write(fileInfo.getDataBytes());
	                bos.flush();
	                if (bos != null) {
	                	bos.close();
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        } 
	        return true;
	    }
	 public void sendFile() {
			
			JFileChooser jx=new JFileChooser();
			jx.showOpenDialog(services.ui);
			services.ui.txtLog.setText(services.ui.txtLog.getText()+jx.getSelectedFile().getAbsolutePath());
			
			FileInfo f=new FileInfo();
			f.setStatus("file");
			f.setFilename(jx.getSelectedFile().getName());
			BufferedInputStream bis = null;
			
			//set byte
			try {
				 File sourceFile = new File(jx.getSelectedFile().getAbsolutePath());
				bis = new BufferedInputStream(new FileInputStream(jx.getSelectedFile().getAbsolutePath()));
				 byte[] fileBytes = new byte[(int) sourceFile.length()];
				 bis.read(fileBytes, 0, fileBytes.length);
				 f.setDataBytes(fileBytes);
				new ObjectOutputStream(s.getOutputStream()).writeObject(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public ClientModel(String clientName, Socket s, TCPServcices services) {
		super();
		this.clientName = clientName;
		this.s = s;
		this.services=services;
	}

}

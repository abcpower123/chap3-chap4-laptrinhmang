package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import main.ClientModel;
import main.TCPServcices;
import model.FileInfo;

public class ClientService extends Thread {
	private Socket s;
	private ClientUI ui;
	
	
public ClientService(Socket s, ClientUI ui) {
		super();
		this.s = s;
		this.ui = ui;
	}
public void sendFile() {
	
	JFileChooser jx=new JFileChooser();
	jx.showOpenDialog(ui);
	ui.txtLog.setText(ui.txtLog.getText()+jx.getSelectedFile().getAbsolutePath());
	
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

@Override
public void run() {
	try {
		while (true) {
		 FileInfo f= (FileInfo) new ObjectInputStream(s.getInputStream()).readObject();
		 if(f!=null) {
			 //end
			 if(f.getStatus().equals("end")) {
				 ui.txtLog.setText(ui.txtLog.getText()+"Disconnect from server!");
				 ui.btnConnectToServer.setEnabled(true);
				 ui.btnDisconnectServer.setEnabled(false);
				 break;
			 }
			//file
			 if(f.getStatus().equals("file")) {
				 ui.txtLog.setText(ui.txtLog.getText()+"Server send a file!\n");
				
				
				 Object [] options= {"Yes","No"};
				int a=JOptionPane.showConfirmDialog(ui, "Recived from server?", "A file comming", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				 //0: yes, 1:no
				if(a==1) continue;
				//pick where to save
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				 fileChooser.setSelectedFile(new File(f.getFilename()));
				int userSelection = fileChooser.showSaveDialog(ui);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    f.setDestinationDirectory(fileChooser.getSelectedFile().getAbsolutePath());
				    ui.txtLog.setText(ui.txtLog.getText()+"Save to" + fileChooser.getSelectedFile().getAbsolutePath()+"\n");
				    createFile(f);
				    ui.txtLog.setText(ui.txtLog.getText()+"Finish reicived file\n");
				}
				
			 }
		 }
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}

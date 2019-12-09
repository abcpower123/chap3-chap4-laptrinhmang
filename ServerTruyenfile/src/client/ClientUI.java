package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.FileInfo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class ClientUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtIp;
	private JTextField txtPort;
	private JTextField txtName;
	private Socket client;
	private ClientService service;
	public JButton btnDisconnectServer;
	public JButton btnConnectToServer;
	public JTextPane txtLog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUI frame = new ClientUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientUI() {
		setTitle("Client ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("IP server");
		label.setBounds(192, 17, 46, 14);
		contentPane.add(label);
		
		txtIp = new JTextField();
		txtIp.setText("localhost");
		txtIp.setColumns(10);
		txtIp.setBounds(253, 13, 153, 23);
		contentPane.add(txtIp);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(192, 61, 46, 14);
		contentPane.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setText("1234");
		txtPort.setColumns(10);
		txtPort.setBounds(253, 57, 153, 23);
		contentPane.add(txtPort);
		
		btnConnectToServer = new JButton("Connect to server");
		btnConnectToServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client=new Socket(txtIp.getText(), Integer.parseInt(txtPort.getText()));
					//send name to server
					new DataOutputStream(client.getOutputStream()).writeUTF(txtName.getText());
					service=new ClientService(client, ClientUI.this);
					service.start();
					btnConnectToServer.setEnabled(false);
					btnDisconnectServer.setEnabled(true);
					txtLog.setText(txtLog.getText()+"Connected to server!\n");
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnConnectToServer.setBounds(132, 146, 153, 29);
		contentPane.add(btnConnectToServer);
		
		btnDisconnectServer = new JButton("Disconnect server");
		btnDisconnectServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileInfo f=new FileInfo();
					f.setStatus("end");
					new ObjectOutputStream(client.getOutputStream()).writeObject(f);
					service.stop();
					client.close();
					btnDisconnectServer.setEnabled(false);
					btnConnectToServer.setEnabled(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDisconnectServer.setBounds(311, 146, 145, 29);
		contentPane.add(btnDisconnectServer);
		
		JLabel lblLog = new JLabel("Log");
		lblLog.setBounds(311, 269, 46, 14);
		contentPane.add(lblLog);
		
		JButton btnNewButton = new JButton("Send file to Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				service.sendFile();
			}
		});
		btnNewButton.setBounds(145, 212, 140, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblClientName = new JLabel("Client Name");
		lblClientName.setBounds(192, 95, 75, 14);
		contentPane.add(lblClientName);
		
		txtName = new JTextField();
		txtName.setText("hao");
		txtName.setColumns(10);
		txtName.setBounds(253, 91, 153, 23);
		contentPane.add(txtName);
		
		txtLog = new JTextPane();
		txtLog.setBounds(10, 294, 690, 195);
		contentPane.add(txtLog);
	}

}

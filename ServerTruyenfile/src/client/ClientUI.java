package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MainUI;
import model.FileInfo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

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
	private JLabel label_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
					    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					        if ("Nimbus".equals(info.getName())) {
					            UIManager.setLookAndFeel(info.getClassName());
					            break;
					        }
					    }
					} catch (Exception e) {
					    // If Nimbus is not available, you can set the GUI to another look and feel.
					}
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
		label.setFont(new Font("Candara", Font.BOLD, 14));
		label.setBounds(190, 6, 75, 32);
		contentPane.add(label);
		
		txtIp = new JTextField();
		txtIp.setFont(new Font("Candara", Font.BOLD, 14));
		txtIp.setText("localhost");
		txtIp.setColumns(10);
		txtIp.setBounds(301, 7, 155, 30);
		contentPane.add(txtIp);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Candara", Font.BOLD, 14));
		lblPort.setBounds(190, 54, 55, 19);
		contentPane.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setFont(new Font("Candara", Font.BOLD, 14));
		txtPort.setText("1234");
		txtPort.setColumns(10);
		txtPort.setBounds(301, 47, 155, 32);
		contentPane.add(txtPort);
		
		btnConnectToServer = new JButton("Connect to server");
		btnConnectToServer.setBackground(new Color(0, 138, 201));
		btnConnectToServer.setForeground(new Color(255, 255, 255));
		btnConnectToServer.setFont(new Font("Century Gothic", Font.BOLD, 14));
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
					txtLog.setText(txtLog.getText()+"Không kết nối được đến server!\n");
				}
				
			}
		});
		btnConnectToServer.setBounds(178, 134, 155, 51);
		contentPane.add(btnConnectToServer);
		
		btnDisconnectServer = new JButton("Disconnect server");
		btnDisconnectServer.setEnabled(false);
		btnDisconnectServer.setBackground(new Color(220, 0, 48));
		btnDisconnectServer.setForeground(new Color(255, 255, 255));
		btnDisconnectServer.setFont(new Font("Century Gothic", Font.BOLD, 14));
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
		btnDisconnectServer.setBounds(387, 134, 147, 51);
		contentPane.add(btnDisconnectServer);
		
		JLabel lblLog = new JLabel("Log");
		lblLog.setFont(new Font("Candara", Font.BOLD, 14));
		lblLog.setBounds(332, 269, 55, 14);
		contentPane.add(lblLog);
		
		JButton btnNewButton = new JButton("Send file to Server");
		btnNewButton.setBackground(new Color(87, 55, 138));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				service.sendFile();
				}
				catch(Exception e) {
					txtLog.setText(txtLog.getText()+"Không kết nối được đến server!\n");
				}
			}
		});
		btnNewButton.setBounds(269, 206, 171, 51);
		contentPane.add(btnNewButton);
		
		JLabel lblClientName = new JLabel("Client Name");
		lblClientName.setFont(new Font("Candara", Font.BOLD, 14));
		lblClientName.setBounds(190, 97, 75, 14);
		contentPane.add(lblClientName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Candara", Font.BOLD, 14));
		txtName.setText("hao");
		txtName.setColumns(10);
		txtName.setBounds(301, 88, 155, 32);
		contentPane.add(txtName);
		
		txtLog = new JTextPane();
		txtLog.setFont(new Font("Candara", Font.BOLD, 14));
		txtLog.setBounds(10, 294, 690, 195);
		contentPane.add(txtLog);
		
		label_1 = new JLabel();
		label_1.setBounds(10, 12, 147, 112);
		label_1.setIcon(new ImageIcon(new ImageIcon(ClientUI.class.getResource("/model/Managed-File-Transfer-Market.jpg")).getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_DEFAULT)));
		contentPane.add(label_1);
	}

}

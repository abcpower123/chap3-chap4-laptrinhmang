package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.CustomListModel;

import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;

public class MainUI extends JFrame {

	private JPanel contentPane;
	public JList listClient;
	public JTextPane txtLog;
	public CustomListModel clm;
	public TCPServcices services;
	private JButton btnStartListener;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
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
	int value=100,i=0;
	private JTextField txtIP;
	private JTextField txtPort;
	public MainUI() {
		setTitle("File transfer Server TCP IP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listClient = new JList();
		clm=new CustomListModel();
		listClient.setModel(clm);
		listClient.setBounds(10, 45, 185, 113);
		contentPane.add(listClient);
		
		JLabel lblDanhSchClient = new JLabel("Danh S\u00E1ch client k\u1EBFt n\u1ED1i");
		lblDanhSchClient.setBounds(49, 11, 126, 23);
		contentPane.add(lblDanhSchClient);
		
		JLabel lblIpServer = new JLabel("IP server");
		lblIpServer.setBounds(325, 31, 46, 14);
		contentPane.add(lblIpServer);
		
		JLabel lblHost = new JLabel("Port");
		lblHost.setBounds(325, 75, 46, 14);
		contentPane.add(lblHost);
		
		txtIP = new JTextField();
		txtIP.setEditable(false);
		txtIP.setBounds(376, 25, 153, 23);
		contentPane.add(txtIP);
		txtIP.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setText("1234");
		txtPort.setBounds(376, 72, 153, 23);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		btnStartListener = new JButton("Start server");
		btnStartListener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					txtIP.setText(InetAddress.getLocalHost().getHostAddress());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				services=new TCPServcices(Integer.parseInt(txtPort.getText()),MainUI.this);
				services.start();
				btnStartListener.setEnabled(false);
			}
		});
		btnStartListener.setBounds(311, 122, 111, 29);
		contentPane.add(btnStartListener);
		
		JButton btnStopServer = new JButton("Stop server");
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				services.disconnectAll();
				btnStopServer.setEnabled(false);
				btnStartListener.setEnabled(true);
			}
		});
		btnStopServer.setBounds(444, 122, 111, 29);
		contentPane.add(btnStopServer);
		
		JButton btnGiFilen = new JButton("G\u1EEDi file \u0111\u1EBFn client");
		btnGiFilen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listClient.getSelectedIndex()==-1) {
					txtLog.setText(txtLog.getText()+"Chưa chọn client trong danh sách!\n");
					return;
				}
				services.sendFileWithClient(clm.getElementAt(listClient.getSelectedIndex()).toString());
			}
		});
		btnGiFilen.setBounds(157, 188, 153, 32);
		contentPane.add(btnGiFilen);
		
		JButton btnNgtKtNi = new JButton("Ng\u1EAFt k\u1EBFt n\u1ED1i v\u1EDBi client");
		btnNgtKtNi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listClient.getSelectedIndex()==-1) {
					txtLog.setText(txtLog.getText()+"Chưa chọn client trong danh sách!\n");
					return;
				}
				try {
				services.disconnectClient(clm.getElementAt(listClient.getSelectedIndex()).toString());
				}
				catch (Exception ee) {
					
				}
			}
		});
		btnNgtKtNi.setBounds(10, 184, 137, 36);
		contentPane.add(btnNgtKtNi);
		
		txtLog = new JTextPane();
		txtLog.setBounds(10, 275, 784, 294);
		contentPane.add(txtLog);
		
		JLabel lblLog = new JLabel("Log");
		lblLog.setBounds(355, 250, 46, 14);
		contentPane.add(lblLog);
	}
}

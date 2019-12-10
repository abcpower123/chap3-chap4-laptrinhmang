package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import model.CustomListModel;

import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;

public class MainUI extends JFrame {

	private JPanel contentPane;
	public JList listClient;
	public JTextPane txtLog;
	public CustomListModel clm;
	public TCPServcices services;
	public JButton btnStopServer;
	private JButton btnStartListener;
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
		listClient.setFont(new Font("Candara", Font.BOLD, 14));
		clm=new CustomListModel();
		listClient.setModel(clm);
		listClient.setBounds(10, 45, 370, 112);
		contentPane.add(listClient);
		
		JLabel lblDanhSchClient = new JLabel("Danh S\u00E1ch client k\u1EBFt n\u1ED1i");
		lblDanhSchClient.setFont(new Font("Candara", Font.BOLD, 14));
		lblDanhSchClient.setBounds(111, 11, 173, 23);
		contentPane.add(lblDanhSchClient);
		
		JLabel lblIpServer = new JLabel("IP server");
		lblIpServer.setFont(new Font("Candara", Font.BOLD, 14));
		lblIpServer.setBounds(551, 47, 66, 17);
		contentPane.add(lblIpServer);
		
		JLabel lblHost = new JLabel("Port");
		lblHost.setFont(new Font("Candara", Font.BOLD, 14));
		lblHost.setBounds(551, 109, 46, 14);
		contentPane.add(lblHost);
		
		txtIP = new JTextField();
		txtIP.setFont(new Font("Candara", Font.BOLD, 14));
		txtIP.setEditable(false);
		txtIP.setBounds(641, 43, 153, 23);
		contentPane.add(txtIP);
		txtIP.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setFont(new Font("Candara", Font.BOLD, 14));
		txtPort.setText("1234");
		txtPort.setBounds(641, 104, 153, 23);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		btnStartListener = new JButton("Start server");
		btnStartListener.setForeground(new Color(255, 255, 255));
		btnStartListener.setBackground(new Color(34, 139, 34));
		btnStartListener.setFont(new Font("Century Gothic", Font.BOLD, 14));
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
				btnStopServer.setEnabled(true);
			}
		});
		btnStartListener.setBounds(506, 170, 124, 49);
		contentPane.add(btnStartListener);
		
		 btnStopServer = new JButton("Stop server");
		 btnStopServer.setEnabled(false);
		 btnStopServer.setBackground(new Color(220, 0, 48));
		 btnStopServer.setForeground(new Color(255, 255, 255));
		 btnStopServer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStopServer.setEnabled(false);
				btnStartListener.setEnabled(true);
				services.disconnectAll();
				
			}
		});
		btnStopServer.setBounds(664, 170, 111, 49);
		contentPane.add(btnStopServer);
		
		JButton btnGiFilen = new JButton("Send file to client");
		btnGiFilen.setForeground(Color.WHITE);
		btnGiFilen.setBackground(new Color(87, 55, 138));
		btnGiFilen.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnGiFilen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listClient.getSelectedIndex()==-1) {
					txtLog.setText(txtLog.getText()+"Chưa chọn client trong danh sách!\n");
					return;
				}
				services.sendFileWithClient(clm.getElementAt(listClient.getSelectedIndex()).toString());
			}
		});
		btnGiFilen.setBounds(207, 170, 173, 49);
		contentPane.add(btnGiFilen);
		
		JButton btnNgtKtNi = new JButton("Disconnect with client");
		btnNgtKtNi.setForeground(new Color(255, 255, 255));
		btnNgtKtNi.setBackground(new Color(255, 153, 51));
		btnNgtKtNi.setFont(new Font("Century Gothic", Font.BOLD, 14));
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
		btnNgtKtNi.setBounds(10, 169, 185, 51);
		contentPane.add(btnNgtKtNi);
		
		txtLog = new JTextPane();
		txtLog.setFont(new Font("Candara", Font.BOLD, 14));
		txtLog.setBounds(10, 275, 784, 294);
		contentPane.add(txtLog);
		
		JLabel lblLog = new JLabel();
		lblLog.setBounds(355, 250, 46, 14);
		contentPane.add(lblLog);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(392, 11, 147, 112);
		
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(MainUI.class.getResource("/model/Managed-File-Transfer-Market.jpg")).getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT)));
		
		contentPane.add(lblNewLabel);
	}
}

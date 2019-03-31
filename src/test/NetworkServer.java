package test;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import network.MainServer;
import network.serverEvents.ClientRequestHandler;
import network.serverEvents.DataCodex;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NetworkServer {

	private JFrame frmNetworkTesting;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel_1;
	private JTextArea textArea;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainServer server=new MainServer();
					server.startServer();
					
					NetworkServer window = new NetworkServer();
					window.frmNetworkTesting.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NetworkServer() {
		initialize();
		
		MainServer.server.addClientRequestHandler("test_NetworkServer", new ClientRequestHandler(){

			@Override
			public void onClientRequest(DataCodex code) {
				// TODO Auto-generated method stub
				textArea.append("*** New Incoming message ***\n");
				ArrayList<String> keys=code.getKeys();
				for(int i=0;i<keys.size();i++)
				{
					textArea.append(" '"+keys.get(i)+"' = '"+code.get(keys.get(i))+"'\n");
				}
				textArea.append("--- End of message ---\n");
			}
			
		});
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNetworkTesting = new JFrame();
		frmNetworkTesting.setTitle("network testing");
		frmNetworkTesting.setBounds(100, 100, 450, 499);
		frmNetworkTesting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmNetworkTesting.getContentPane().add(panel, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setText("192.168.1.121");
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("8082");
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton);
		
		panel_1 = new JPanel();
		frmNetworkTesting.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				textArea.setText("");
			}
		});
		textArea.setAutoscrolls(true);
		textArea.setText("                                                   \n                                   \n                                 \n        \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		panel_1.add(textArea);
		
		btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		panel_1.add(btnNewButton_1);
	}

}

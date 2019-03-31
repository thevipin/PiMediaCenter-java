package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import compounds.VButton;
import compounds.VPanel;

import java.awt.Panel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JDesktopPane;

public class Remoter {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Remoter window = new Remoter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Remoter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JTextArea txtrAnyEventHere;
	private void initialize() {
		frame = new JFrame();		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 1,alignx center,aligny top");
		VButton btnLeft=new VButton("Left");
		
		VButton btnRight=new VButton("Right");
		
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "cell 0 2,alignx center,aligny baseline");
		VButton btnDown=new VButton("Down");
		
		
		Panel panel_3 = new Panel();
		panel.add(panel_3, "cell 0 0,alignx center");
		VButton btnUp=new VButton("Up");		
		txtrAnyEventHere = new JTextArea();
		txtrAnyEventHere.setText("****Any Event here*****");
		frame.getContentPane().add(txtrAnyEventHere, BorderLayout.EAST);	
		btnLeft.Navigate.add(btnUp,btnDown, btnLeft,btnRight);		
		btnUp.Navigate.add(btnUp,btnDown, btnLeft,btnRight);
		btnDown.Navigate.add(btnUp,btnDown, btnLeft,btnRight);
		btnRight.Navigate.add(btnUp,btnDown, btnLeft,btnRight);
		panel_3.add(btnUp);
		panel_2.add(btnDown);
		panel_1.add(btnLeft);
		panel_1.add(btnRight);
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.WEST);
		
		VPanel panel_5 = new VPanel();
		panel_4.add(panel_5);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(Color.WHITE);
		panel_5.add(lblNewLabel);
		
		VPanel panel_6 = new VPanel();
		panel_4.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(Color.WHITE);
		panel_6.add(lblNewLabel_1);
		
		JDesktopPane desktopPane = new JDesktopPane();
		panel_4.add(desktopPane);
		Internalfarme win=new Internalfarme();
		win.setVisible(true);
		desktopPane.add(win);
	}
}

package test;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JRootPane;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Internalfarme extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2646581514899719168L;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Internalfarme frame = new Internalfarme();
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
	public Internalfarme() {
		setClosable(true);
		setRootPaneCheckingEnabled(false);
		setBorder(null);
		getContentPane().setBackground(Color.PINK);
		
		textField = new JTextField();
		getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		setBounds(100, 100, 450, 300);
		
		putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
	    this.setBorder(null);

	}

}

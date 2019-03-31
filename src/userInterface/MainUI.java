
package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainUI {

	private JFrame frmPiMediaCenter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmPiMediaCenter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		frmPiMediaCenter = new JFrame();
		frmPiMediaCenter.setResizable(false);
		frmPiMediaCenter.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				TabUI.tabControl_Video.requestFocus();
			}
		});
		frmPiMediaCenter.getContentPane().setLayout(new CardLayout(0, 0));
		
		JDesktopPane desktopPane = new JDesktopPane();
		TabUI tabUi=new TabUI();
		
		
		tabUi.setNormalBounds(new Rectangle(100, 101, 574, 444));
		tabUi.setVisible(true);
		tabUi.setOpaque(false);
		desktopPane.setBackground(new Color(0, 0, 0));
		desktopPane.setLayout(new BorderLayout(0, 0));
		desktopPane.add(tabUi, BorderLayout.CENTER);
		frmPiMediaCenter.getContentPane().add(desktopPane, "name_29157294796377");
		desktopPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tabUi, tabUi.getContentPane()}));
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		frmPiMediaCenter.getContentPane().add(lblNewLabel, "name_29157272428978");
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/Gray-plain-website-background.jpg")));	
		
		frmPiMediaCenter.setTitle("Pi Media Center");
		frmPiMediaCenter.setBounds(0, 0, width,height);
		frmPiMediaCenter.setUndecorated(true);
		frmPiMediaCenter.setVisible(true);
		frmPiMediaCenter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPiMediaCenter.setExtendedState(JFrame.MAXIMIZED_BOTH);
	
	}
	public void setPanel(int type)
	{
		switch(type)
		{
		case 1:
			
		}
	}
	
	
}

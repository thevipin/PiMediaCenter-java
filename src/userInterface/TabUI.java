package userInterface;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//   import java.awt.CardLayout;ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
import java.beans.PropertyVetoException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import compounds.VButton;
import compounds.VDesktopPane;
import compounds.VTabControl;

public class TabUI extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8082271132556552940L;
	private JPanel LeftTab_PanelContain;
	private AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	public static Object TabRight;
	static VDesktopPane desktopPane_Main;  
	static VButton btnShutdown;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabUI frame = new TabUI();
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
	public static VTabControl tabControl_Video,tabControl_Usb,tabControlSettings;
	public TabUI() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				tabControl_Video.requestFocus();
			}
		});
		//setBounds(100, 100, 574, 444);
		
		JPanel panel_LeftTab = new JPanel();
		panel_LeftTab.setForeground(Color.BLACK);
		panel_LeftTab.setBorder(null);
		panel_LeftTab.setBackground(new Color(51, 51, 51));		
		getContentPane().add(panel_LeftTab, BorderLayout.WEST);		
		panel_LeftTab.setLayout(new BorderLayout(0, 0));
		
		
		JPanel LeftTab_PanelHeading = new JPanel();
		LeftTab_PanelHeading.setOpaque(false);
		LeftTab_PanelHeading.setBorder(new EmptyBorder(25, 0, 25, 0));
		panel_LeftTab.add(LeftTab_PanelHeading, BorderLayout.NORTH);
		LeftTab_PanelHeading.setLayout(new BorderLayout(0, 0));
		
		
		JLabel labelHeading = new JLabel("");
		labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
		labelHeading.setIcon(new ImageIcon(getClass().getResource("/1487080306_xing.png")));
		labelHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
		LeftTab_PanelHeading.add(labelHeading, BorderLayout.CENTER);
		
		JLabel labelText = new JLabel("   Pi Media Center    ");
		labelText.setHorizontalAlignment(SwingConstants.CENTER);
		labelText.setForeground(Color.WHITE);
		labelText.setFont(new Font("SansSerif", Font.BOLD, 22));
		
		LeftTab_PanelHeading.add(labelText, BorderLayout.SOUTH);
		
		LeftTab_PanelContain = new JPanel();
		LeftTab_PanelContain.setOpaque(false);
		LeftTab_PanelContain.setBorder(null);
		panel_LeftTab.add(LeftTab_PanelContain, BorderLayout.CENTER);
		GridBagLayout gbl_LeftTab_PanelContain = new GridBagLayout();
		gbl_LeftTab_PanelContain.columnWidths = new int[]{286, 0};
		gbl_LeftTab_PanelContain.rowHeights = new int[]{35, 35, 35, 35, 0, 0, 0, 0, 0, 0};
		gbl_LeftTab_PanelContain.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_LeftTab_PanelContain.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		LeftTab_PanelContain.setLayout(gbl_LeftTab_PanelContain);
		
		
		tabControl_Video = new VTabControl("Videos",new ImageIcon(this.getClass().getResource("/1487133368_Video.png")) );		
		tabControl_Video.TextLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		
		FlowLayout fl_tabControl_Video = (FlowLayout) tabControl_Video.getLayout();
		fl_tabControl_Video.setAlignment(FlowLayout.LEFT);
		
		
		
		tabControl_Video.TextLabel.setText(" Videos");
		tabControl_Video.TextLabel.setHorizontalAlignment(SwingConstants.LEFT);
		tabControl_Video.ImgLabel.setHorizontalAlignment(SwingConstants.LEFT);
		tabControl_Video.setBorder(new EmptyBorder(0, 25, 0, 0));
		tabControl_Video.setBackground(new Color(51, 51, 51));
		
		
		
		GridBagConstraints gbc_tabControl_Video = new GridBagConstraints();
		gbc_tabControl_Video.fill = GridBagConstraints.BOTH;
		gbc_tabControl_Video.insets = new Insets(0, 0, 5, 0);
		gbc_tabControl_Video.gridx = 0;
		gbc_tabControl_Video.gridy = 0;
		LeftTab_PanelContain.add(tabControl_Video, gbc_tabControl_Video);
		
		tabControl_Usb = new VTabControl("USB Media", new ImageIcon(this.getClass().getResource("/1487133188_39_TV.png")));
		tabControl_Usb.TextLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		tabControl_Usb.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					UsbMenu();
				}
			}
		});
		FlowLayout fl_tabControl_Usb = (FlowLayout) tabControl_Usb.getLayout();
		fl_tabControl_Usb.setAlignment(FlowLayout.LEFT);
		tabControl_Usb.TextLabel.setText(" USB Media");
		tabControl_Usb.TextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tabControl_Usb.ImgLabel.setHorizontalAlignment(SwingConstants.LEFT);
		tabControl_Usb.setBorder(new EmptyBorder(0, 25, 0, 0));
		tabControl_Usb.setBackground(new Color(51, 51, 51));
		tabControl_Usb.setAlignmentY(0.0f);
		GridBagConstraints gbc_tabControl_Usb = new GridBagConstraints();
		gbc_tabControl_Usb.fill = GridBagConstraints.BOTH;
		gbc_tabControl_Usb.insets = new Insets(0, 0, 5, 0);
		gbc_tabControl_Usb.gridx = 0;
		gbc_tabControl_Usb.gridy = 1;
		LeftTab_PanelContain.add(tabControl_Usb, gbc_tabControl_Usb);
		
		tabControlSettings = new VTabControl("Settings", new ImageIcon(this.getClass().getResource("/1487135488_Working_Tools_2.png")));
		tabControlSettings.TextLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		FlowLayout flowLayout_3 = (FlowLayout) tabControlSettings.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		tabControlSettings.TextLabel.setText(" Settings");
		tabControlSettings.TextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tabControlSettings.ImgLabel.setHorizontalAlignment(SwingConstants.LEFT);
		tabControlSettings.setBorder(new EmptyBorder(0, 25, 0, 0));
		tabControlSettings.setBackground(new Color(51, 51, 51));
		tabControlSettings.setAlignmentY(0.0f);
		GridBagConstraints gbc_tabControlSettings = new GridBagConstraints();
		gbc_tabControlSettings.insets = new Insets(0, 0, 5, 0);
		gbc_tabControlSettings.anchor = GridBagConstraints.NORTH;
		gbc_tabControlSettings.fill = GridBagConstraints.HORIZONTAL;
		gbc_tabControlSettings.gridx = 0;
		gbc_tabControlSettings.gridy = 2;
		LeftTab_PanelContain.add(tabControlSettings, gbc_tabControlSettings);
		
		JPanel LeftTab_PanelTools = new JPanel();
		LeftTab_PanelTools.setOpaque(false);
		GridBagConstraints gbc_LeftTab_PanelTools = new GridBagConstraints();
		gbc_LeftTab_PanelTools.fill = GridBagConstraints.BOTH;
		gbc_LeftTab_PanelTools.gridx = 0;
		gbc_LeftTab_PanelTools.gridy = 8;
		LeftTab_PanelContain.add(LeftTab_PanelTools, gbc_LeftTab_PanelTools);
		LeftTab_PanelTools.setLayout(new BorderLayout(0, 0));
		
		btnShutdown = new VButton("");
		btnShutdown.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					TurnOff();
			}
		});
		
		btnShutdown.setText("TURN OFF");
		LeftTab_PanelTools.add(btnShutdown, BorderLayout.SOUTH);
		
		JPanel panel_RightTab = new JPanel();
		getContentPane().add(panel_RightTab, BorderLayout.CENTER);
		panel_RightTab.setLayout(new BorderLayout(0, 0));
		
		VDesktopPane desktopPane_Clock = new VDesktopPane();
		
		ClockWeather clock=new ClockWeather();
		clock.setOpaque(false);
		clock.setVisible(true);
		desktopPane_Clock.setLayout(new BorderLayout(0, 0));
		desktopPane_Clock.add(clock, BorderLayout.CENTER);
		desktopPane_Clock.setOpaque(false);
		panel_RightTab.add(desktopPane_Clock, BorderLayout.NORTH);
		
		desktopPane_Main = new VDesktopPane();
		panel_RightTab.add(desktopPane_Main, BorderLayout.CENTER);
		desktopPane_Main.setOpaque(false);
		//
		
		//
		VideoMenu();
		//UsbMenu();
		tabControl_Video.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					VideoMenu();
				}
			}
		});
		 setNavi();
		//		
		/*tabControl_Video.Navigate.add(tabControlSettings, tabControl_Music, desktopPane_Main, TabRight);
		tabControl_Music.Navigate.add(tabControl_Video, tabControl_Usb, desktopPane_Main, TabRight);
		tabControl_Usb.Navigate.add(tabControl_Music, tabControl_youtube, desktopPane_Main, TabRight);
		tabControl_youtube.Navigate.add(tabControl_Usb, tabControlSettings, desktopPane_Main, TabRight);
		tabControlSettings.Navigate.add(tabControl_youtube, btnShutdown, desktopPane_Main, TabRight);
		btnShutdown.Navigate.add(tabControlSettings, tabControl_Video, desktopPane_Main, TabRight);*/
		//desktopPane_Main.Navigation.add(tabControl_Video, tabControl_Video, tabControl_Video, tabControl_Video);
		//		
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
	    this.setBorder(null);
	    this.setOpaque(false);
	    tabControl_Video.setRequestFocusEnabled(true);
	    //tab
	    tabControl_Video.requestFocus();
	    try {
			this.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//public void Navigation()
	 @Override
	   public void paint(Graphics g) {
	      //Graphics2D g2 = (Graphics2D) g;
	      //g2.setComposite(comp);
	      super.paint(g);
	   }
	 public void setAlpha(float alpha) {
	      comp = comp.derive(alpha);
	   }
	 public void VideoMenu()
	 {
		 desktopPane_Main.removeAll();
		 desktopPane_Main.repaint();
		 VideoMenu menu=new VideoMenu();		
		 menu.setVisible(true);
		 desktopPane_Main.setLayout(new BorderLayout(0, 0));
		 menu.setOpaque(false);
		 desktopPane_Main.add(menu, BorderLayout.CENTER);
		 TabRight=VideoMenu.Elements;
		 setNavi();
		 desktopPane_Main.revalidate();
		 desktopPane_Main.repaint();
	 }
	 public void UsbMenu()
	 {
		 desktopPane_Main.removeAll();
		 desktopPane_Main.repaint();
		 UsbMenu menu=new UsbMenu();		
		 menu.setVisible(true);
		 desktopPane_Main.setLayout(new BorderLayout(0, 0));
		 menu.setOpaque(false);
		 desktopPane_Main.add(menu, BorderLayout.CENTER);
		 TabRight=UsbMenu.Elements;
		 setNavi();
		 desktopPane_Main.revalidate();
		 desktopPane_Main.repaint();
	 }
	 public static  void setNavi()
	 {
		 tabControl_Video.Navigate.add(btnShutdown, tabControl_Usb, null, TabRight);
		 tabControl_Usb.Navigate.add(tabControl_Video,tabControlSettings , null, TabRight);
		 tabControlSettings.Navigate.add(tabControl_Usb, btnShutdown, null, TabRight);
		 btnShutdown.Navigate.add(tabControlSettings, tabControl_Video, null, TabRight);
	 }
	 public void TurnOff()
	 {
		 ProcessBuilder pb = new ProcessBuilder("bash", "-c", "sudo halt");
		 try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}

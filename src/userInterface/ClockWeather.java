package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dataSaver.SharedPreferences;
import javax.swing.border.EmptyBorder;

public class ClockWeather extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2199845254549068260L;
	JLabel lblTime;
	JLabel lblDate;
	SharedPreferences Preferences;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClockWeather frame = new ClockWeather();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Thread Clock=new Thread(){
		public void run()
		{
			try
			{							
				while(true)
				{
					DateFormat df = new SimpleDateFormat(Preferences.getDateFormat());
					Date dateobj = new Date();
					lblDate.setText(df.format(dateobj));
					DateFormat tf = new SimpleDateFormat(Preferences.getTimeFormat());									
					lblTime.setText(tf.format(dateobj));
					Thread.sleep(DateFormat.SECOND_FIELD*1000);
				}
			}
			catch(Exception e)
			{
				System.out.println("Clock Stoped : "+e.getMessage());				
			}
		}
	};
	//private JEditorPane editorPane;
	private JPanel panelClockBack;
	private JLabel label;

	/**
	 * Create the frame.
	 */
	public ClockWeather() {
		Preferences=new SharedPreferences();
		setBackground(new Color(51, 51, 51));
		getContentPane().setForeground(new Color(51, 51, 51));
		
		JPanel panel_Clock = new JPanel();
		panel_Clock.setOpaque(false);
		getContentPane().add(panel_Clock, BorderLayout.EAST);
		panel_Clock.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelClockBack = new JPanel();		
		panelClockBack.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_Clock.add(panelClockBack);
		panelClockBack.setOpaque(false);
		panelClockBack.setSize(WIDTH, 250);
		panelClockBack.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblDate = new JLabel("DD-MM-YYYY");
		panelClockBack.add(lblDate);
		lblDate.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDate.setForeground(Color.WHITE);
		
		lblTime = new JLabel("HH:MM TT");
		panelClockBack.add(lblTime);
		
		lblTime.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 28));
		lblTime.setForeground(Color.WHITE);
		
		label = new JLabel("                   ");
		panel_Clock.add(label);
		
		/*editorPane = new JEditorPane();
		try {
			editorPane.setPage("http://www.accuweather.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().add(editorPane, BorderLayout.CENTER);*/
		
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
	    this.setBorder(null);
	    this.setOpaque(false);
	    Clock.start();
	}

}

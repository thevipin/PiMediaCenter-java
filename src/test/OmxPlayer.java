package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import player.OmxPlayerControl;

import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OmxPlayer {

	private JFrame frame;
	public static OmxPlayer window;
	public static Runnable runnable =new Runnable() {
		public void run() {
			try {
				window = new OmxPlayer();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(runnable);
	}

	/**
	 * Create the application.
	 */
	public OmxPlayer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 786, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		OmxPlayerControl omxplayer = new OmxPlayerControl();
		
		frame.getContentPane().add(omxplayer, BorderLayout.CENTER);
		
		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		TextField textField = new TextField();
		textField.setText("/home/pi/Angry.Birds.2016.720p.BRRip.900MB.MkvCage.mkv");
		Button button = new Button("Play");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				omxplayer.omxplayer.Playlist.add(textField.getText());
				omxplayer.Play();
			}
		});		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					omxplayer.omxplayer.stop();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Button button_8 = new Button("H");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					omxplayer.omxplayer.HideSubtitles();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(button_8);
		
		Button button_6 = new Button("<");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					omxplayer.omxplayer.Rewind();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(button_6);
		panel.add(textField);
		panel.add(button);
		
		Button button_1 = new Button("Pause");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					omxplayer.omxplayer.Pause();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		TextField textField_1 = new TextField();
		textField_1.setText("***********");
		
		
		panel.add(textField_1);
		panel.add(button_1);
		Button button_2 = new Button("ErrorList");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(omxplayer.omxplayer.ErrorList);
				JOptionPane.showMessageDialog(null, omxplayer.omxplayer.ErrorList);
			}
		});
		panel.add(button_2);
		
		Button button_3 = new Button("+");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					omxplayer.omxplayer.VolumeUp();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(button_3);
		
		Button button_4 = new Button("-");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					omxplayer.omxplayer.VolumeDown();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(button_4);
		
		Button button_5 = new Button(">");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					omxplayer.omxplayer.FastForward();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(button_5);
		
		Button button_7 = new Button("S");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					omxplayer.omxplayer.ShowSubtitles();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(button_7);
	}

}

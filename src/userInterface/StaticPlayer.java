package userInterface;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import network.PlayerRemoteController;
import network.PlayerRemoteControllerEvents;
import network.serverEvents.DataCodex;
import player.OMXPlayer;
import player.OmxPlayerEvents;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class StaticPlayer {

	private JFrame frame;

	private static PlayerRemoteController Remoter; 
	private static OMXPlayer omxplayer;
	public static StaticPlayer window;
	public static String FileName;
	/**
	 * Launch the application.
	 */
	public static void RunPlayer(String src,String name) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					omxplayer=new OMXPlayer();
					FileName=name;
					omxplayer.Playlist.add(src);
					
					omxplayer.setOmxEvents(new OmxPlayerEvents(){

						@Override
						public boolean onPlay(String src) {
							try {
								DataCodex code=new DataCodex("");
								
								code.put("src", src);
								//if(omxplayer.IsStreaming())
									code.put("name",FileName);
								Remoter.Play(code);
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return true;
						}

						@Override
						public void onStop() {
							// TODO Auto-generated method stub
							try {
								Remoter.Stop();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							window.frame.dispose();
						}

						@Override
						public void onPause() {
							try {
								Remoter.Pause();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						@Override
						public void onResume() {
							// TODO Auto-generated method stub
							try {
								Remoter.Resume();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						@Override
						public void onDecreaseSpeed() {
							try {
								Remoter.DecreaseSpeed();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						@Override
						public void onIncreaseSpeed() {
							try {
								Remoter.IncreaseSpeed();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						@Override
						public void onRewind() {
							try {
								Remoter.Rewind();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						@Override
						public void onFastForward() {
							try {
								Remoter.FastForward();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						@Override
						public void onShowSubtitles() {
							try {
								Remoter.ShowSubtitles();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						@Override
						public void onHideSubtitles() {
							try {
								Remoter.HideSubtitles();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					window = new StaticPlayer();
					window.frame.setVisible(true);
					omxplayer.Play();
					Remoter.sentInfo(FileName, false);
					System.out.println("OMXPLAYER PLAYING : "+FileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void InitPlayer()
	{
		//omxplayer=new OMXPlayer();
		try {
			Remoter=new PlayerRemoteController();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Remoter.addEvents(new PlayerRemoteControllerEvents(){

			@Override
			public void onPlay(DataCodex code) {
				// TODO Auto-generated method stub
				RunPlayer(code.get("src"),code.get("name"));
			}		

			@Override
			public void onStop() {
				// TODO Auto-generated method stub
				try {
					omxplayer.stop();
					window.frame.dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onConnected() {
				// TODO Auto-generated method stub
				try {
					if(omxplayer.Playlist.size()>0)
					Remoter.sentInfo(FileName,omxplayer.IsPause);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}

			@Override
			public void onWriteToOutputcmd(char cmd) {
				// TODO Auto-generated method stub
				try {
					omxplayer.WriteOutputToCmd(cmd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(cmd);
					e.printStackTrace();
				}

			}

			@Override
			public void onPause() {
				// TODO Auto-generated method stub

				try {
					omxplayer.Pause();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}

			@Override
			public void onResume() {
				// TODO Auto-generated method stub

				try {
					omxplayer.Resume();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onDecreaseSpeed() {
				// TODO Auto-generated method stub
				try {
					omxplayer.DecreaseSpeed();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onIncreaseSpeed() {
				// TODO Auto-generated method stub
				try {
					omxplayer.IncreaseSpeed();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onRewind() {
				// TODO Auto-generated method stub
				try {
					omxplayer.Rewind();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFastForward() {
				// TODO Auto-generated method stub
				try {
					omxplayer.FastForward();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onShowSubtitles() {
				// TODO Auto-generated method stub
				try {
					omxplayer.ShowSubtitles();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onHideSubtitles() {
				// TODO Auto-generated method stub
				try {
					omxplayer.HideSubtitles();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onSendInfo() {
				// TODO Auto-generated method stub
				try {
					Remoter.sentInfo(FileName,omxplayer.IsPause);
				} catch (NullPointerException|IOException e) {
					// TODO Auto-generated catch block
					
					//e.printStackTrace();
				}
			}

			@Override
			public void onSeek10Back() {
				// TODO Auto-generated method stub
				omxplayer.Seek10back();
			}

			@Override
			public void onSeek10For() {
				// TODO Auto-generated method stub
				omxplayer.Seek10for();
			}

		});
	}

	/**
	 * Create the application.
	 */
	public StaticPlayer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		
		JLabel lblPleaseWait = new JLabel("Please Wait...");
		lblPleaseWait.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblPleaseWait.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseWait.setForeground(Color.WHITE);
		frame.getContentPane().add(lblPleaseWait, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				try {
					omxplayer.WriteOutputToCmd(arg0.getKeyChar());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.setTitle("vipin OMXplayer");
		frame.setBounds(0, 0, width, height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setBackground(Color.BLACK);
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

}

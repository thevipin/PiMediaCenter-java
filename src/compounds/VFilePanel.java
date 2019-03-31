package compounds;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import userInterface.PopupMenuForm;
import userInterface.StaticPlayer;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class VFilePanel extends VPanel {
	JLabel lblFileName,lblImg,lblFileName2;
	int FType=0;
	File IoFile;
	public VFilePanel() {
		super();
		addMouseListener(onMouse);
		addKeyListener(onClick);
		if(FType==0)
		{
			
		}
		//this.setBackground(this.getParent().getBackground());
		setLayout(new BorderLayout(0, 0));
		//this.setForeground(this.getParent().getForeground());
		this.setOpaque(true);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.addMouseListener(onMouse);
		panel.addKeyListener(onClick);
		add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{242, 0};
		gbl_panel.rowHeights = new int[]{0, 15, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblFileName = new JLabel("0123456789012345678901234567.89");
		lblFileName.addMouseListener(onMouse);
		lblFileName.addKeyListener(onClick);
		GridBagConstraints gbc_lblFileName = new GridBagConstraints();
		gbc_lblFileName.insets = new Insets(0, 0, 5, 0);
		gbc_lblFileName.gridx = 0;
		gbc_lblFileName.gridy = 0;
		panel.add(lblFileName, gbc_lblFileName);
		lblFileName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFileName.setForeground(Color.WHITE);
		lblFileName.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblFileName2 = new JLabel("");
		lblFileName2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFileName2.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileName2.setForeground(Color.WHITE);
		lblFileName2.addMouseListener(onMouse);
		lblFileName2.addKeyListener(onClick);
		GridBagConstraints gbc_lblFileName2 = new GridBagConstraints();
		gbc_lblFileName2.gridx = 0;
		gbc_lblFileName2.gridy = 1;
		panel.add(lblFileName2, gbc_lblFileName2);
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.addMouseListener(onMouse);
		panel_1.addKeyListener(onClick);
		add(panel_1, BorderLayout.CENTER);		
		
		lblImg = new JLabel("");
		lblImg.addMouseListener(onMouse);
		lblImg.addKeyListener(onClick);
		panel_1.add(lblImg);
		
		lblImg.setVerticalAlignment(SwingConstants.TOP);
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setBackground(Color.WHITE);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 9045474451803250947L;
	private JPanel panel_1;
	private DirctoryCallBack CallBack;
	
	public KeyAdapter onClick= new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent arg0) {
			KeyEvent key=arg0;
			if(key.getKeyCode()==KeyEvent.VK_ENTER)
			{
				Excute();
			}
			else if(key.getKeyCode()==525||key.getKeyCode()==KeyEvent.VK_INSERT)
			{
				//JOptionPane.showMessageDialog(null, key.getKeyCode());
				GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
				int width = gd.getDisplayMode().getWidth()/2;
				int height = gd.getDisplayMode().getHeight()/2;
				PopupMenuForm.show(IoFile.getAbsolutePath(), lblFileName.getText(), FType,width,height);
			}
			System.out.println("Last Pressed Key in VFilePanel is : "+key.getKeyCode());
		}
	};
	public MouseAdapter onMouse=new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getButton()==1)
					Excute();
			else
			{
				GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
				int width = gd.getDisplayMode().getWidth()/2;
				int height = gd.getDisplayMode().getHeight()/2;
				PopupMenuForm.show(IoFile.getAbsolutePath(), lblFileName.getText(), FType,width,height);
			}
		}
	};
	public Boolean setFile(File file ,int type)
	{
		IoFile=file;
		FType=type;
		Boolean isFile=true;
		Boolean isVideo=true;
		if(IoFile.isDirectory())
		{
			
			if(IoFile.isDirectory())
			{String Name=file.getName();
			if(Name.length()>30)
			{
				lblFileName.setText(Name.substring(0,30));
				lblFileName2.setText(Name.substring(30,((Name.length()>70)?(70):(Name.length()))));
			}
			}
		}
		if(FType==0 || FType==1 || FType==2)
		{			
			String Name=file.getName();
			String extention;
			
			try
			{
				//if(!IoFile.isDirectory())
				//{
					extention=Name.substring(Name.lastIndexOf("."));
					switch(extention.toLowerCase())
					{
						case ".mkv": break;
						case ".flv":break;
						case ".vob":break;
						case ".avi":break;
						case ".mov":break;
						case ".qt":break;
						case ".wmv":break;
						case ".mpg":break;
						case ".mpeg":break;
						case ".mp4":break;
						default:if(FType==0) return false;
						isVideo=false;
					}
				}
			//}
			catch(Exception e)
			{
				if(FType==0)
					return false;
				isFile=false;
				isVideo=false;
			}
			
			String NameOnly=Name.substring(0,(IoFile.isDirectory())?(Name.length()):(Name.lastIndexOf(".")));
			if(NameOnly.length()>30)
			{
				NameOnly=Name.substring(0,30)+"\n";
				lblFileName2.setText(Name.substring(30,((Name.length()>60)?(30):((IoFile.isDirectory())?(Name.length()):(Name.lastIndexOf("."))))));
			}
			
			if(isFile && isVideo)
			{
				lblImg.setIcon(new ImageIcon(this.getClass().getResource("/VideoMenu.png")));
				lblFileName.setText(NameOnly);
			}
			else if(FType==1 && IoFile.isDirectory())
			{
				lblImg.setIcon(new ImageIcon(this.getClass().getResource("/file-directory.png")));
				lblFileName.setText(NameOnly);
			}
			else if(FType==2 && IoFile.isDirectory())
			{
				lblImg.setIcon(new ImageIcon(this.getClass().getResource("/arrow-back.png")));
				lblFileName.setText("Back");
			}
			else
			{
				return false;
			}
			
		}
		return true;
		
	}
	public void Excute()
	{
		if(FType==0)
		{
			StaticPlayer.RunPlayer(IoFile.getAbsolutePath(), IoFile.getName().substring(0,IoFile.getName().lastIndexOf('.')));
		}
		else if(FType==1||FType==2)
		{
			CallBack.onBrowseNext(IoFile.getAbsolutePath());
		}
	}
	public void AddActionForClick(DirctoryCallBack callbacker)
	{
		CallBack=callbacker;
	}
	//public setFileName
	
}

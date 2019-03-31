package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import compounds.RenameController;
import compounds.VButton;
import dataSaver.SharedPreferences;

public class PopupMenuForm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	static String Filesrc;
	static String FileName="No File Selected";
	static int FType=0;
	static PopupCallBack callBack;
	 static int X,Y;
	 VButton btnDelete,btnCpyTo,btnClose;	 
	 JPanel panel;
	 private RenameController RenamePanel;
	 private JPanel panel_2;	
	 JLabel lblFileName;
	 long val=0;	
	 //private RenameController RenamePanel;
	public static void AddAction(PopupCallBack back)
	{
		callBack=back;
	}
	public static void show(String filesrc,String fileName,int type,int x,int y) {
		FileName=fileName;
		Filesrc=filesrc;
		FType=type;
		X=x;
		Y=y;
		//callBack=callback;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupMenuForm window = new PopupMenuForm();
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
	public PopupMenuForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//File IoFile=new File(Filesrc);
		String Drive="USB";
		switch(FType)
		{
		case 0:
			Drive="USB";
			break;
		case 1:
			Drive="Video";
			break;
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);		
		JPanel panel1 = new JPanel();
		panel1.setForeground(Color.BLACK);
		panel1.setBorder(new LineBorder(new Color(192, 192, 192), 20, true));
		panel1.setBackground(Color.GRAY);
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new BorderLayout(0, 0));		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel1.add(panel_1, BorderLayout.NORTH);		
		lblFileName = new JLabel(FileName);
		panel_1.add(lblFileName);
		lblFileName.setForeground(Color.BLACK);
		lblFileName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileName.setHorizontalAlignment(SwingConstants.CENTER);		
		
		panel_2 = new JPanel();
		panel1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new CardLayout(0, 0));
		panel = new JPanel();
		panel_2.add(panel, "name_164221830220437");
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.GRAY);
		panel.setLayout(new GridLayout(0, 1, 10, 5));
		
		RenamePanel = new RenameController();
		panel.add(RenamePanel);		
		
				RenamePanel.btnNewButton.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						KeyEvent key=arg0;
						if(key.getKeyCode()==KeyEvent.VK_ENTER)				
							Rename();
					}
				});
				RenamePanel.btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Rename();
					}
				});
				RenamePanel.textField.setText("");
				btnDelete = new VButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Delete();
					}
				});
				btnDelete.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						KeyEvent key=arg0;
						if(key.getKeyCode()==KeyEvent.VK_ENTER)
							Delete();
					}
				});
				
				//RenamePanel = new JPanel();
				panel.add(RenamePanel);
				panel.add(btnDelete);
				
				btnCpyTo = new VButton("Copy To "+Drive);
				btnCpyTo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CpyTo();
					}
				});
				btnCpyTo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						KeyEvent key=arg0;
						if(key.getKeyCode()==KeyEvent.VK_ENTER)
							CpyTo();
					}
				});
				panel.add(btnCpyTo);
				
				VButton btnMoveTo = new VButton("Move To "+Drive);
				btnMoveTo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						KeyEvent key=arg0;
						if(key.getKeyCode()==KeyEvent.VK_ENTER)
							MoveTo();
					}
				});
				btnMoveTo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
							MoveTo();
					}
				});
				panel.add(btnMoveTo);
				
				btnClose = new VButton("Close");
				btnClose.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						KeyEvent key=arg0;
						if(key.getKeyCode()==KeyEvent.VK_ENTER)
							Close();
					}
				});
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Close();
					}
				});
				
				panel.add(btnClose);
				RenamePanel.Navigate.add(btnClose, btnClose, null, null);
				btnDelete.Navigate.add(RenamePanel.textField, btnCpyTo, null, null);
				btnCpyTo.Navigate.add(btnDelete, btnMoveTo, null, null);
				btnMoveTo.Navigate.add(btnCpyTo, btnClose, null, null);
				btnClose.Navigate.add(btnMoveTo, RenamePanel.textField, null, null);	
				
				RenamePanel.btnNewButton.Navigate.add(btnClose,btnDelete,RenamePanel.textField, RenamePanel.textField);
				RenamePanel.textField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						switch(e.getKeyCode())
						{
							case KeyEvent.VK_ENTER:RenamePanel.btnNewButton.requestFocus();break;					
							case KeyEvent.VK_UP:btnClose.requestFocus();;break;
							case KeyEvent.VK_DOWN:btnDelete.requestFocus();break;					
						}
					}
				});
		
		/*if(FType!=0)
		{
			btnCpyTo.setVisible(false);
			btnMoveTo.setVisible(false);
		}*/
		
		frame.setType(Type.UTILITY);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setBounds(X-133, Y-150, 266, 300);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		//RenamePanel.setVisible(false);
	}
	void Close()
	{
		frame.dispose();
	}
	void Rename()
	{
		
		File IoFile=new File(Filesrc);
		String newName=Filesrc.substring(0,Filesrc.lastIndexOf('/')+1)+RenamePanel.textField.getText()+IoFile.getName().substring(IoFile.getName().lastIndexOf('.'),IoFile.getName().length());
		if(IoFile.getName()!=newName)
		{
			File NewFile=new File(newName);
			IoFile.renameTo(NewFile);
			//IoFile
		}
		callBack.onComplete();
		frame.dispose();
	}
	void Delete()
	{
		File IoFile=new File(Filesrc);
		IoFile.delete();
		callBack.onComplete();
		frame.dispose();
	}
	void MoveTo()
	{
		File IoFile=new File(Filesrc);
		SharedPreferences pre=new SharedPreferences();
		if(FType==0)			
		{
			File NewFile=new File(pre.getUSBDevicePath()+IoFile.getName());
			moveFileUsingChannel(IoFile,NewFile);
			//frame.dispose();
			
		}
		else if(FType==1)
		{
			File NewFile=new File(pre.getVideoFileLocation()+IoFile.getName());
			moveFileUsingChannel(IoFile,NewFile);
		}
		//Delete();
			
	}
	void CpyTo()
	{
		File IoFile=new File(Filesrc);
		SharedPreferences pre=new SharedPreferences();
		if(FType==0)			
		{
			
			File UsbFile=new File(pre.getUSBDevicePath());
			File NewFile=new File(UsbFile.listFiles()[0].getAbsoluteFile()+"/"+IoFile.getName());
			copyFileUsingChannel(IoFile,NewFile);
			//frame.dispose();
			
		}
		else if(FType==1)
		{
			File NewFile=new File(pre.getVideoFileLocation()+IoFile.getName());
			copyFileUsingChannel(IoFile,NewFile);
		}
	}
	@SuppressWarnings({ "resource" })
	private void copyFileUsingChannel(File source, File dest) {	       
	   Thread th=new Thread(){
		   public void run(){
		try {
	    	
	    	FileChannel sourceChannel = new FileInputStream(source).getChannel();
	        FileChannel destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	        sourceChannel.close();
	        destChannel.close();
	        
	        callBack.onComplete();
	        frame.dispose();
	       }catch(IOException e){
	    	   JOptionPane.showMessageDialog(null,e.getMessage());
	   }
		   }};
		   th.start();
		   lblFileName.setText("File Copy on Progress..");
		   panel.setVisible(false);
		   frame.repaint();
	}
	@SuppressWarnings("resource")
	private void moveFileUsingChannel(File source, File dest) {	       
		   Thread th=new Thread(){
			   
			public void run(){
			try {
		    	
		    	FileChannel sourceChannel = new FileInputStream(source).getChannel();
		        FileChannel destChannel = new FileOutputStream(dest).getChannel();
		        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		        sourceChannel.close();
		        destChannel.close();
		        Delete();
		        callBack.onComplete();
		        frame.dispose();
		       }catch(IOException e){
		    	   JOptionPane.showMessageDialog(null,e.getMessage());
		   }
			   }};
			   th.start();
			   lblFileName.setText("File move on Progress..");
			   panel.setVisible(false);
			   frame.repaint();
			   //frame.setVisible(false);
		}
}

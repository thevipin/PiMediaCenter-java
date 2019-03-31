package userInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import compounds.DirctoryCallBack;
import compounds.VFilePanel;
import dataSaver.SharedPreferences;
import net.miginfocom.swing.MigLayout;

public class UsbMenu extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -826429255709123600L;
	public static String Home;
	public static String CurrentPath;
	JPanel panelContainer;
	JLabel lblLocation;
	public static VFilePanel Elements;	
	JPanel panelBottom;
	SharedPreferences preferance;
	JScrollPane panel;
	private JPanel panelTop;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsbMenu window = new UsbMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public UsbMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
preferance=new SharedPreferences();
		
		panelTop = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setHgap(2);
		flowLayout.setVgap(2);
		getContentPane().add(panelTop, BorderLayout.NORTH);		
		
		lblLocation = new JLabel("New label");
		panelTop.add(lblLocation);
				
		panelContainer = new JPanel();		
		panel = new JScrollPane(panelContainer);
		panelContainer.setLayout(new MigLayout("", "[0][1][2][3]", "[]"));
		getContentPane().add(panel, BorderLayout.CENTER);		
		
		panelBottom = new JPanel();
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		Home=preferance.getUSBDevicePath();
		CreateFileList(Home);	    
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(null);
		this.setOpaque(false);		
		PopupMenuForm.AddAction(new PopupCallBack(){

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				panelContainer.removeAll();
				//panelContainer.repaint();
				Panels.clear();
				CreateFileList(CurrentPath);
				panelContainer.repaint();
				panelContainer.revalidate();
			}});
		
	}
	
	ArrayList<VFilePanel> Panels;
	//		
	VFilePanel panel1;
	public void CreateFileList(String dir)
	{	
		DirctoryCallBack callback=new DirctoryCallBack(){

			@Override
			public void onBrowseNext(String src) {
				// TODO Auto-generated method stub				
				panelContainer.removeAll();
				panelContainer.repaint();
				Panels.clear();
				CreateFileList(src);
				panelContainer.revalidate();
				panelContainer.repaint();
			}
		};
		
		CurrentPath=dir;
		File directory = new File(dir);	
		Panels=new ArrayList<VFilePanel>();
		if(directory.getAbsolutePath().compareTo(Home)>0)
		{
			try{
			panel1=new VFilePanel();
			panel1.AddActionForClick(callback);
			File pfile=directory.getParentFile();
			if(panel1.setFile(pfile,2));
			{				
				Panels.add(panel1);	
			}
			}catch(Exception e){}
		}
		File[] fList = directory.listFiles();		
				
		
		for (int i=0;i<fList.length;i++)
		{
			VFilePanel panel=new VFilePanel();
			panel.AddActionForClick(callback);
			if(panel.setFile(fList[i],(fList[i].isAbsolute())?(1):(0)))
			{				
				Panels.add(panel);		
			}

		}
		createSelectives();
		for(int i=0;i<Panels.size();i++)
			panelContainer.add(Panels.get(i),"cell "+i%4+" "+(int)(i/4));
		Elements=Panels.get(0);		
		Elements.requestFocus();
		TabUI.TabRight=Elements;
		TabUI.setNavi();
		lblLocation.setText(dir);
	}
	public void createSelectives()
	{
		try{
			
			int ItemPerRow=4;
			for(int i=0;i<Panels.size();i++)
			{
				try{
					try{
						int index=i-ItemPerRow;
						Panels.get(i).Navigate.Up=Panels.get(index);
					}catch(Exception e){}
					try{
						int index=i+ItemPerRow;
						Panels.get(i).Navigate.Down=Panels.get(index);
					}catch(Exception e){}
					try{
						Panels.get(i).Navigate.Left=Panels.get(i-1);
					}catch(Exception e){}
					try{
						Panels.get(i).Navigate.Right=Panels.get(i+1);
					}catch(Exception e){}					
				}
				catch(Exception e){System.out.println("Error 3 "+i);}
				if((i)%ItemPerRow==0)
				{
					try{Panels.get(i).Navigate.Left=TabUI.tabControl_Video;}
					catch(Exception e){ System.out.println("Error 1 "+i);}
				}				
				else if((i)%ItemPerRow==3)
				{
					try{Panels.get(i).Navigate.Right=Panels.get(i+1);}
					catch(Exception e){System.out.println("Error 2 "+i);}
				}
			}
		}
		catch(Exception e){}
	}
	

}

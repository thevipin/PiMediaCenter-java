package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import compounds.VFilePanel;
import dataSaver.SharedPreferences;
import net.miginfocom.swing.MigLayout;

public class VideoMenu extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4919750215799455732L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VideoMenu frame = new VideoMenu();
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
	public static VFilePanel Elements;
	JPanel panelContainer;
	JPanel panelBottom;
	SharedPreferences preferance;
	JScrollPane panel;
	public VideoMenu() {
		//setBounds(100, 100, 500, 500);		
		preferance=new SharedPreferences();
		
		panelTop = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setHgap(2);
		flowLayout.setVgap(2);
		getContentPane().add(panelTop, BorderLayout.NORTH);		
		
		//panel.setLayout(new BorderLayout(0, 0));
		panelContainer = new JPanel();
		//panelContainer.setSize(getContentPane().getWidth(), 100);
		//panelContainer.setMaximumSize(getContentPane().getWidth(),3000);
		
		panel = new JScrollPane(panelContainer);
		panelContainer.setLayout(new MigLayout("", "[0][1][2][3]", "[]"));
		getContentPane().add(panel, BorderLayout.CENTER);		
		//panel.getViewport().scrollRectToVisible(focused.getBounds());
		
		panelBottom = new JPanel();
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		CreateFileList();	    
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(null);
		this.setOpaque(false);
		//getContentPane().setLayout(new BorderLayout(0, 0));
		PopupMenuForm.AddAction(new PopupCallBack(){

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				Panels.clear();
				panelContainer.removeAll();				
				CreateFileList();
				panelContainer.repaint();
				panelContainer.revalidate();
				
			}});
	}
	ArrayList<VFilePanel> Panels;
	private JPanel panelTop;
	public void CreateFileList()
	{
		File directory = new File(preferance.getVideoFileLocation());        
		File[] fList = directory.listFiles();		
		Panels=new ArrayList<VFilePanel>();		

		for (int i=0;i<fList.length;i++)
		{
			VFilePanel panel=new VFilePanel();
			if(panel.setFile(fList[i],0))
			{				
				Panels.add(panel);				
			}

		}
		createSelectives();
		for(int i=0;i<Panels.size();i++)
			panelContainer.add(Panels.get(i),"cell "+i%4+" "+(int)(i/4));
		Elements=Panels.get(0);
		/*if(Panels.size()>0)
		{
			//int y=Panels.get(Panels.size()-1).getY()+Panels.get(Panels.size()-1).getHeight();
			//panelContainer.setSize(getContentPane().getWidth(), y+50);
			//panel.getVerticalScrollBar().setMaximum(y);
		}*/
	}
	public void createSelectives()
	{
		try{
			//int MaxWidth=panelContainer.getWidth();
			//int Width=Panels.get(0).getWidth();
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
					//Panels.get(i).Navigate.add(Panels.get(i-ItemPerRow), Panels.get(i+ItemPerRow), Panels.get(i-1), );
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

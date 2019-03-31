package compounds;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VTabControl extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 244879442665689016L;
	public navigate Navigate;
	public JLabel ImgLabel;
	public JLabel TextLabel;
	Color bgcolor=new Color(51, 51, 51);
	Color FocusBgColor=new Color(15,133,165);
	FocusListener onFocus=new FocusListener(){

		@Override
		public void focusGained(FocusEvent arg0) {
			onFocus();				
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			removeFocus();				
		}
		
	};
	MouseListener onMouse=new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			requestFocus();
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	};

	public VTabControl(String text,ImageIcon icon)
	{		
		super();
		Navigate =new navigate();
		this.addKeyListener(Navigate.KeyListner());
		//this.setFocusPainted(false);
		//this.setBorderPainted(false);
		this.setBackground(bgcolor);
		this.setForeground(Color.WHITE);
		this.addFocusListener(onFocus);
		this.addMouseListener(onMouse);
		//img
		ImgLabel=new JLabel("");
		ImgLabel.addFocusListener(onFocus);
		ImgLabel.addMouseListener(onMouse);
		ImgLabel.setIcon(icon);
		this.add(ImgLabel);
		//label
		TextLabel=new JLabel(text);
		TextLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		TextLabel.setForeground(Color.white);		
		TextLabel.addFocusListener(onFocus);
		TextLabel.addMouseListener(onMouse);		
		this.add(TextLabel);
	}
	void onFocus()
	{		
		this.setBackground(FocusBgColor);
	}
	void removeFocus()
	{
		this.setBackground(bgcolor);		
	}
}

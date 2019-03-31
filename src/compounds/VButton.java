package compounds;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class VButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6545072282589504710L;
	public navigate Navigate;
	Color bgcolor=new Color(14, 23, 27);
	Color FocusBgColor=new Color(15,133,165);
	public VButton(String arg0)
	{		
		super(arg0);
		Navigate =new navigate();
		this.addKeyListener(Navigate.KeyListner());
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setBackground(bgcolor);
		this.setForeground(Color.WHITE);	
		this.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				onFocus();				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				removeFocus();				
			}
			
		});
		this.addMouseListener(new MouseListener(){

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

		});
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

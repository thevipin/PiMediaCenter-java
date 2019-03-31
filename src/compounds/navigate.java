package compounds;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
public class navigate {
	public Object Up;
	public Object Down;
	public Object Left;
	public Object Right;
	public void add(Object up, Object down,Object left, Object right)
	{
		Up=up;
		Down=down;
		Left=left;
		Right=right;
	}
	public KeyAdapter KeyListner(){
		return new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode())
				{
				case KeyEvent.VK_UP:
					if(Up!=null){
						try{
						((JComponent) Up).requestFocus();}
						catch(Exception ex){
							((VFilePanel) Up).requestFocus();
						}
						}
					break;
				case KeyEvent.VK_DOWN:
					if(Down!=null){
						try{
						((JComponent) Down).requestFocus();}
						catch(Exception ex){
							((VFilePanel) Down).requestFocus();
						}
						}
				break;
				case KeyEvent.VK_LEFT:
					if(Left!=null){
						try{
						((JComponent) Left).requestFocus();}
						catch(Exception ex){
							((VFilePanel) Left).requestFocus();
						}
						}
					break;
				case KeyEvent.VK_RIGHT:
					if(Right!=null){
						try{
						((JComponent) Right).requestFocus();}
						catch(Exception ex){
							((VFilePanel) Right).requestFocus();
						}
						}
					break;
				}
			}
		};
	}

}

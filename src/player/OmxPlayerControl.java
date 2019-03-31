package player;

import java.awt.Color;
import java.awt.Container;
import java.awt.Panel;

import player.OMXPlayer;

public class OmxPlayerControl extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5123168286210580827L;
	public OMXPlayer omxplayer;
	OmxPlayerEvents events;
	public OmxPlayerControl()
	{
		this.setBackground(Color.BLACK);
		omxplayer=new OMXPlayer();
	}

	public void EventHandler(OmxPlayerEvents event)
	{
		events=event;
	}

	public void Play()
	{
		
		//omxplayer=new OMXPlayer(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		int x=this.getX(),y=this.getY();
		try{
			Container object=this;
			do{
				object=object.getParent();
				x+=object.getX();
				y+=object.getY();			
			}while(object.getParent()!=null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		omxplayer.setLocation(x,y);
		omxplayer.setSize(this.getWidth(),this.getHeight());
		omxplayer.Play();
		
	}
}

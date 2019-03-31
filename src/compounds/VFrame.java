package compounds;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class VFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -544705143016587704L;
	Image BgImage;
	public VFrame(Image img)
	{
		BgImage=img;
	}
	 @Override
	public void paint(Graphics g) {
	    super.paint(g);
	    //
	}

}

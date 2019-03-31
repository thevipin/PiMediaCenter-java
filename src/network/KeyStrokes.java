package network;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import network.serverEvents.ClientRequestHandler;
import network.serverEvents.DataCodex;

public class KeyStrokes {

	public static void Start()
	{
		try{

			MainServer.server.addClientRequestHandler("KeyStrokes", new ClientRequestHandler(){

				@Override
				public void onClientRequest(DataCodex code) {
					try {
						Robot robot	=new Robot();				
						String call=code.get("call");
						//System.out.println("KeyStrokes : "+call);
						int key=KeyEvent.VK_F24;
						switch(call)
						{
						case "keyup": key=KeyEvent.VK_UP;break;
						case "keydown":key=KeyEvent.VK_DOWN;break;
						case "keyleft":key=KeyEvent.VK_LEFT;break;
						case "keyright":key=KeyEvent.VK_RIGHT;break;
						case "keyenter":key=KeyEvent.VK_ENTER;break;
						case "keyback":key=KeyEvent.VK_BACK_SPACE;break;
						case "mouseright":key=KeyEvent.VK_INSERT;break;
						}
						if(key!=KeyEvent.VK_F24)
						{
							robot.keyPress(key);
							
							System.out.println("Key Pressed : "+key);
							robot.keyRelease(key);
						}
						switch(call)
						{
						
						case "text":
							String text=code.get("text");
							for(int i=0;i<text.length();i++)
							{
								char ch=text.charAt(i);
								if(!('A'<=ch&&ch>='Z'))								
									robot.keyPress(KeyEvent.VK_SHIFT);
								robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(text.toLowerCase().charAt(i)));
								robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(text.toLowerCase().charAt(i)));
								if(!('A'<=ch&&ch>='Z'))
									robot.keyRelease(KeyEvent.VK_SHIFT);
							}
							break;
						case "shutdown": System.exit(0);break;
						}
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}		

}

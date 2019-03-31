import java.io.IOException;
import java.net.UnknownHostException;

import network.KeyStrokes;
import network.MainServer;
import userInterface.StaticPlayer;

public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MainServer server=new MainServer();
			server.startServer();
			StaticPlayer.InitPlayer();
			KeyStrokes.Start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//RaspberryPiHome.Show(null);
		//test.Remoter.main(null);
		userInterface.MainUI.main(null);
	}

}

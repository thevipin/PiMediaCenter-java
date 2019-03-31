package network.serverEvents;

import java.io.IOException;
import java.net.Socket;

public interface ClientConnectHandler {
	public void onClientConnected(Socket socket) throws NullPointerException;
	public void onClientDisconnected(Socket socket) throws NullPointerException;
	public void onConnectionFailed(IOException e) throws NullPointerException;
	//public abstract void OnClientRequest(DataCodex codex);
	//public Boolean ClientRequestDemand(String Identifier);
	
}

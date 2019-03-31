package network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import dataSaver.SharedPreferences;
import network.serverEvents.*;

public class MainServer {
	public static Server server;
	static MainServerEvents mainServerEvents;
	/*public static MainServer getDefault()
	{
		return this;
	}*/
	public MainServer() throws UnknownHostException, IOException
	{
		SharedPreferences sharedPreferences=new SharedPreferences();
		server=new Server(sharedPreferences.getMainServerPort());
	}
	public void startServer()
	{
		server.StartListening(new ClientConnectHandler(){

			@Override
			public void onClientConnected(Socket socket) {
				// TODO Who
				//try {
					try{
						mainServerEvents.OnConnected();
					}
					catch(NullPointerException d){

					}
					/*OutputStreamWriter outputStreamWrite = new OutputStreamWriter(socket.getOutputStream());
					outputStreamWrite.write("WHO\n");
					outputStreamWrite.flush();*/
					
				/*} catch (IOException e) {					
					e.printStackTrace();
				}*/
			}

			@Override
			public void onClientDisconnected(Socket socket) {
				// TODO Auto-generated method stub
				mainServerEvents.OnDisconnected();
			}

			@Override
			public void onConnectionFailed(IOException e) {
				// TODO Auto-generated method stub

			}		
		});
		server.addClientRequestHandler("MainServer_startServer", new ClientRequestHandler(){

			@Override
			public void onClientRequest(DataCodex code) {
				mainServerEvents.OnConnectedInfo(code);
				
			}});
	}
	public void stopServer()
	{
		server.StopListening();
	}
			
	
	public void setServerInterface(MainServerEvents events)
	{
		mainServerEvents=events;
	}
	
}

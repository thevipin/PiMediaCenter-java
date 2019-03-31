package network.serverEvents;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import network.serverEvents.ClientConnectHandler;
import java.net.*;
import java.util.ArrayList;

public class Server {
	String port;
	String Ipaddress;
	ArrayList<Socket> socket=new ArrayList<Socket>();
	ServerSocket serverSocket;
	ArrayList<String> Identifiers= new ArrayList<String>();
	ArrayList<ClientRequestHandler> clientRequestHandlers=new ArrayList<ClientRequestHandler>();
	public Boolean isServerRunning=true;
	ClientConnectHandler onClientConnect;
	public void addClientRequestHandler(String identifier,ClientRequestHandler handler)
	{
		handler.putIdentifier(identifier);
		Identifiers.add(identifier);
		clientRequestHandlers.add(handler);
	}
	
	public void onClientConnected(ClientConnectHandler clientConnectHandler){
		onClientConnect=clientConnectHandler;
	}
	//Client 	
	public Server(int port) throws UnknownHostException, IOException{
		serverSocket=new ServerSocket(port);
	}

	public void StartListening(ClientConnectHandler connected){
		onClientConnect=connected;
		connectionThread.start();
	}
	public void StopListening(){
		isServerRunning=false;
	}
	 private void onDataRecieve(int Socketindex,String Data)
	 {
		 DataCodex codex=new DataCodex();
		 codex.Socketindex=Socketindex;
		 codex.putCodex(Data);
		 
		 int index=Identifiers.indexOf(codex.Identifier);
		 clientRequestHandlers.get(index).onClientRequest(codex);
		 //
		 System.out.println("onDataRecieve, from "+socket.get(Socketindex).getInetAddress());
		 /*OutputStreamWriter writer;
		try {
			writer = new OutputStreamWriter(socket.get(Socketindex).getOutputStream());
			writer.write("hai__\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		*/ 
		 
		 ///
		 
	 }
	 public void sentResponse(DataCodex code)
	 {
		 for(int i=0;i<socket.size();i++)
		 {
			 try {
				 if(socket.get(i).isConnected())
				 {
					 OutputStreamWriter writer=new OutputStreamWriter(socket.get(i).getOutputStream());
					 //code.
					 BufferedWriter Bwritter=new BufferedWriter(writer);
					 Bwritter.write(code.getCodex()+"\n");
					 Bwritter.flush();
					 System.out.println("sentResponse, Server sent data to "+socket.get(i).getInetAddress());
				 }
				 else
				 {
					 socket.remove(i);
				 }
			 } catch (Exception e) {
				 // TODO Auto-generated catch block
				 System.out.println("sentResponse Failed to sent Error :"+ e.getMessage());
				 try{
					 if(socket.get(i).isConnected())
					 {
						 System.out.println("Connection status was connected");
						 if(e.getMessage().contains("Connection reset"))
						 {
							 /*socket.get(i).se;						 
							 System.out.println("Connection close");*/							 
						 }
					 }
					 else{
						 System.out.println("Connection status was closed");
						 //socket.remove(i);
						// System.out.println("Client Removed");
					 }

				 }
				 catch(Exception ex)
				 {
					 System.out.println("sentResponse Complete Failed : "+ex.getMessage());
				 }
			 }
		 }
	 }
	//Thread 
	Thread connectionThread=new Thread(){
		public void run(){  
			while(isServerRunning)
			{
				try {				
					Socket tsocket=serverSocket.accept();
					if(socket.contains(tsocket))
						socket.remove(tsocket);
					if(!isServerRunning){
						tsocket.close();
						return;
					}
					System.out.println("Client Connected : Ip : "+tsocket.getInetAddress());
					socket.add(tsocket);
					try
					{
						onClientConnect.onClientConnected(tsocket);
					}
					catch(NullPointerException e){
						
					}
					new Thread(){
						public void run(){
							int i=socket.size()-1;
							BufferedReader reader;
							try {
								reader = new BufferedReader(new InputStreamReader(socket.get(i).getInputStream()));
								while((!socket.get(i).isClosed())&&(socket.get(i).isConnected())){
									//on data request
									try {									
										if(reader.ready())
										{
											String data=reader.readLine();
											onDataRecieve(i,data);
											
										}
									} catch (Exception e) {
										
										e.printStackTrace();
									}
								}
								onClientConnect.onClientDisconnected(socket.get(i));
								System.out.println("Client Disconnected : Ip : "+socket.get(i).getInetAddress());
								socket.get(i).close();
								socket.remove(i);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					}.start();

				} catch (IOException e) {
					onClientConnect.onConnectionFailed(e);
				}
			}
		}
	};

}

package network;

import network.serverEvents.DataCodex;

public interface MainServerEvents {
	public void OnConnected() throws NullPointerException;
	public void OnDisconnected() throws NullPointerException;
	public void OnConnectedInfo(DataCodex data);
}

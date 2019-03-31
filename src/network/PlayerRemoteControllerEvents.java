package network;

import network.serverEvents.DataCodex;

public interface PlayerRemoteControllerEvents {
	public void onPlay(DataCodex code);
	public void onPause();
	public void onStop();
	public void onConnected();
	public void onWriteToOutputcmd(char cmd);	
	public void onResume();
	public void onDecreaseSpeed();
	public void onIncreaseSpeed();
	public void onRewind();
	public void onFastForward();
	public void onShowSubtitles();
	public void onHideSubtitles();
	public void onSendInfo();
	public void onSeek10Back();
	public void onSeek10For();
}

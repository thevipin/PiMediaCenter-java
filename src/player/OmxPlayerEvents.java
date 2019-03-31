package player;

public interface OmxPlayerEvents {
	public boolean onPlay(String src);
	public void onStop();
	public void onPause();
	public void onResume();
	/*public void onVolumeUp();
	public void onVolumeDown();*/
	public void onDecreaseSpeed();	
	public void onIncreaseSpeed();
	public void onRewind();	
	public void onFastForward();	
	public void onShowSubtitles();	
	public void onHideSubtitles();
}

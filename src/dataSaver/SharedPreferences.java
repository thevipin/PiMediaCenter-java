package dataSaver;

public class SharedPreferences {
	IniFile preference;
	int mainServerPort;
	String AudioOutput;
	String DateFormating;
	String TimeFormating;
	String VideoFileLocation;
	String USBPath;
	public SharedPreferences()
	{
		
		DateFormating="dd-MMMM-yyyy";
		TimeFormating="hh:mm aa";
		AudioOutput="both";
		mainServerPort=8082;
		preference=new IniFile("Config");
		if(System.getProperty("os.name").toLowerCase().startsWith("win"))
		{
			VideoFileLocation="D:\\movies\\";//"Z:\\Multimedia\\Video Songs\\English (ALL)\\";
			USBPath="E:\\";
		}
		else
		{
			VideoFileLocation="/home/pi/piMediaCenter/Video/";
			USBPath="/media/pi/";
		}
	}
	public String getUSBDevicePath(){
		return preference.get("USBPath",USBPath);
	}
	public int getMainServerPort()
	{
		return preference.getInt("MainServerPort",mainServerPort);
	}
	public void setMainServerPort(int Port)
	{
		preference.putInt("MainServerPort", Port);
	}
	public String getAudioOutput()
	{
		return preference.get("AudioOutput",AudioOutput);
	}
	public void setAudioOutputt(String option)
	{
		preference.put("AudioOutput", option);
	}
	public void setDataFormat(String date)
	{
		preference.put("DateFormating", date);
	}
	public String getDateFormat()
	{
		return preference.get("DateFormating", DateFormating);
	}
	public void setTimeFormat(String time)
	{
		preference.put("TimeFormating", time);
	}
	public String getTimeFormat()
	{
		return preference.get("TimeFormating", TimeFormating);
	}
	public String getVideoFileLocation()
	{
		return preference.get("VideoFileLocation", VideoFileLocation);
	}
	public void setVideoFileLocation(String videoFileLocation)
	{
		preference.put("VideoFileLocation", videoFileLocation);
	}
	
}

package player;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.*;
import java.util.ArrayList;

public class OMXPlayer 
{
	static Process BackgroundProcess;
	public ArrayList<String> Playlist=new ArrayList<String>();
	//public ArrayList<String> PlaylistNames=new ArrayList<String>();
	public int PlaylistIndex;
	int LocX,LocY,SizeWidth,SizeHeight;
	String AudioOutput;
	public final String AudioOutput_HDMI="hdmi";
	public final String AudioOutput_Anlog="local";
	public final String AudioOutput_Both="both";
	public String ErrorList;
	public Boolean IsPause;
	//public float PlayBackSpeed=1;
	InputStream in;
	OutputStream out;
	OmxPlayerEvents events;
	Thread RunTime;

	public void setOmxEvents(OmxPlayerEvents event)
	{
		events=event;
	}	

	public OMXPlayer()
	{		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();		
		init(0,0,gd.getDisplayMode().getWidth(),gd.getDisplayMode().getHeight(),"both");
	}
	public OMXPlayer(int X,int Y, int Width,int Height)
	{				
		init(X,Y,Width,Height,"Both");
	}
	public OMXPlayer(int X,int Y, int Width,int Height,String audio)
	{				
		init(X,Y,Width,Height,audio);
	}
	public OMXPlayer(String audio)
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();	
		init(0,0,gd.getDisplayMode().getWidth(),gd.getDisplayMode().getHeight(),audio);
	}
	public void finalize() throws IOException
	{		
		WriteOutputToCmd('q');
	}
	
	public Boolean IsStreaming()
	{
		return Playlist.get(PlaylistIndex).contains("rtsp://");
	}
	public String getClient()
	{
		String temp= Playlist.get(PlaylistIndex).substring(Playlist.get(PlaylistIndex).indexOf("rtsp://")+"rtsp://".length());
		return temp.substring(0,temp.indexOf(":"));
	}
	void init(int X,int Y, int Width,int Height,String audio)
	{
		LocX=X;
		LocY=Y;		
		AudioOutput=audio;
		PlaylistIndex=0;
		SizeWidth=Width;
		SizeHeight=Height;

	}

	public void setLocation(int x, int y)
	{
		LocX=x;
		LocY=y;
	}
	public void setSize(int Width,int Height)
	{
		SizeWidth=Width;
		SizeHeight=Height;
	}

	public void Play()
	{
		if((out!=null)&&(BackgroundProcess!=null))
		{
			try {
				stop();
			} catch (Exception e) {

			}
		}
		RunTime=new Thread()
		{
			public void run()
			{			
				
				while(Playlist.size()>PlaylistIndex)
				{
					//PlayBackSpeed=1;
					try{events.onPlay(Playlist.get(PlaylistIndex));}
					catch(Exception e){}
					String cmd="omxplayer --key-config=/home/pi/piMediaCenter/keyfile "+"-o "+AudioOutput+" ";
					
					if(LocX!=0||LocY!=0)
						cmd+="--win \""+ LocX+" "+LocY+" "+SizeWidth+" "+SizeHeight+"\"";
					cmd+=Playlist.get(PlaylistIndex);
					ErrorList=cmd;
					try {
						IsPause=false;
						ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
						BackgroundProcess=pb.start();
						in=BackgroundProcess.getInputStream();
						out=BackgroundProcess.getOutputStream();
						
						while(BackgroundProcess.isAlive())
						{
							if(in.available()>0)
							{
								System.out.write(("\n---------OmxPlayer-"+in.available()+"------------\n").getBytes());
								byte[] ret=new byte[in.available()];
								in.read(ret);
								System.out.write(ret);
								System.out.write(("\n---------Omxplayer-End-----------\n").getBytes());
							}
						}
						BackgroundProcess.waitFor();

					} catch (Exception e) {

						//e.printStackTrace();
						System.out.println("Player Stopped");
						ErrorList=e.getMessage();
					}

					PlaylistIndex++;

				}
				try{events.onStop();}
				catch(Exception e){}
				//BackgroundProcess=null;
			}

		};
		RunTime.start();
	}
	public void WriteOutputToCmd(char ch) throws IOException
	{
		try
		{
			System.out.write(("::OmxPlayer--Input :'"+ch+"'").getBytes());
			out.write(ch);
			out.flush();
		}catch(IOException e){                   
			if(e.getMessage().contains("Stream closed"))
			{
				events.onStop();
			}

		}
	}
	public void stop() throws IOException
	{
		WriteOutputToCmd('q');
		Playlist.clear();		
		//RunTime.interrupt();
		//BackgroundProcess.destroyForcibly();
		PlaylistIndex=0;
		events.onStop();
	}
	public void Pause() throws IOException
	{
		try{
			if(IsPause)
				return;
			WriteOutputToCmd('p');
			IsPause=!IsPause;
			if(IsPause)	{	
				events.onPause();
			}
			else{
				events.onResume();
			}
		}
		catch(NullPointerException e){}
	}
	public void Resume() throws IOException
	{
		try{
			if(!IsPause)
				return;
			WriteOutputToCmd('p');
			IsPause=!IsPause;
			if(IsPause)	{	
				events.onPause();
			}
			else{
				events.onResume();
			}
		}
		catch(NullPointerException e){}
	}
	public void VolumeUp() throws IOException
	{
		WriteOutputToCmd('+');
		
	}
	public void VolumeDown() throws IOException
	{
		WriteOutputToCmd('-');
	}
	public void DecreaseSpeed() throws IOException
	{
		WriteOutputToCmd('1');
		events.onDecreaseSpeed();
	}
	public void IncreaseSpeed() throws IOException
	{
		WriteOutputToCmd('2');
		events.onIncreaseSpeed();
	}
	public void Rewind() throws IOException
	{
		WriteOutputToCmd('<');
		events.onRewind();
	}
	public void FastForward() throws IOException
	{
		WriteOutputToCmd('>');
		events.onFastForward();
	}
	public void ShowSubtitles() throws IOException
	{
		WriteOutputToCmd('w');
		events.onShowSubtitles();
	}
	public void HideSubtitles() throws IOException
	{
		WriteOutputToCmd('x');
		events.onHideSubtitles();
	}
	public void Seek10back()
	{
		try {
			//KeyEvent arg0			
			WriteOutputToCmd('4');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Seek10for()
	{
		try {
			out.write('6');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * KEY BINDINGS

			1           decrease speed
			2           increase speed
			<           rewind
			>           fast forward
			z           show info
			j           previous audio stream
			k           next audio stream
			i           previous chapter
			o           next chapter
			n           previous subtitle stream
			m           next subtitle stream
			s           toggle subtitles
			w           show subtitles
			x           hide subtitles
			d           decrease subtitle delay (- 250 ms)
			f           increase subtitle delay (+ 250 ms)
			q           exit omxplayer
			p / space   pause/resume
			-           decrease volume
			+ / =       increase volume
			left arrow  seek -30 seconds
			right arrow seek +30 seconds
			down arrow  seek -600 seconds
			up arrow    seek +600 seconds
	 */

}

/*
 * 
 * omxplayer [OPTIONS] [FILE]

		-h  --help                  Print this help
		-v  --version               Print version info
		-k  --keys                  Print key bindings
		-n  --aidx  index           Audio stream index    : e.g. 1
		-o  --adev  device          Audio out device      : e.g. hdmi/local/both
		-i  --info                  Dump stream format and exit
		-I  --with-info             dump stream format before playback
		-s  --stats                 Pts and buffer stats
		-p  --passthrough           Audio passthrough
		-d  --deinterlace           Force deinterlacing
		    --nodeinterlace         Force no deinterlacing
		    --nativedeinterlace     let display handle interlace
		    --anaglyph type         convert 3d to anaglyph
		    --advanced              Allow advanced deinterlace for HD videos
		-w  --hw                    Hw audio decoding
		-3  --3d mode               Switch tv into 3d mode (e.g. SBS/TB)
		-M  --allow-mvc             Allow decoding of both views of MVC stereo stream
		-y  --hdmiclocksync         Display refresh rate to match video (default)
		-z  --nohdmiclocksync       Do not adjust display refresh rate to match video
		-t  --sid index             Show subtitle with index
		-r  --refresh               Adjust framerate/resolution to video
		-g  --genlog                Generate log file
		-l  --pos n                 Start position (hh:mm:ss)
		-b  --blank                 Set background to black
		    --loop                  Loop file. Ignored if file not seekable
		    --no-boost-on-downmix   Don't boost volume when downmixing
		    --vol n                 set initial volume in millibels (default 0)
		    --amp n                 set initial amplification in millibels (default 0)
		    --no-osd                Do not display status information on screen
		    --no-keys               Disable keyboard input (prevents hangs for certain TTYs)
		    --subtitles path        External subtitles in UTF-8 srt format
		    --font path             Default: /usr/share/fonts/truetype/freefont/FreeSans.ttf
		    --italic-font path      Default: /usr/share/fonts/truetype/freefont/FreeSansOblique.ttf
		    --font-size size        Font size in 1/1000 screen height (default: 55)
		    --align left/center     Subtitle alignment (default: left)
		    --no-ghost-box          No semitransparent boxes behind subtitles
		    --lines n               Number of lines in the subtitle buffer (default: 3)
		    --win 'x1 y1 x2 y2'     Set position of video window
		    --win x1,y1,x2,y2       Set position of video window
		    --crop 'x1 y1 x2 y2'    Set crop area for input video
		    --crop x1,y1,x2,y2      Set crop area for input video
		    --aspect-mode type      Letterbox, fill, stretch. Default: stretch if win is specified, letterbox otherwise
		    --audio_fifo  n         Size of audio output fifo in seconds
		    --video_fifo  n         Size of video output fifo in MB
		    --audio_queue n         Size of audio input queue in MB
		    --video_queue n         Size of video input queue in MB
		    --threshold   n         Amount of buffered data required to finish buffering [s]
		    --timeout     n         Timeout for stalled file/network operations (default 10s)
		    --orientation n         Set orientation of video (0, 90, 180 or 270)
		    --fps n                 Set fps of video where timestamps are not present
		    --live                  Set for live tv or vod type stream
		    --layout                Set output speaker layout (e.g. 5.1)
		    --dbus_name name        default: org.mpris.MediaPlayer2.omxplayer
		    --key-config <file>     Uses key bindings in <file> instead of the default
		    --alpha                 Set video transparency (0..255)
		    --layer n               Set video render layer number (higher numbers are on top)
		    --display n             Set display to output to
		    --cookie 'cookie'       Send specified cookie as part of HTTP requests
		    --user-agent 'ua'       Send specified User-Agent as part of HTTP requests
		    --lavfdopts 'opts'      Options passed to libavformat, e.g. 'probesize:250000,...'
 */



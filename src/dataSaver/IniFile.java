package dataSaver;
import java.io.*;

public class IniFile {

	String FileName;
	public IniFile()
	{
		OpenOrCreate("Default");
	}
	public IniFile(String src)
	{
		OpenOrCreate(src);
	}
	private void OpenOrCreate(String src)
	{	
		try {
			File file=new File(src+".ini");
			if(!file.exists())
				file.createNewFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};		
		FileName=src;		
	}
	@SuppressWarnings("resource")
	public void put(String key,String value)
	{
		BufferedReader  read;
		BufferedWriter write;
		try {
			FileReader input=new FileReader(FileName+".ini");
			FileWriter output=new FileWriter(FileName+".ini",true);		
			read =new BufferedReader (input);
			write =new BufferedWriter(output);
			String wkey;
			String str;
			do
			{				
				if((str=read.readLine())==null)
				{
					write.write(key+":"+value+"\n");
					return;
				}					
				wkey=str.substring(0, str.indexOf(":")-1);
			}while(wkey.compareTo(key)!=0);
			//
			FileWriter tempFile=new FileWriter(FileName+".temp",false);
			while((str=read.readLine())!=null)
			{
				wkey=str.substring(0, str.indexOf(":")-1);
				if(wkey!=key)
					tempFile.write(str+"\n");
				else
					tempFile.write(key+":"+value+"\n");
			}
			write.close();
			read.close();
			input.close();
			output.close();
			tempFile.close();			
			//new File(FileName+".ini").delete();
			new File(FileName+".temp").renameTo(new File(FileName+".ini"));
			//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("resource")
	public String get(String key,String Default)
	{
		String wkey,wvalue=Default;	
		try {
			FileReader input=new FileReader(FileName+".ini");			
			BufferedReader  read =new BufferedReader (input);			
			String str;
			do
			{				
				if((str=read.readLine())==null)
					return wvalue;
				wkey=str.substring(0, str.indexOf(":"));
			}while(wkey.compareTo(key)!=0);
			wvalue=str.substring(str.indexOf(":")+1);
			read.close();
			input.close();
		} catch (IOException e) {
			put(key,Default);
		}
		return wvalue;

	}
	public void putInt(String key,int value)
	{
		put(key,value+"");
	}
	public int getInt(String key,int Default)
	{
		return Integer.parseInt(get(key,Default+""));
	}
}

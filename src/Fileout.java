import java.io.*;
import java.awt.*;

//3-17-06 Added Dialog Box to override FileExists
//9-18-05 Class created

class Fileout
{
	private PrintWriter out;		//The outputstream
	private String name;			//The name of the file
	
	Fileout(String Fname)	//Opens the file
	{
		name=Fname;
		
		File temp=new File(name);
		if(temp.exists())
		{
	
			Popup message = new Popup(new Frame("Overwrite") , "Overwrite the file?", true);
    		message.requestFocus();
			
			if(message.id)
			{
				try{out = new PrintWriter(new FileWriter(name));}
				catch(java.io.IOException e){}
			}
			message.dispose();
			return;
	
		}
		else
		{
			try{out = new PrintWriter(new FileWriter(name));}
			catch(java.io.IOException e){}
		}
		
	}
	
	
	Fileout(String Fname,boolean v)	//Opens the file and writes without prompt
									//when v has the value true
	{
		name=Fname;
		if(v==true)
		{
			File temp=new File(name);		
			try{out = new PrintWriter(new FileWriter(name));}
			catch(java.io.IOException e){}
		}
	}
	
	void writeLine(String text)	//Writes a string then a return
	{	try{out.println(text);}
		catch(java.lang.NullPointerException e){}
	}	
	void write(String text)	//Writes without going down a line
	{	try{out.print(text);}
		catch(java.lang.NullPointerException e){}
	}
	
	void writeLine(int num){writeLine(num+"");}	
	void write(int num){write(num+"");}	
	void writeLine(double num){writeLine(num+"");}	
	void write(double num){write(num+"");}
	void writeLine(float num){writeLine(num+"");}	
	void write(float num){write(num+"");}	
	void writeLine(long num){writeLine(num+"");}	
	void write(long num){write(num+"");}
	void writeLine(boolean num){writeLine(num+"");}	
	void write(boolean num){write(num+"");}
	void writeLine(char num){writeLine(num+"");}	
	void write(char num){write(num+"");}
	
	String name(){return name;}
	
	void close() 	//Closes the file
	{
		try{out.close();}
		catch(java.lang.NullPointerException e){}
	}

}
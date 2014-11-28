import java.io.*;
import java.util.*;
import java.lang.*;

//10-19-07 Began conversion from BufferedReader format to Scanner
//10-20-07 Conversion done, testing begin. Remove mark()/reset() since they're not necessary.
//10-23-07 Superseded the old version of Filein

class Filein
{
	private Scanner in;			//The inputstream
	private String name;		//The name of the file
	
	private String word;		//used in readWord()
	
	private char lastchar;		//used in read()
	private int charloc;		//used in read()
	private boolean newWord;	//used in read()
	private int wordLength;		//used in read()
	
	private boolean DelChanged;	//used in Delimiter Settings
	
	Filein(String Fname)		//Opens the file
	{
		name=Fname;
		lastchar = 0;
		charloc = 0;
		newWord = true;
		
		word="";
		DelChanged=false;
		try{in = new Scanner(new FileInputStream(name));}
		catch(java.io.FileNotFoundException e){}
	}
	
	void setDel(String del)		//Sets Delimiter in use.
	{
		try{in.useDelimiter(del);}
		catch(java.lang.IllegalStateException e){}
		DelChanged=true;
	}
	
	void setDefaultDel()		//Resets Delimiter to default.
	{
		try{in.useDelimiter("\\s*");}
		catch(java.lang.IllegalStateException e){}
		DelChanged=false;
	}
	
	String readLine()	//Reads one line at a time
	{
		try{return in.nextLine();}
		catch(java.util.NoSuchElementException e){return "";}
		catch(java.lang.IllegalStateException e){return "";}
	}
	
	char read()		//Reads one character at a time
	{
		setDel("");	//Set Delimiter to nothing to read Char by Char
		
		if(!newWord)
		{
			if(charloc <= wordLength-1)
			{
				lastchar = (char)word.charAt(charloc);
				charloc++;
				return lastchar;
				
			} 
			else {newWord = true;}
		}
		
		if(newWord)
		{
			charloc=0;
			newWord = false;
			word = readWordDel();
			wordLength = word.length();
			lastchar = (char)word.charAt(charloc);
			charloc++;
			return lastchar;
		}
		
		return '\u0000';
	}
	
	String readN(int n)		//Reads n characters in file
	{
		StringBuffer temp=new StringBuffer("");
		for(int i=0;i<n;i++)temp.append(this.read());
		return temp.toString();
	}
	
	String readWordDel()	// Reads anything into a String.
	{
		try{return in.next();}
		catch(java.util.NoSuchElementException e){return readWordDel();}
		catch(java.lang.IllegalStateException e){return "";}
	}
	
	String readWord()			// Reads anything into a String.
	{
		if(DelChanged)setDefaultDel();
		
		try{return in.next();}
		catch(java.util.NoSuchElementException e){return readWord();}
		catch(java.lang.IllegalStateException e){return "";}
	}
	
	double readDouble()		//Reads a double from a file
	{
		try
		{
			while(!in.hasNextDouble())	//check if next token is a double
			{	
				in.next(); 				//if not skip to next token.
			}
			
			return in.nextDouble();	//return double
		}
		catch(java.util.NoSuchElementException e){return 0;}
		catch(java.lang.IllegalStateException e){return 0;}
	}

	long readLong()
	{
		try{
		while(!in.hasNextLong())		//check if next token is a long
			{	
				in.next(); 				//if not skip to next token.
			}
			
		return in.nextLong();
		}
		catch(java.util.NoSuchElementException e){return 0;}
		catch(java.lang.IllegalStateException e){return 0;}
	}
	
	boolean hasNextInt(){return in.hasNextInt();}
	
	int readInt()
	{
		try
		{
		while(!in.hasNextInt())		//check if next token is a Int
			{	
				in.next(); 				//if not skip to next token.
			}
		return in.nextInt();
		}
		catch(java.util.NoSuchElementException e){return 0;}
		catch(java.lang.IllegalStateException e){return 0;}
	}
	
	double readNumber()				//returns any of the common number types as a double!
	{
		try
		{
			while(!in.hasNextInt() & !in.hasNextLong() & !in.hasNextDouble() & !in.hasNextShort())	//scan for all common number types
			{
				in.next();
			}
		
			if(in.hasNextInt())
			{
				try{return (double)in.nextInt();}
				catch(java.util.NoSuchElementException e){return 0;}
				catch(java.lang.IllegalStateException e){return 0;}
			}
			if(in.hasNextLong())
			{
				try{return (double)in.nextLong();}
				catch(java.util.NoSuchElementException e){return 0;}
				catch(java.lang.IllegalStateException e){return 0;}
			}
			if(in.hasNextDouble())
			{
				try{return in.nextDouble();}
				catch(java.util.NoSuchElementException e){return 0;}
				catch(java.lang.IllegalStateException e){return 0;}
			}
			if(in.hasNextShort())
			{
				try{return (double)in.nextShort();}
				catch(java.util.NoSuchElementException e){return 0;}
				catch(java.lang.IllegalStateException e){return 0;}
			}
		}
		catch(java.util.NoSuchElementException e){}
		catch(java.lang.IllegalStateException e){}
			
		return 0;
	}
	
	String readFile()	//Reads entire file into a string
	{
		StringBuffer temp=new StringBuffer("");
		while(this.ready()){temp.append(this.readLine());temp.append("\n");}
		return temp.toString();
	}

	boolean ready()		//returns true if the file is not EOF 
	{
		try{return in.hasNext();}
		catch(java.util.NoSuchElementException e){return false;}
		catch(java.lang.IllegalStateException e){return false;}
	}
	
	String name(){return name;}	//returns file name

	void close() 	//Closes the file
	{
		try{in.close();}
		catch(java.lang.IllegalStateException e){}
	}
}
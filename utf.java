import java.*;
import java.io.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;
import java.lang.*;
import java.lang.Character.*;

public class utf 
{

	public static void printBytes(byte[] array, String name) {
      for (int k = 0; k < array.length; k++) {
         System.out.println(name + "[" + k + "] = " + "0x" +
            UnicodeFormatter.byteToHex(array[k]));
      }
    }
	
	public static ArrayList loadFile(String fileName)
    {
        if ((fileName == null) || (fileName == ""))
            throw new IllegalArgumentException();
        
        String line;
        ArrayList file = new ArrayList();

        try
        {    
            BufferedReader in = new BufferedReader(new FileReader(fileName));

            if (!in.ready())
                throw new IOException();
	
            while ((line = in.readLine()) != null)
                file.add(line);

            in.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
            return null;
        }

        return file;
    }
	 
	public static void main(String[] args) 
	{
		String utf8try = "☛";
		System.out.println("utf8try: " + utf8try);
		String original = utf8try;
		
		try {
			byte[] utf8Bytes = original.getBytes("UTF8");
			byte[] defaultBytes = original.getBytes();

			String roundTrip = new String(utf8Bytes, "UTF8");
			System.out.println("roundTrip = " + roundTrip);
			System.out.println();
			printBytes(utf8Bytes, "utf8Bytes");
			System.out.println();
			printBytes(defaultBytes, "defaultBytes");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String u = "";
		System.out.println(u);
		u = "";
		System.out.println(u);
		String t = "® ";
		System.out.println(t);
		t = "® ";
		System.out.println(t);
		String f = null;
		try
		{
			f = new String("".getBytes(), "UTF-8");
			System.out.println(f);
		}
		catch(Exception e){}
		try
		{
			f = new String("".getBytes(),"Cp1252");
			//f = "";
			System.out.println("--------------------" + f);
		}
		catch(Exception e){}
		try
		{
			f = new String("".getBytes(), "US-ASCII");
			System.out.println(f);
		}
		catch(Exception e){}
		try
		{
			f = new String("".getBytes(), "UTF-16BE");
			System.out.println(f);
		}
		catch(Exception e){}
		try
		{
			f = new String("".getBytes(), "UTF-16LE");
			System.out.println(f);
		}
		catch(Exception e){}
		try
		{
			f = new String("".getBytes(), "UTF-16");
			System.out.println(f);
		}
		catch(Exception e){}
	
		/*
		File f = new File("f.xml");
		InputStream inputStream = new FileReader("f.xml");
		String c;
  	while ((c = inputStream.read()) != -1) 
  	{
	  	System.out.println(c);
	  }
	  */
	  System.out.println("===============================================");
	  //String line = new String("UTF-16");
	  	String line;
		double value;
		BufferedReader in;
		InputStreamReader inSr;
		try 
		{
			/*
			inSr = new InputStreamReader(new FileInputStream("f.xml"), "Cp1252");
			in = new BufferedReader(new FileReader("f.xml"));
			//line = in.readLine();
			line = (String)inSr.read();
			System.out.println(line);
			//line = in.readLine();
			line = (String)inSr.read();
			System.out.println(line);
			line = (String)inSr.read();
			System.out.println(line);
			*/
			//ArrayList aL = loadFile("./eboac-41-2007-08-08-13-50-12.test.tm.xml");
			//ArrayList aL = loadFile("./f.xml");
			ArrayList aL = loadFile("./loaderD-1.log");
			for(int i=0; i< aL.size(); i++)
      {
      				String logLine = (String)aL.get(i);
      				if(logLine.indexOf("? ") != -1)
      				{
      					System.out.println("a match");
      				}
      				
      				/*
      				System.out.println(logLine);
      				char[] cArray = logLine.toCharArray();
      				//Byte[] bArray = cArray.toByteArray();
							for(int j=0; j<cArray.length; j++)
							{
      					//System.out.println(Character.getNumericValue(cArray[j]));
      					//if((int)Character.getNumericValue(cArray[j]) == -1)
      					System.out.println(Character.isDefined(cArray[j]));
      					if(Character.isDefined(cArray[j]))
        				{	
        					System.out.println("found it");	
        				}	
	     			  }
	     			  */
	     			  
  		}
  		System.out.println("not found");
		}
		catch(Exception e){}
	}
}

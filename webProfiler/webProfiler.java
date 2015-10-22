import java.io.*;
import java.net.*;
import java.util.*;

/*
FILE FORMAT: ./list.txt
<SEARCH_TERM>
<http://WEBSITE_TO_ANALYZE...0>
<http://WEBSITE_TO_ANALYZE...1>
<http://WEBSITE_TO_ANALYZE...2>
...
<http://WEBSITE_TO_ANALYZE...n>
*/

public class webProfiler 
{
	private static String list = "list.txt";
	private Vector v       = new Vector();
	private Vector vResult = new Vector();
	private Vector vCount  = new Vector();
	private String search  = "";
	
	public static void main(String[] args) 
	{
		System.out.println("****    acorn webProfiler     ****");
		if(args.length != 0)
		{
			list = args[0];
		}
		System.out.println("Website list: ./" + list);
		webProfiler wB = new webProfiler();
	}
	 
	public webProfiler()
	{
		readFile();
		search = (String)v.get(0);
		vResult.add("YES");
		vCount.add("" + 1);
		for(int i=1; i<v.size(); i++)
		{
			String content = getWeb((String)v.get(i));
			System.out.print("Analyzing Site:   " + (String)v.get(i));
			if(content == null){ vResult.add("XXX"); vCount.add("0"); System.out.println(" 0"); }
			else if(!content.contains(search)){ vResult.add("NO "); vCount.add("0"); System.out.println(" 0"); }
			else
			{ 
				vResult.add("YES");
				int count = 0;
				int index = 0;
				while(index != -1)
				{
					index = content.indexOf(search, index);
					if(index != -1){ count++; index++; }
				}
				vCount.add("" + count);
				System.out.println(" " + count);
			}
		}
		writeFile();
		System.out.println("****  acorn webProfiler DONE  ****");
	}
	
	public String getWeb(String site)
	{
		System.out.println("Retrieving Site:  " + site);
		StringBuffer buf = new StringBuffer();
		try
		{
			URL url = new URL(site);
			InputStream in = url.openStream();
			BufferedInputStream bufIn = new BufferedInputStream(in);
			while(true)
			{
				int data = bufIn.read();
				if (data == -1)
				{
				break;
				}
				buf.append((char) data);
			}
			return buf.toString();
		}
		catch(Exception e){ return null; }
	}
	
	public void readFile()
	{
		try
		{
			FileInputStream fstream = new FileInputStream("./" + list);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)   
			{
				v.add(strLine);
				//System.out.println(strLine);
			}
			in.close();
			}
			catch (Exception e){ System.err.println ("Unable to read file"); System.exit(-1); }
	}
	
	public void writeFile()
	{
		FileOutputStream fout;	
		PrintStream pout;
		try
		{
		    fout = new FileOutputStream (list + "_RESULT.txt");
			pout = new PrintStream(fout);
			pout.println("SEARCH_STRING: " + search);
			pout.println("STRING_FOUND - NUMBER_FOUND - SITE_EXAMINED");
			for(int i=1; i<vResult.size(); i++)
			{
				String line = "";
				if(vResult.get(i) != null)
				{
					line = (String)vResult.get(i) + " - ";
				}
				else
				{
					line = "XXX - ";
				}
				pout.println(line + (String)vCount.get(i) + " - " + (String)v.get(i));
			}
		    fout.close();		
		}
		catch (IOException e){ System.err.println ("Unable to write to file"); System.exit(-1); }
	}
	
	private String addSlashes(String str)
    {
        if(str==null) return "";
        StringBuffer s = new StringBuffer ((String) str);
        for(int i = 0; i < s.length(); i++)
        {
            if (s.charAt (i) == '\''){ s.insert (i++, '\\'); }
        }
        return s.toString();
     }
}

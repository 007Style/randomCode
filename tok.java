import java.*;
import java.io.*;
import java.util.*;

public class tok 
{
	 
	public static void main(String[] args) 
	{
		//cartModParse();
		//tokParse();
		stQueryParse();
	}
	
	public static void stQueryParse()
	{
		String test = "Ms'pacman@comcast.net";
		System.out.println(test);
		System.out.println(addSlashes(test));
	
	}
	
	private static String addSlashes(String str)
    {
        if(str==null) return "";
        StringBuffer s = new StringBuffer ((String) str);
        for(int i = 0; i < s.length(); i++)
        {
            if (s.charAt (i) == '\''){ s.insert (i++, '\\'); }
        }
        return s.toString();
    }
	
	public static void tokParse()
	{
		String logLine = "a,b,c,d,,e,f";
		WStringTokenizer sT = new WStringTokenizer(logLine, ",");
		System.out.println(sT.countTokens());
		int num = sT.countTokens();
		System.out.println(num);
		for(int j=0; j<num; j++)
            	{
            		System.out.println(j);
            		System.out.println(sT.nextToken());
            	}
	}
	
	public static void cartModParse()
	{
		String modString = "discount%:10,color1:blue,color2:green";
		StringTokenizer mST = new StringTokenizer(modString, ",");
		while(mST.hasMoreTokens())
		{
			String tok = mST.nextToken();
			StringTokenizer mST2 = new StringTokenizer(tok, ":");
			String item = mST2.nextToken();
			String val = mST2.nextToken();
			System.out.println("item=" + item + " val=" + val);
		}
		String me = "how are you " +
		"Oh I am fine " +
		"what are you doing?";
		System.out.println(me);
		/*
    int commaTokArrayNum = mST.countTokens();
    String[] commaTokArray = new String[commaTokArrayNum];
    for(int i=0; i<commaTokArrayNum; i++)
    {
        commaTokArray[i] = mST.nextToken();
    }
    String[] colonTokArray = new String[commaTokArrayNum];
    for(int i=0; i<commaTokArrayNum; i++)
    {
        StringTokenizer mST2 = new StringTokenizer(spaceTokArray[i], ":");
        sT2.nextToken();
        eqTokArray[i] = sT2.nextToken();
    }
    */
  }
}

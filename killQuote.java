import java.*;
import java.io.*;
import java.util.*;

public class killQuote 
{
	 
	public static void main(String[] args) 
	{
		String str = "\"wahahah I am the man, YOYOYOyoyoy...\"";
		System.out.println(strParse(str));
	  str = "wahahah I am the man, YOYOYOyoyoy...\"";
		System.out.println(strParse(str));
	}
	
	public static String strParse(String str)
	{
		if(str.startsWith("\"") && str.endsWith("\""))
        {
            return str.substring(1, str.length()-1);
        }
        else
        {
            return str;
        }
	}
}

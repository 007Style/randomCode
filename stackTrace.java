
import java.io.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;
import java.lang.*;

public class stackTrace
{	
	public static void main(String[] args)
	{
		System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
		Double d = null;
		try{ d = Double.parseDouble("asdf"); } 
    catch (Exception e) 
    { 
    	e.printStackTrace();
    	System.out.println("----------------");
    	System.out.println(e);
    	System.out.println("----------------");
    	stackTrace(e);
    }
	}
	
	public static String stackTrace(Exception e)
	{
		String error = null;
		error = e.toString() + "\n";
		error +=e.getMessage() + "\n";
		StackTraceElement[] ee = e.getStackTrace();
    for(int u=0; u<ee.length; u++)
    {
    	error += "FileName: " + ee[u].getFileName() + " || ClassName: " + ee[u].getClassName() + " || MethodName: " + ee[u].getMethodName() + " || LineNumber: " + ee[u].getLineNumber() + "\n";
    }
    //System.out.println(error);
    return error;
	}
}
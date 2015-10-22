import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class CustomClassLoaderTest {
 
     public static void main(String [] args){
	try
	{
		System.out.println("Starting up...");
        	CustomClassLoader test = new CustomClassLoader();
	        test.loadClass(HelloWorld);
		test.printer();
	}
	catch(Exception e){ System.out.println(e); }
     }
}

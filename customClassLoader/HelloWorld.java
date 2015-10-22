import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class HelloWorld {
 
     public HelloWorld()
     {
  	System.out.println("Class Loaded");
     }

     public void printer()
     {
  	System.out.println("Class Loaded");
     }

     public static void main(String [] args){
	HelloWorld hW = new HelloWorld();
     }
}

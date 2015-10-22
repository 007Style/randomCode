import java.io.*;
 
public class Tail
{
  public static void main(String args[])
  {
    try
    {
      BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
      String line;
      String mailLogFile = "";
 			boolean control = true;
 			long maillogSize = 0;
 
      while (control)
      {
      	File log = new File("file.txt");
        long lSize = log.length();
        if(lSize < maillogSize)
        {
        	reader = new BufferedReader(new FileReader("file.txt"));
        }
        maillogSize = lSize;
        
        if ((line = reader.readLine()) != null)
        {
          System.out.println(line);
          //mailLogFile += line + "\n";
        }
        else
        {
          Thread.sleep(1000);
          //control = false;
        }
      }
      System.out.println(mailLogFile);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
}

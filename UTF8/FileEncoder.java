import java.io.*;
import com.sun.syndication.io.XmlReader;

/**
* A utility to convert text files to utf-8.
*/
public class FileEncoder 
{
  /**
  * args[0] is the input file name and args[1] is the output file name.
  */
  public static void main(String[] args) 
  {
    try 
    {
      System.out.println("Input  File: " + args[0]);
      System.out.println("Output File: " + args[1]);
      FileInputStream fis = new FileInputStream(args[0]);
      byte[] contents = new byte[fis.available()];
      fis.read(contents, 0, contents.length);
      String asString = new String(contents, "ISO8859_1");
      byte[] newBytes = asString.getBytes("UTF8");
      FileOutputStream fos = new FileOutputStream(args[1]);
      fos.write(newBytes);
      fos.close();

      File fl=new File(args[1]);
      XmlReader xr =new XmlReader(fl);
      System.out.println("File encoding test ==========="+xr.getEncoding());
    } 
    catch(Exception e) 
    {
      e.printStackTrace();
      System.out.println("USAGE: java FileEncoder <input file> <output file>");
    }
  }

  public FileEncoder(){}
}

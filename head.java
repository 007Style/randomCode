import java.io.*;
 
public class head
{
  public head(){}

  public static void main(String args[])
  {
    int hi = 1;
    for(int i=0; i<10; i++)
    {
      System.out.println(hi++);
    }
	String me  = "wanker";
	String too = null;
	String ans = too + "<TEXT>" + me;
	System.out.println(ans);
  }
}

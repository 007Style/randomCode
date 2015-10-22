import java.*;
import java.lang.*;
import java.util.*;

public class puker 
{
	public static void func(String me, String notme)
	{
		System.out.println("me: " + me);
		System.out.println("notme: " + notme);
	}

	private static String fileCatcher(String query, String filename)
    {
		System.out.println("+++ " + filename);
        query = query.replaceAll("%filename%", filename);
        query = query.replaceAll("%filename%", filename);
		System.out.println("=== " + query);
        return query;
    }
	 
	public static void main(String[] args) 
	{
		try
		{
			System.out.println(Double.parseDouble("45"));
		}
		catch(Exception e){ System.out.println("nope"); }
		String wanker = "sadfdsafdsf@wanker.com";
		System.out.println("out: " + wanker.substring(1+wanker.indexOf("@")));
		Random rand = new Random();
    		int num = rand.nextInt(10);
    		System.out.println("Generated Random Number between 0 to 10 is : " + num);
		String[][] me = new String[2][3];
		me[0][0] = "a";		me[0][1] = "b";		me[0][2] = "c";
		me[0][0] = "1";		me[0][1] = "2";		me[0][2] = "3";
		String[] fire = me[1];
		System.out.println(fire[0] + " " + fire[1] + " " + fire[2]);
		//func("me");
		System.out.println("============================================================");
		String[][] mem = new String[8][2];
		mem[0][0] = "988768768687";
		System.out.println(mem[0][0].length());
		System.out.println("mem[0].length = " + mem[0].length);
		System.out.println("mem.length = " + mem.length);
		//System.out.println("mem[0][0].length = " + mem[0][0].length);
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println(fileCatcher("i am a double douche %filename% dooode", "wanker"));
		System.out.println("hi moma hi".replace("moma", "me"));
	}
}

import java.util.*;
import java.text.*;
import java.util.regex.*;

public class listScrubRegEx
{	
	// http://www.regular-expressions.info/email.html
    private static final String emailListPattern1 ="^[A-Z0-9._%+-]+@([A-Z0-9.-]+\\.)+[A-Z]{2,4}$";
    private static final String emailListPattern0 ="[A-Z0-9._%+-]+@([A-Z0-9.-]+\\.)+[A-Z]{2,4}";
    private static final String emailListPattern ="^[A-Za-z0-9._%+-]+@([A-Za-z0-9.-]+\\.)+[A-Za-z]{2,4}$";
    private static final String emailListPatternE0 = "^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)+$";
    private static final String emailListPatternE1 = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    private static final String emailListPatternE2 = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private static final String emailListPatternE3 = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";  // Official Standard RFC 2822 - Obsolete methods included
    private static final String emailListPatternE4 = "[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?";
    
    
	public static boolean validAddr_Scrub(String scrub)
    {
        if(scrub != null)
        {
            Pattern p = java.util.regex.Pattern.compile(emailListPatternE2, java.util.regex.Pattern.CASE_INSENSITIVE);
            Matcher myMatcher = p.matcher(scrub);
            if(myMatcher.matches())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
	
	public boolean scrubAddrRegEx(String eAddr) throws Exception
    {
        try
        {
            if(eAddr != null)
            {
                if(!validAddr_Scrub(eAddr)){ eAddr = null; }
            }
            if(eAddr == null){ return false; }
            else{ return true; }
        }
        catch(Exception e){ throw e; }
    }
    
    public static void main(String[] args)
	{
		listScrubRegEx thD = new listScrubRegEx();
		String e = "";
		try
		{
			System.out.println("PASS");
			e = "S_J@Debra_Drummond.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "S-J@Debra-Drummond.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "S_J@DebraDrummond.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "S-J@DebraDrummond.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "S'J@DebraDrummond.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "dsingley@us.ibm.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "0dsingley@us.ibm.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "dsingley49@ibm.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "dsingley@us.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "phyl6133@aol.c";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "phyl6133@aol.co";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "phyl6133@aol.tw";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "phyl6133@aol.us";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "phyl6133@aol.jp";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "phyl6133@aol.com.amer";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "phyl6133@aol.com.ameri";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "phyl6133@aol.usasdfasdfasdf";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "gsteele@qwerty.qwert.aol.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "gMoneyInDaHouseYo@aol.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "D@AOL.COM";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "D.E.endoman@AOL.tw.COM";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "\"Fred Boggs\"@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "\"FredBoggs\"@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "Fred\\Boggs@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "Fred/Boggs@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			System.out.println("");
			System.out.println("Should PASS");
			e = "Dawn.O'Donnell@gmail.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "\"Abc\\@def\"@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "\"Fred Bloggs\"@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "\"Joe\\Blow\"@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "\"Abc@def\"@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "customer/department=shipping@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "$A12345@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "!def!xyz%abc@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "_somename@example.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			System.out.println("");
			System.out.println("FAIL");
			e = "S_J@Debra_Drummond.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "gsteeladsfa;klkjfe@wi.rr.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "gsteele@oooooooooooo";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "dsingley@.us.ibm.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "dsingley@us.ibm..com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "phyl6133@.aol.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "gsteele@wi.rr..com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "gsteeleoooooooooooo";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "gsteele@@aol.com";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
			e = "gsteele@aol.com.";
			System.out.println(thD.scrubAddrRegEx(e) + " :: " + e);
		}
		catch(Exception ee){}
	}
}
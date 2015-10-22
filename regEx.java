 
import java.util.regex.*;
import java.lang.*;

public class regEx {
	//private static final String emailListPattern ="^[A-Z0-9._%+-]+@([A-Z0-9.-]+\\.)+[A-Z]{2,4}$";
	private static final String emailListPattern ="^[A-Za-z0-9._%+-]+@([A-Za-z0-9.-]+\\.)+[A-Za-z]{2,4}$";
	
  public static void main(String[] argv) {
    String pattern = "[\\xC0-\\xDF]([^\\x80-\\xBF]|$)|[\\xE0-\\xEF].{0,1}([^\\x80-\\xBF]|$)|[\\xF0-\\xF7].{0,2}([^\\x80-\\xBF]|$)|[\\xF8-\\xFB].{0,3}([^\\x80-\\xBF]|$)|[\\xFC-\\xFD].{0,4}([^\\x80-\\xBF]|$)|[\\xFE-\\xFE].{0,5}([^\\x80-\\xBF]|$)|[\\x00-\\x7F][\\x80-\\xBF]|[\\xC0-\\xDF].[\\x80-\\xBF]|[\\xE0-\\xEF]..[\\x80-\\xBF]|[\\xF0-\\xF7]...[\\x80-\\xBF]|[\\xF8-\\xFB]....[\\x80-\\xBF]|[\\xFC-\\xFD].....[\\x80-\\xBF]";  //"^Q[^u]\\\\d+\\\\..*";
    String input = "asdf™"; //"™"; //"®"; //"QA777. is the next flight. It is on time.";
		//pattern  = "[\xC0-\xDF]([^\x80-\xBF]|$):";
  	//pattern .= '|[\xE0-\xEF].{0,1}([^\x80-\xBF]|$)';
    //pattern .= '|[\xF0-\xF7].{0,2}([^\x80-\xBF]|$)';
    //pattern .= '|[\xF8-\xFB].{0,3}([^\x80-\xBF]|$)';
    //pattern .= '|[\xFC-\xFD].{0,4}([^\x80-\xBF]|$)';
    //pattern .= '|[\xFE-\xFE].{0,5}([^\x80-\xBF]|$)';
    //pattern .= '|[\x00-\x7F][\x80-\xBF]';
    //pattern .= '|[\xC0-\xDF].[\x80-\xBF]';
    //pattern .= '|[\xE0-\xEF]..[\x80-\xBF]';
    //pattern .= '|[\xF0-\xF7]...[\x80-\xBF]';
    //pattern .= '|[\xF8-\xFB]....[\x80-\xBF]';
    //pattern .= '|[\xFC-\xFD].....[\x80-\xBF]';
    //pattern .= '|[\xFE-\xFE]......[\x80-\xBF]';
    //pattern .= '|^[\x80-\xBF]';
    
    //String scrub = "dsingley@us.i|||bm.com";
    String scrub = "metermaidr1@aol.co;m";
		
		//Pattern p = Pattern.compile(emailListPattern);
 		//Matcher m = p.matcher(scrub);
 		//boolean b = m.matches();
 		
 		Pattern p = Pattern.compile(emailListPattern);
		Matcher m = p.matcher(scrub);
 		boolean b = m.matches();

		
		//Pattern p = java.util.regex.Pattern.compile(emailListPattern);
    //Matcher myMatcher = p.matcher(scrub);
    //Pattern p = new Pattern();
    //boolean b = p.matches(emailListPattern, scrub);
    System.out.println(b);
    /*
    if(myMatcher.matches())
    {
    	System.out.println("Matches");
    }
    else
    {
    	System.out.println("NO Matches");
    }
    */
		
		
		
    
    /*
    StringBuffer myStringBuffer = new StringBuffer();
		myMatcher = myPattern.matcher("subject");
		while (myMatcher.find()) {
  		if (checkIfThisMatchShouldBeReplaced()) {
    		myMatcher.appendReplacement(myStringBuffer, computeReplacementString());
  		}
		}
		myMatcher.appendTail(myStringBuffer);
    */
    
    
  }
}

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id: REString.java,v 1.1 2008/03/06 02:29:50 dsingley Exp $
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */
/**
 * Simple example of using RE functionality in String class.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: REString.java,v 1.1 2008/03/06 02:29:50 dsingley Exp $
 */
 
import java.util.regex.*;
import java.lang.*;

public class REString {
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
		
		Pattern p = java.util.regex.Pattern.compile(pattern);
		Matcher myMatcher = p.matcher("subjectasdf™Little TikesÂ? Double Easel Little TikesÂ? Double Easel");
		String s = myMatcher.replaceAll("");
		
		System.out.println("****    " + s);
		
		//CharSequence s = 

    boolean found = input.matches(pattern);

    System.out.println("'" + pattern + "'" +
      (found ? " matches " : " doesn't match '") + input + "'");
      
    //System.out.println(input.replaceAll("[\\xC0-\\xDF]([^\\x80-\\xBF]|$)|[\\xE0-\\xEF].{0,1}([^\\x80-\\xBF]|$)|[\\xF0-\\xF7].{0,2}([^\\x80-\\xBF]|$)|[\\xF8-\\xFB].{0,3}([^\\x80-\\xBF]|$)|[\\xFC-\\xFD].{0,4}([^\\x80-\\xBF]|$)|[\\xFE-\\xFE].{0,5}([^\\x80-\\xBF]|$)|[\\x00-\\x7F][\\x80-\\xBF]|[\\xC0-\\xDF].[\\x80-\\xBF]|[\\xE0-\\xEF]..[\\x80-\\xBF]|[\\xF0-\\xF7]...[\\x80-\\xBF]|[\\xF8-\\xFB]....[\\x80-\\xBF]|[\\xFC-\\xFD].....[\\x80-\\xBF]|[\\xFE-\\xFE]......[\\x80-\\xBF]|^[\\x80-\\xBF]", ""));
    
    
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

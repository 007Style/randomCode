/*************************************************************************
 *  Compilation:  javac WebCrawler.java In.java
 *  Execution:    java WebCrawler url
 *  
 *  Downloads the web page and prints out all urls on the web page.
 *  Gives an idea of how Google's spider crawls the web. Instead of
 *  looking for hyperlinks, we just look for patterns of the form:
 *  http:// followed by an alternating sequence of alphanumeric
 *  characters and dots, ending with a sequence of alphanumeric 
 *  characters.
 *
 *  % java WebCrawler http://www.slashdot.org
 *  http://www.slashdot.org
 *  http://www.osdn.com
 *  http://sf.net
 *  http://thinkgeek.com
 *  http://freshmeat.net
 *  http://newsletters.osdn.com
 *  http://slashdot.org
 *  http://osdn.com
 *  http://ads.osdn.com
 *  http://sourceforge.net
 *  http://www.msnbc.msn.com
 *  http://www.rhythmbox.org
 *  http://www.apple.com
 *  ...
 *
 *  % java WebCrawler http://www.cs.princeton.edu
 *  http://www.cs.princeton.edu
 *  http://www.w3.org
 *  http://maps.yahoo.com
 *  http://www.princeton.edu
 *  http://www.Princeton.EDU
 *  http://ncstrl.cs.Princeton.EDU
 *  http://www.genomics.princeton.edu
 *  http://www.math.princeton.edu
 *  http://libweb.Princeton.EDU
 *  http://libweb2.princeton.edu
 *  http://www.acm.org
 *  ...
 *
 *
 *  Instead of setting the system property in the code, you could do it
 *  from the commandline
 *  % java -Dsun.net.client.defaultConnectTimeout=250 WebCrawler http://www.cs.princeton.edu

 *
 *************************************************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashSet;

public class WebCrawler { 


    public static void main(String[] args) { 

        // timeout connection after 500 miliseconds
        System.setProperty("sun.net.client.defaultConnectTimeout", "500");
        System.setProperty("sun.net.client.defaultReadTimeout",    "1000");

        // initial web page
        String s = args[0];

        // list of web pages to be examined
        Queue<String> q = new Queue<String>();
        q.enqueue(s);

        // existence symbol table of examined web pages
        HashSet<String> st = new HashSet<String>();
        st.add(s);

        // breadth first search crawl of web
        while (!q.isEmpty()) {
            String v = q.dequeue();
            System.out.println(v);

            In in = new In(v);

            // only needed in case website does not respond
            if (!in.exists()) continue;

            String input = in.readAll();

           /*************************************************************
            *  Find links of the form: http://xxx.yyy.zzz
            *  \\w+ for one or more alpha-numeric characters
            *  \\. for dot
            *  could take first two statements out of loop
            *************************************************************/
            String regexp = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);

            // find and print all matches
            while (matcher.find()) {
                String w = matcher.group();
                if (!st.contains(w)) {
                    q.enqueue(w);
                    st.add(w);
                }
            }

        }
   }
}

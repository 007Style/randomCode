
import java.io.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;
import java.lang.*;
import java.text.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class sDateTime
{	

    public static void main(String[] args) 
    {
        // Get the default MEDIUM/SHORT DateFormat
        DateFormat format =
            DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
            DateFormat.SHORT);

        // Read and parse input, stopping on a blank input line
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("ENTER DATE STRING: ");
            String dateString = reader.readLine();

            while ((dateString != null) && (dateString.length() > 0)) {
                // Parse the date
                try {
                    Date date = format.parse(dateString);
                    System.out.println("Original string: " + dateString);
                    System.out.println("Parsed date    : " +
                        date.toString());
                    System.out.println(); // Skip a line
                }
                catch(ParseException pe) {
                    System.out.println(
                        "ERROR: could not parse date in string \"" +
                        dateString + "\"");
                }

                // Read another string
                System.out.print("ENTER DATE STRING: ");
                dateString = reader.readLine();
            }
        }
        catch(IOException ioe) {
            System.out.println("I/O Exception: " + ioe);
        }
  }
	
	public void wanker() //public static void main(String[] args)
	{
		//System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
		//File f = new File("wank.java");
		//System.out.println("f.lastModified() = " + f.lastModified());
		/*
            //  **** IF FILE MORE THAN ONE DAY OLD, MOVE TO PROB DIR ****
            if((listOfFiles[i].lastModified()+86400000 < System.currentTimeMillis()) && (!ProgramConfig.getacornDEBUG()))
            {
                writeMsg("File: " + listOfFiles[i] + " is incomplete after 1 day, moving to PROBLEM DIR...");
                moveToProbDir(listOfFiles[i]);
                listOfFiles[i] = nullFile;
            }
            //  **** IF FILE MORE THAN ONE DAY OLD, MOVE TO PROB DIR ****
    */     
    
    Format formatter;
    
    // The year
    formatter = new SimpleDateFormat("yy");    // 02
    formatter = new SimpleDateFormat("yyyy");  // 2002
    
    // The month
    formatter = new SimpleDateFormat("M");     // 1
    formatter = new SimpleDateFormat("MM");    // 01
    formatter = new SimpleDateFormat("MMM");   // Jan
    formatter = new SimpleDateFormat("MMMM");  // January
    
    // The day
    formatter = new SimpleDateFormat("d");     // 9
    formatter = new SimpleDateFormat("dd");    // 09
    
    // The day in week
    formatter = new SimpleDateFormat("E");     // Wed
    formatter = new SimpleDateFormat("EEEE");  // Wednesday
    
    // Get today's date
    Date date = new Date();
    
    // Some examples
    formatter = new SimpleDateFormat("MM/dd/yy");
    String s = formatter.format(date);
    // 01/09/02
    
    formatter = new SimpleDateFormat("dd-MMM-yy");
    s = formatter.format(date);
    // 29-Jan-02
    
    // Examples with date and time; see also
    // BEGIN TIME
    //Format formatter;
    
    // The hour (1-12)
    formatter = new SimpleDateFormat("h");     // 8
    formatter = new SimpleDateFormat("hh");    // 08
    
    // The hour (0-23)
    formatter = new SimpleDateFormat("H");     // 8
    formatter = new SimpleDateFormat("HH");    // 08
    
    // The minutes
    formatter = new SimpleDateFormat("m");     // 7
    formatter = new SimpleDateFormat("mm");    // 07
    
    // The seconds
    formatter = new SimpleDateFormat("s");     // 3
    formatter = new SimpleDateFormat("ss");    // 03
    
    // The am/pm marker
    formatter = new SimpleDateFormat("a");     // AM
    
    // The time zone
    formatter = new SimpleDateFormat("z");     // PST
    formatter = new SimpleDateFormat("zzzz");  // Pacific Standard Time
    formatter = new SimpleDateFormat("Z");     // -0800
    
    // Get today's date
    //Date date = new Date();
    
    // Some examples
    formatter = new SimpleDateFormat("hh:mm:ss a");
    //String s = formatter.format(date);
    // 01:12:53 AM
    
    formatter = new SimpleDateFormat("HH.mm.ss");
    s = formatter.format(date);
    // 14.36.33
    // END TIME

    formatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    s = formatter.format(date);
    // 2002.01.29.08.36.33
    
    formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
    s = formatter.format(date);
    System.out.println(s);
    // Tue, 09 Jan 2002 22:14:02 -0500
        
        
    String myFrom = "Sep  4 17:58:33";
    String myTo   = "2007-04-23 14:21:52";
    //Date d = formatter.parse(myD, 0);
    
    //System.out.println(d);
    
    
	}
}

import java.io.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;
import java.lang.*;

public class wank
{	
	public static void main(String[] args)
	{
		System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
		File f = new File("wank.java");
		System.out.println("f.lastModified() = " + f.lastModified());
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
	    String errorMsg = "Dec 16 13:06:26 triggeremails acornPost6/smtp[11191]: C406C205C0B6: to=<gartland@sdb.k12.wi.us>, relay=mail.sdb.k12.wi.us[205.213.112.13], delay=3, status=bounced (host mail.sdb.k12.wi.us[205.213.112.13] said: 550-Verification failed for <janet-at-atd.com@triggeremails.com> 550-Called:   67.192.126.210 550-Sent:     RCPT TO:<janet-at-atd.com@triggeremails.com> 550-Response: 550 <janet-at-atd.com@triggeremails.com>: Recipient address rejected: User unknown in local recipient table 550-Sender verification failed (Sending test bounce to 550 janet-at-atd.com@triggeremails.com failed). (in reply to RCPT TO command))";
	    int indexEC = errorMsg.indexOf(" said: ");
            if(indexEC == -1)
            {
                System.out.println("-1");
            }
            else
            {
                int endexEC = errorMsg.indexOf(" ", indexEC+7);
                System.out.println(errorMsg.substring(indexEC+7, indexEC+10));
            }
	}
}
/*
 * mailPoster.java
 *
 * Created on November 6, 2006, 11:48 PM
 * Copyright (C) 2006, 2007, 2008 Trigger Email Marketing, LLC.
 *
 * Sends mail coordinated by the MailerD...
 *
 *mailerD
 *errorAlert
 *id  catch_id  cat_id  sev_id  error_name  error_desc  error_solutions  rc_datetime
 *1  1000  4  1  "mailerD Query Error" NULL  2007-09-05 23:55:59
 *2  2000  4  1  "mailerD General Error" NULL  2007-09-05 23:55:59
 *3  3000  4  1  "mailerD HOST_AUTH_SMTP Error" NULL  2007-09-05 23:55:59
 *4  4000  4  1  "mailerD HOST_NO_AUTH_SMTP Error" NULL  2007-09-05 23:55:59
 *5  5000  4  1  "mailerD acorn_SMTP Error" NULL  2007-09-05 23:55:59
 *6  6000  4  1  "mailerD CLIENT_SMTP Error" NULL  2007-09-05 23:55:59
 *7  7000  4  1  "mailerD mailLoggerD General Error" NULL  2007-09-05 23:55:59
 */

package mailTester;

import java.net.URL;
import java.sql.*;
import sun.jdbc.odbc.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.*;


/**
 *
 * @author dsingley
 */
public class mailPoster extends Thread {
    
    private boolean tEnd = false;
    private String[] toaddr = null;
    private String[] bcc = null;
    private String replytoaddr = null;
    private String subject = null;
    private String template = null;
    private String fromaddr = null;
    private String smtpserver = null;
    private String smtpport = null;
    private String emailformat = null;
    private String username = null;
    private String password = null;
    private String sender = null;
    private String rPath = null;
    private String listUnsub = null;
    private String[] idA = null;
    private byte[] subjectBytes = null;
    private int mailer = 0;
    public static final int HOST_AUTH_SMTP = 1;
    public static final int HOST_NO_AUTH_SMTP = 2;
    public static final int acorn_SMTP = 3;
    public static final int CLIENT_SMTP = 4;
    private mailEngine mE = null;
    private fileWriter fW = null;
    private mailTester mT = null;
    
    /**
     * Creates a new instance of mailPoster
     */
    public mailPoster(mailTester mt, int m, String[] taddr, String[] tbcc, String raddr, String sub, String temp, String faddr, String port, String smtpS, String eformat, String uName, String pWord, fileWriter f, String s, String rp, String lU){
        mT = mt;
        toaddr = taddr;
        bcc = tbcc;
        replytoaddr = raddr;
        //subject = sub;
        subject = "Hi Pete \u261B";
        try{ subjectBytes = subject.getBytes("UTF8"); } catch( Exception e) {}
        subject = "" + subjectBytes;
        subject = "hi pete \u261b";
        subject = "hi pete &#261B&#261B&#261B&#261B &#347 &lt;Fran&ccedil;ais&gt; &#9755 &#261D";
        //System.out.println(subject);
        String bmine = StringEscapeUtils.unescapeJava(subject);
        //bmine = new String(subject, "UTF-8");
        //System.out.println(bmine);
        bmine = html2UTF8(bmine);
        //System.out.println(bmine);
        subject = bmine;
        template = temp;
        fromaddr = faddr;
        smtpserver = smtpS;
        smtpport = port;
        emailformat = eformat;
        username = uName;
        password = pWord;
        mailer = m;
        sender = s;
        rPath = rp;
        listUnsub = lU;
        fW = f;
        mE = new mailEngine();
        fW = f;
        //fW = new fileWriter();
        //fW.createFile("LOGS/mailerD.log");
    }

    public String html2UTF8(String input)
    {
        //System.out.println("XXXX " + input);
        int occurance = StringUtils.countMatches(input, "&#x");
        if(occurance > 0)
        {
            input = input.replace("&#x2600;", "\u2600");  //Black Sun With Rays
            input = input.replace("&#x2601;", "\u2601");  //Cloud
            input = input.replace("&#x2602;", "\u2602");  //Umbrella
            input = input.replace("&#x2603;", "\u2603");  //Snowman
            input = input.replace("&#x2604;", "\u2604");  //Comet
            input = input.replace("&#x2605;", "\u2605");  //Black Star
            input = input.replace("&#x2606;", "\u2606");  //White Star
            input = input.replace("&#x2607;", "\u2607");  //Lightning
            input = input.replace("&#x2608;", "\u2608");  //Thunderstorm
            input = input.replace("&#x2609;", "\u2609");  //Sun
            input = input.replace("&#x260A;", "\u260A");  //Ascending Node
            input = input.replace("&#x260B;", "\u260B");  //Descending Node
            input = input.replace("&#x260C;", "\u260C");  //Conjunction
            input = input.replace("&#x260D;", "\u260D");  //Opposition
            input = input.replace("&#x260E;", "\u260E");  //Black Telephone
            input = input.replace("&#x260F;", "\u260F");  //White Telephone
            input = input.replace("&#x2610;", "\u2610");  //Ballot Box
            input = input.replace("&#x2611;", "\u2611");  //Ballot Box With Check
            input = input.replace("&#x2612;", "\u2612");  //Ballot Box With X
            input = input.replace("&#x2613;", "\u2613");  //Saltire
            input = input.replace("&#x261A;", "\u261A");  //Black Left Pointing Index
            input = input.replace("&#x261B;", "\u261B");  //Black Right Pointing Index
            input = input.replace("&#x261C;", "\u261C");  //White Left Pointing Index
            input = input.replace("&#x261D;", "\u261D");  //White Up Pointing Index
            input = input.replace("&#x261E;", "\u261E");  //White Right Pointing Index
            input = input.replace("&#x261F;", "\u261F");  //White Down Pointing Index
            input = input.replace("&#x2620;", "\u2620");  //Skull And Crossbones
            input = input.replace("&#x2621;", "\u2621");  //Caution Sign
            input = input.replace("&#x2622;", "\u2622");  //Radioactive Sign
            input = input.replace("&#x2623;", "\u2623");  //Biohazard Sign
            input = input.replace("&#x2624;", "\u2624");  //Caduceus
            input = input.replace("&#x262E;", "\u262E");  //Peace Symbol
            input = input.replace("&#x262F;", "\u262F");  //Yin Yang
            input = input.replace("&#x2639;", "\u2639");  //White Frowning Face
            input = input.replace("&#x263A;", "\u263A");  //White Smiling Face
            input = input.replace("&#x263B;", "\u263B");  //Black Smiling Face
            input = input.replace("&#x263C;", "\u263C");  //White Sun With Rays
            input = input.replace("&#x263D;", "\u263D");  //First Quarter Moon
            input = input.replace("&#x263E;", "\u263E");  //Last Quarter Moon
            input = input.replace("&#x2640;", "\u2640");  //Female Sign
            input = input.replace("&#x2641;", "\u2641");  //Earth
            input = input.replace("&#x2642;", "\u2642");  //Male Sign
            input = input.replace("&#x2660;", "\u2660");  //Black Spade Suite
            input = input.replace("&#x2661;", "\u2661");  //White Heart Suit
            input = input.replace("&#x2662;", "\u2662");  //White Diamond Suit
            input = input.replace("&#x2663;", "\u2663");  //Black Club Suit
            input = input.replace("&#x2664;", "\u2664");  //White Spade Suit
            input = input.replace("&#x2665;", "\u2665");  //Black Heart Suit
            input = input.replace("&#x2666;", "\u2666");  //Black Diamond Suit
            input = input.replace("&#x2667;", "\u2667");  //White Club Suit
            input = input.replace("&#x2669;", "\u2669");  //Quarter Note
            input = input.replace("&#x266A;", "\u266A");  //Eight Note
            input = input.replace("&#x266B;", "\u266B");  //Beamed Eighth Notes
            input = input.replace("&#x266C;", "\u266C");  //Beamed Sixteenth Notes
            input = input.replace("&#x266D;", "\u266D");  //Music Flat Sign
            input = input.replace("&#x266E;", "\u266E");  //Music Natural Sign
            input = input.replace("&#x266F;", "\u266F");  //Music Sharp Sign
            input = input.replace("&#x2713;", "\u2713");  //Check Mark
            input = input.replace("&#x274B;", "\u274B");  //Heavy Eight Teardrop-Spoked Propeller Asterisk
            input = input.replace("&#x00BB;", "\u00BB");  //>>
        }
        return input;
    }
    
    public mailPoster(){}
    
    public void writeMsg(String msg)
    {
        fW.writeFileFixed(msg);
    }
    
    public void writeMsgPlus(String msg)
    {
        writeMsg(msg);
        System.out.print(msg); System.out.flush();
    }
    
    public void writeMsgError(String msg)
    {
        writeMsg(msg);
    }

    private void writeMailMsg(String wMail){}
    private void writeMailMsg2(String wMail)
    {
        String out = "";
        if(toaddr != null)
        {
            out = fW.timeDate() + " " + wMail + " - Sent mail TO:";
            for(int i=0; i<toaddr.length; i++)
            {
                out = out + " " + toaddr[i];
            }
        }
        if(bcc != null)
        {
            out = out + " || " + wMail + " - Sent mail BCC:";
            for(int i=0; i<bcc.length; i++)
            {
                out = out + " " + bcc[i];
            }
        }
        if((toaddr != null) || (bcc != null)){ writeMsg(out + "\n"); }
    }
    
    public void threadEnd(){
        tEnd = true;
    }
    
    public void threadWork()
    {
        //System.out.println("mailPoster posting mail to: " + toaddr[0]);
        switch(mailer)
        {
            case HOST_AUTH_SMTP:
                //System.out.println("HOST_AUTH_SMTP");
                try
                {
                    //mE.postMail(toaddr, bcc, replytoaddr, subject, template, fromaddr, smtpserver, username, password, smtpport, emailformat, sender, rPath, listUnsub);
                    writeMailMsg("HOST_AUTH_SMTP");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    String error = (fW.timeDate() + " mailPoster HOST_AUTH_SMTP Error - " + e + "\n");
                    writeMsgPlus(error);
                }
                break;
            case HOST_NO_AUTH_SMTP:
                //System.out.println("HOST_NO_AUTH_SMTP");
                try
                {
                    //(String sender, String rPath, String recipients[ ], String[] bcc, String replyaddr, String subject, String message , String from, String smtp, String smtpPort, String eFormat, String message_id, Date dateTime, String listUnsub)
                    mE.postMail(sender, rPath, toaddr, bcc, replytoaddr, subject, template, fromaddr, smtpserver, smtpport, emailformat, null, listUnsub);
                    writeMailMsg("HOST_NO_AUTH_SMTP");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    String error = (fW.timeDate() + " HOST_NO_AUTH_SMTP Error - " + e + "\n");
                    writeMsgPlus(error);
                }
                break;
            case acorn_SMTP:
                //System.out.println("HOST_NO_AUTH_SMTP");
                try
                {
                    //mE.postMail(toaddr, bcc, replytoaddr, subject, template, fromaddr, emailformat, sender, rPath, listUnsub);
                    writeMailMsg("acorn_SMTP");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    String error = (fW.timeDate() + " mailPoster acorn_SMTP Error - " + e + "\n");
                    writeMsgPlus(error);
                }
                break;
            case CLIENT_SMTP:
                //System.out.println("CLIENT_SMTP");
                try
                {
                    //mE.postMail(toaddr, bcc, replytoaddr, subject, template, fromaddr, smtpserver, username, password, "25", emailformat, sender, rPath, listUnsub);
                    writeMailMsg("CLIENT_SMTP");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    String error = (fW.timeDate() + " mailPoster CLIENT_SMTP Error - " + e + "\n");
                    writeMsgPlus(error);
                }
                break;
        }
    }
    
    public void run()
    {
        if(!tEnd)
        {
            threadWork();
            mT.threadDown();
        }    
    }
}

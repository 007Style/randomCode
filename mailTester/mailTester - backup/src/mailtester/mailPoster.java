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
    private String[] idA = null;
    private int mailer = 0;
    public static final int HOST_AUTH_SMTP = 1;
    public static final int HOST_NO_AUTH_SMTP = 2;
    public static final int acorn_SMTP = 3;
    public static final int CLIENT_SMTP = 4;
    private mailEngine mE = null;
    private fileWriter fW = null;
    
    /**
     * Creates a new instance of mailPoster
     */
    public mailPoster(int m, String[] taddr, String[] tbcc, String raddr, String sub, String temp, String faddr, String port, String smtpS, String eformat, String uName, String pWord, fileWriter f, String s, String rp){        
        toaddr = taddr;
        bcc = tbcc;
        replytoaddr = raddr;
        subject = sub;
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
        fW = f;
        mE = new mailEngine();
        fW = f;
        //fW = new fileWriter();
        //fW.createFile("LOGS/mailerD.log");
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
    
    private void writeMailMsg(String wMail)
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
        if((toaddr != null) || (bcc != null)){ writeMsgPlus(out + "\n"); }
    }
    
    public void threadEnd(){
        tEnd = true;
    }
    
    private void threadWork()
    {
        System.out.println("mailPoster posting mail to: " + toaddr[0]);
        switch(mailer)
        {
            case HOST_AUTH_SMTP:
                System.out.println("HOST_AUTH_SMTP");
                try
                {
                    //mE.postMail(toaddr, bcc, replytoaddr, subject, template, fromaddr, smtpserver, username, password, smtpport, emailformat, sender, rPath);
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
                System.out.println("HOST_NO_AUTH_SMTP");
                try
                {
                    mE.postMail(toaddr, bcc, replytoaddr, subject, template, fromaddr, smtpserver, smtpport, emailformat, sender, rPath);
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
                System.out.println("HOST_NO_AUTH_SMTP");
                try
                {
                    //mE.postMail(toaddr, bcc, replytoaddr, subject, template, fromaddr, emailformat, sender, rPath);
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
                System.out.println("CLIENT_SMTP");
                try
                {
                    //mE.postMail(toaddr, bcc, replytoaddr, subject, template, fromaddr, smtpserver, username, password, "25", emailformat, sender, rPath);
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
        }    
    }
}

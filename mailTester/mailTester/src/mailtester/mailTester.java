/*
 * mailTester.java
 *
 * Created on January 30, 2008, 8:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mailTester;

import java.io.*;
import java.util.*;
import java.text.*;
import java.util.concurrent.*;

/**
 *
 * @author dsingley
 */
public class mailTester {
    private final Semaphore availableMailSet = new Semaphore(1, true);
    private int threadNum = 0;
    
    public String setTemp()
    {
        String temp = "";
        return temp;
                
    }
    
    private String tempDataRead()
    {
        synchronized(this)
        {
            try
            {
                FileInputStream fis = new FileInputStream("./template.txt");
                DataInputStream in = new DataInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String contents = "";
                String line = "";
                while (( line = br.readLine()) != null){
                    //contents.concat(line);
                    //contents.concat(System.getProperty("line.separator"));
                    contents = contents + "\n" + line;
                }
                
                in.close();
                //System.out.println("TEMPLATE: \n" + contents);
                return contents;
            }
            catch(Exception e)
            {
                e.printStackTrace(); 
                return null;
            }   
        }
    }

    public synchronized int threadGet()
    {
        availableMailSet.acquireUninterruptibly();
        int num = threadNum;
        availableMailSet.release();
        return num;
    }

    public synchronized void threadUp()
    {
        availableMailSet.acquireUninterruptibly();
        threadNum++;
        availableMailSet.release();
    }

    public synchronized void threadDown()
    {
        availableMailSet.acquireUninterruptibly();
        threadNum--;
        availableMailSet.release();
    }
    
    /** Creates a new instance of mailTester */
    public mailTester() {
        mailEngine mE = new mailEngine();
        fileWriter fW = new fileWriter();
        fW.createFile("./mailer.log");
        String[] taddr = new String[1];
        //String[] taddr1 = new String[1];
        //taddr1[0] = "peter@fiddleheadconsultants.com";
        String[] tbcc = null;
        taddr[0] = "peter@fiddleheadconsultants.com";  // "test@dkimtest.jason.long.name";  //"dsingley@fiddleheadconsultants.com"; //taddr[1] = "brian.maples@rackspace.com";
        String raddr = "postmaster@trig6.trig-mailer.com"; //postmaster@triggeremails.com";
        String sub = "postfixGold TEST MAIL (67.192.93.232, trig7.trig-mailer.com)";
        String temp = "Trigger Email Marketing, LLC.";
        String faddr = "postmaster@trig7.trig-mailer.com";
        String port = "25";  //"587";  //25";
        String smtpS = "67.192.114.35";  //"67.192.93.232";
        String eformat = "text/plain";
        String uName = "";
        String pWord = "";
        String send = "";
        String rPath = "";
        String listUnsub = "";
        
        try
        {
            new ProgramConfig("./config.txt");
        }
        catch(Exception e){}
        uName     = ProgramConfig.getsmtpUserName();
        pWord     = ProgramConfig.getsmtpPassWord();
        smtpS     = ProgramConfig.getsmtpServerName();
        port      = ProgramConfig.getsmtpPort();
        faddr     = ProgramConfig.getfromAddr();
        raddr     = ProgramConfig.getreplyAddr();
        taddr[0]  = ProgramConfig.gettoAddr();
        temp      = ProgramConfig.gettemplate();
        sub       = ProgramConfig.getsubject();
        send      = ProgramConfig.getsender();
        rPath     = ProgramConfig.getreturnpath();
        //listUnsub = ProgramConfig.getlistUnsub();
        listUnsub = null;
        
        temp = tempDataRead();
        
        if(send.equals("")){ send = null; }
        ProgramConfig.store();
        
        mailPoster mP1 = new mailPoster(this, 2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP1.threadWork();
        for(int i=0; i < 10000; i++)
        {
            System.out.println("MAIL YES Send - " + i);
            mP1 = new mailPoster(this, 2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP1.threadWork();
        }
        /*
        for(int i=0; i<100; i++)
        {
            for(int j=0; j<100; j++)
            {
                if(threadGet() < 16)
                {
                    (new mailPoster(this, 2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub)).start();
                    threadUp();
                }
            }
            System.out.println("****====**** SENT " + i + " x100 mailings");
        }
         */
        /*
        mailPoster mP2 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP2.start();
        mailPoster mP3 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP3.start();
        mailPoster mP4 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP4.start();
        mailPoster mP5 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP5.start();
        mailPoster mP6 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP6.start();
        mailPoster mP7 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP7.start();
        mailPoster mP8 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP8.start();
        mailPoster mP9 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP9.start();
        mailPoster mP10 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP10.start();
        mailPoster mP11 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP11.start();
        mailPoster mP12 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP12.start();
        mailPoster mP13 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP13.start();
        mailPoster mP14 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP14.start();
        mailPoster mP15 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP15.start();
        mailPoster mP16 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP16.start();
        mailPoster mP17 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP17.start();
        mailPoster mP18 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP18.start();
        mailPoster mP19 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP19.start();
        mailPoster mP20 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub);  mP20.start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW, send, rPath, listUnsub).start();
        */
        
        
        
        /*
        sub = "Postfix Server #2 TEST (67.192.114.34)";
        smtpS = "67.192.114.34";
        mailPoster mP2 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW);  mP2.start();
        */
        //sub = "dkim test";
        //smtpS = "67.192.93.230";
        //faddr = "dsingley@fiddleheadconsultants.com";
        //raddr = "dsingley@fiddleheadconsultants.com";
        //taddr[0] = "test@test.trig-mailer.com";
        //mailPoster mP1 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW);  mP1.start();
        
        //mailPoster mP2 = new mailPoster(2, taddr1, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW);  mP2.start();
        /*
        sub = "Postfix Server #1 TEST (67.192.126.210)";
        smtpS = "67.192.114.36";
        mailPoster mP5 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW);  mP5.start();
        
        sub = "Postfix Server #1 TEST (67.192.126.210)";
        smtpS = "67.192.114.36";
        mailPoster mP6 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW);  mP6.start();
        */
         
        /*
        sub = "Postfix Server #3 TEST (67.192.114.35)";
        smtpS = "67.192.114.35";
        mailPoster mP3 = new mailPoster(2, taddr, tbcc, raddr, sub, temp, faddr, port, smtpS, eformat, uName, pWord, fW);  mP3.start();
        */
        //mE.postMail(taddr, tbcc, raddr, String subject, String message , String from, String smtp, String smtpPort, String eFormat)
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("*****************  mailTester v1.18  *****************");  System.out.flush();
        System.out.println("**** modify config.txt properties to deploy test ****");  System.out.flush();
        System.out.println("*****************  mailTester v1.18  *****************");  System.out.flush();
        mailTester mT = new mailTester();
    }

}

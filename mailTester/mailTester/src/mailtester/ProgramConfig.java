/*
 * ProgramConfig.java
 *
 * Copyright (C) 2006, 2007, 2008 Trigger Email Marketing, LLC.
 */

package mailTester;

import java.util.*;
import java.io.*;

public class ProgramConfig extends Properties
{
    public static ProgramConfig m_this;
    public static String m_configFileName;
    
    // Creates new ProgramConfig 
    public ProgramConfig(String configFileName) throws Exception
    {
        m_configFileName = configFileName;
        
        File configFile = new File(configFileName);
        String sprop;
        
        try 
        {
            InputStream is = new FileInputStream(configFile);
            load(is);
        } 
        catch (Exception e) {}
        
        sprop = getProperty("template");
        if(sprop == null) {
            setProperty("template", "" + m_template);
        } else {
            m_template = sprop;
        } 
        
        sprop = getProperty("subject");
        if(sprop == null) {
            setProperty("subject", "" + m_subject);
        } else {
            m_subject = sprop;
        } 
        
        sprop = getProperty("fromAddr");
        if(sprop == null) {
            setProperty("fromAddr", "" + m_fromAddr);
        } else {
            m_fromAddr = sprop;
        } 
        
        sprop = getProperty("replyAddr");
        if(sprop == null) {
            setProperty("replyAddr", "" + m_replyAddr);
        } else {
            m_replyAddr = sprop;
        } 
        
        sprop = getProperty("toAddr");
        if(sprop == null) {
            setProperty("toAddr", "" + m_toAddr);
        } else {
            m_toAddr = sprop;
        } 
        
        sprop = getProperty("listUnsub");
        if(sprop == null) {
            setProperty("listUnsub", "" + m_listUnsub);
        } else {
            m_listUnsub = sprop;
        } 
        
        sprop = getProperty("smtpServerName");
        if(sprop == null) {
            setProperty("smtpServerName", "" + m_smtpServerName);
        } else {
            m_smtpServerName = sprop;
        } 
        
        sprop = getProperty("smtpUserName");
        if(sprop == null) {
            setProperty("smtpUserName", "" + m_smtpUserName);
        } else {
            m_smtpUserName = sprop;
        } 
        
        sprop = getProperty("smtpPassWord");
        if(sprop == null) {
            setProperty("smtpPassWord", "" + m_smtpPassWord);
        } else {
            m_smtpPassWord = sprop;
        } 
        
        sprop = getProperty("smtpPort");
        if(sprop == null) {
            setProperty("smtpPort", "" + m_smtpPort);
        } else {
            m_smtpPort = sprop;
        } 
        
        sprop = getProperty("listUnsubscribe_Domain");
        if(sprop == null) {
            setProperty("listUnsubscribe_Domain", "" + m_listUnsubscribe_Domain);
        } else {
            m_listUnsubscribe_Domain = sprop;
        } 
        
        sprop = getProperty("perfTest");
        if(sprop == null) {
            setProperty("perfTest", "" + m_perfTest); }
        else if(sprop != null && sprop.equalsIgnoreCase("true")) {
            m_perfTest = true;
        }
        else if(sprop != null && sprop.equalsIgnoreCase("false")) {
            m_perfTest = false;
        }
        else{ throw new Exception("'perfTest' parm != true/false"); }
        
        sprop = getProperty("sender");
        if(sprop == null) {
            setProperty("sender", "" + m_sender);
        } else {
            m_sender = sprop;
        } 
        
        sprop = getProperty("return-path");
        if(sprop == null) {
            setProperty("return-path", "" + m_returnpath);
        } else {
            m_returnpath = sprop;
        } 
        
        m_this = this;
        if(!configFile.exists()) {
            //System.err.println("Error: config file missing: " + configFileName);
            //System.exit(1);
        }
  
        store();
    }

    public static void store() 
    {
        File configFile = new File(m_configFileName);
        try {
            OutputStream os = new FileOutputStream(configFile);
            m_this.store(os, "mailTester properties file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String m_template = "Trigger Email Marketing, LLC.";
    public static String gettemplate() { return m_template; }
    public static void settemplate(String name) { m_template = name; m_this.setProperty("template", "" + name); }
    
    private static String m_subject = "postfixGold TEST MAIL (67.192.93.232, trig7.trig-mailer.com)";
    public static String getsubject() { return m_subject; }
    public static void setsubject(String name) { m_subject = name; m_this.setProperty("subject", "" + name); }
    
    private static String m_fromAddr = "dsingley@triggeremails.com";
    public static String getfromAddr() { return m_fromAddr; }
    public static void setfromAddr(String name) { m_fromAddr = name; m_this.setProperty("from_addr", "" + name); }
    
    private static String m_replyAddr = "dsingley@triggeremails.com";
    public static String getreplyAddr() { return m_replyAddr; }
    public static void setreplyAddr(String name) { m_replyAddr = name; m_this.setProperty("reply_addr", "" + name); }
    
    private static String m_toAddr = "dsingley@fiddleheadconsultants.com";
    public static String gettoAddr() { return m_toAddr; }
    public static void settoAddr(String name) { m_toAddr = name; m_this.setProperty("to_addr", "" + name); }
    
    private static String m_listUnsub = "<http://www.triggeremails.com/public/redirect.php?c=50&cs=57fee5a95bfa81d1e4eecbe40189b12d&thurl=ed0b8cf98095468796d056e52bceb9cc1dee3305cfb6aa08d824e22ad090acf3f1866d110f81ce63e3569b2bcc68391d219837bd9f95826748f9b7c52bed6052d685286997f6ebe0adc69bb66bff009b5166bd18d3fbb43bad9e084c569f4c7474f2b3eb6ee9f08e26944d95cdbb8563c080b93c62e2b4cac041c7a8dc6505d4dd0e4c7b8dc43c06131befe95b29762add6d0f6e36a931f4d1fcec75216d451c613ad508328dd6c517212e0a65df9f53ba7f13d7c64784641519cc7c98e41e8d5adfe4481826fd7ff0bab5510f8c051e33a10f7a565205d51a301f8d8a2fe5d4f9f2f1e26cf256b7eb565dd5f7d2e3cd445bd89c5c3ab9519c5a78b40d1ed70e186c0945a42371b9962af552fdfacb853bb2a99989975c673f09553d5387987b>";
    public static String getlistUnsub() { return m_listUnsub; }
    public static void setlistUnsub(String name) { m_listUnsub = name; m_this.setProperty("listUnsub", "" + name); }
    
    private static String m_smtpServerName = "67.192.93.232";
    public static String getsmtpServerName() { return m_smtpServerName; }
    public static void setsmtpServerName(String name) { m_smtpServerName = name; m_this.setProperty("smtpServerName", "" + name); }
    
    private static String m_smtpUserName = "";
    public static String getsmtpUserName() { return m_smtpUserName; }
    public static void setsmtpUserName(String name) { m_smtpUserName = name; m_this.setProperty("smtpUserName", "" + name); }
    
    private static String m_smtpPassWord = "";
    public static String getsmtpPassWord() { return m_smtpPassWord; }
    public static void setsmtpPassWord(String name) { m_smtpPassWord = name; m_this.setProperty("smtpPassWord", "" + name); }
    
    private static String m_messageID_Domain = "triggeremails.com";
    public static String getmessageID_Domain() { return m_messageID_Domain; }
    public static void setmessageID_Domain(String name) { m_messageID_Domain = name; m_this.setProperty("m_messageID_Domain", "" + name); }
    
    private static String m_listUnsubscribe_Domain = "list-unsubscribe.triggeremails.com";
    public static String getlistUnsubscribe_Domain() { return m_listUnsubscribe_Domain; }
    public static void setlistUnsubscribe_Domain(String name) { m_listUnsubscribe_Domain = name; m_this.setProperty("m_listUnsubscribe_Domain", "" + name); }
    
    private static boolean m_perfTest = false;
    public static boolean getperfTest() { return m_perfTest; }
    public static void setperfTest(boolean b) { m_perfTest = b; m_this.setProperty("perfTest", "" + b); }
    
    private static String m_smtpPort = "25";
    public static String getsmtpPort() { return m_smtpPort; }
    public static void setsmtpPort(String name) { m_smtpPort = name; m_this.setProperty("smtpPort", "" + name); }
    
    private static String m_sender = "";
    public static String getsender() { return m_sender; }
    public static void setsender(String name) { m_sender = name; m_this.setProperty("sender", "" + name); }
    
    private static String m_returnpath = "";
    public static String getreturnpath() { return m_returnpath; }
    public static void setreturnpath(String name) { m_returnpath = name; m_this.setProperty("return-path", "" + name); }
}
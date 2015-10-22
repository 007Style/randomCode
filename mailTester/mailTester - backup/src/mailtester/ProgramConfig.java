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
    
    private static String m_smtpServerName = "67.192.93.232";
    public static String getsmtpServerName() { return m_smtpServerName; }
    public static void setsmtpServerName(String name) { m_smtpServerName = name; m_this.setProperty("smtpServerName", "" + name); }
    
    private static String m_smtpUserName = "";
    public static String getsmtpUserName() { return m_smtpUserName; }
    public static void setsmtpUserName(String name) { m_smtpUserName = name; m_this.setProperty("smtpUserName", "" + name); }
    
    private static String m_smtpPassWord = "";
    public static String getsmtpPassWord() { return m_smtpPassWord; }
    public static void setsmtpPassWord(String name) { m_smtpPassWord = name; m_this.setProperty("smtpPassWord", "" + name); }
    
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
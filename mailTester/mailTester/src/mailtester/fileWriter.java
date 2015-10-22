/*
 * fileWriter.java
 *
 * Created on October 30, 2006, 11:14 PM
 * Copyright (C) 2006, 2007, 2008 Trigger Email Marketing, LLC.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
 * @author d
 */
public class fileWriter {
    
    PrintWriter dout = null;
    private String writeString = "";
    private int writeLines = 0;
    private String file = "";
    private int fileLines = 10000;
    private File openedFile = null;
    
    /** Creates a new instance of fileWriter */
    public fileWriter(){}
    
    private void openFile(String s)
    {
        openedFile = new File(s);
    }
    
    public String readFile(String s) throws Exception
    {
        String fileTXT = "";
        try
        {
            FileInputStream fstream = new FileInputStream(s);
            BufferedReader d = new BufferedReader(new InputStreamReader(fstream));
            String fline = d.readLine();
            while (fline != null)
            {
                fline = fline + "\n";
                fileTXT = fileTXT + fline;
                fline = d.readLine();
            }
            d.close();
        }catch (Exception e){ throw e; }
        return fileTXT;
    }
    
    public void writeFile(String fileName, String contents) throws Exception
    {
        synchronized(this)
        {
            try
            {
                BufferedWriter d = new BufferedWriter(new FileWriter(fileName));
                d.write(contents);
                d.close();
            }catch (Exception e){ throw e; }
        }
    }
    
    public fileWriter(String s) 
    {
        createFile(s);
    }
    
    public void writeFile(String l)
    {
        synchronized(this)
        {
            dout.println(l);
            dout.flush();
        }
    }
    
    public void writeFileFixed(String line)
    {
        synchronized(this) 
        {
            closeFile();
            createFile(file);
            int index = writeString.length();
            int lineNums = 0;
            writeString += line;
            while((10000 > lineNums) && (index != -1))
            {
                index = writeString.lastIndexOf("\n", index-1);
                lineNums++;
            }
            if(index != -1) 
            {
                writeString = writeString.substring(index+1);
            }
            dout.println(writeString);
            dout.flush();
        }
    }
    
    public void writeFileFixed_Old(String line)
    {
        synchronized(this) 
        {
            closeFile();
            createFile(file);
            if(writeLines < fileLines)
            { 
               writeLines++; 
            }
            else
            {
                int index = writeString.indexOf("\n");
                writeString = writeString.substring(index+2);
            }
            writeString = writeString + line;
            dout.println(writeString);
            dout.flush();
        }
    }
    
    public String timeDate()
    {
        synchronized(this)
        {
            Calendar time = Calendar.getInstance();
            SimpleDateFormat fmtYMD = new SimpleDateFormat("yyyyMMdd");
            int timerYMD = Integer.parseInt(fmtYMD.format(time.getTime()));
            SimpleDateFormat fmtHMS = new SimpleDateFormat("HHmmss");
            int timer = Integer.parseInt(fmtHMS.format(time.getTime()));
            return fmtYMD.format(new java.util.Date()) + "+" + fmtHMS.format(new java.util.Date()) + ": ";
        }
    }
    
    public String datePlusTime()
    {
        synchronized(this)
        {
            Calendar time = Calendar.getInstance();
            SimpleDateFormat fmtYMD = new SimpleDateFormat("yyyyMMdd");
            int timerYMD = Integer.parseInt(fmtYMD.format(time.getTime()));
            SimpleDateFormat fmtHMS = new SimpleDateFormat("HHmmss");
            int timer = Integer.parseInt(fmtHMS.format(time.getTime()));
            return fmtYMD.format(new java.util.Date()) + "+" + fmtHMS.format(new java.util.Date());
        }
    }
    
    public void createFile(String filename)
    {
        synchronized(this)
        {
            file = filename;
            try
            {
                dout = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            }
            catch(Exception exc) {System.out.println(exc.toString());}
        }
    }
  
    public void closeFile()
    {
        synchronized(this)
        {
            if(dout != null)
            {
                dout.close();
                dout = null;
            }
        }
    }
}

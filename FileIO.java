/*
 * FileIO.java
 *
 * Created on September 4, 2007, 5:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package acorn;

import java.io.*;
import java.util.*;

/**
 *
 * @author dsingley
 */
public class FileIO
{
    public static void main(String[] args)
    {
        ArrayList bob = loadFile(args[0]);
        for (int i=0; i<bob.size(); i++)
            System.out.println(i+1 + ":\t" + bob.get(i));
    }

    public static ArrayList loadFile(String fileName)
    {
        if ((fileName == null) || (fileName == ""))
            throw new IllegalArgumentException();
        
        String line;
        ArrayList file = new ArrayList();

        try
        {    
            BufferedReader in = new BufferedReader(new FileReader(fileName));

            if (!in.ready())
                throw new IOException();

            while ((line = in.readLine()) != null)
                file.add(line);

            in.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
            return null;
        }

        return file;
    }
}
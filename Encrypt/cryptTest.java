import java.*;
import java.lang.*;
import java.util.*;

import java.security.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class cryptTest
{
	public static void main(String[] args) 
	{
		try
		{
			String text = "a:5:{s:8:\"clientid\";s:2:\"39\";s:10:\"client_key\";s:10:\"2174576881\";s:3:\"mid\";s:1:\"1\";s:9:\"emailaddr\";s:16:\"endo22@wacko.net\";s:8:\"url_fact\";s:20:\"http://www.yahoo.com\";}";
			byte[] b = encrypt("21745768", text);
			//System.out.println("byte[] to string: " + b.toString());
			String bHex = bytesToHex(b);
			//System.out.println("String bHex length: " + bHex.length());
			System.out.println(bHex);
			
			byte[] bts = new byte[bHex.length() / 2];
			for (int i = 0; i < bts.length; i++) 
			{
				bts[i] = (byte) Integer.parseInt(bHex.substring(2*i, 2*i+2), 16);
			}

			String answer = decrypt("21745768", bts);
			System.out.println(answer);
			String md5 = MD5(text);
			System.out.println("md5: " + md5);
		}
		catch(Exception e){ e.printStackTrace(); }
	}
	
	public static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException  
	{
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] md5hash = new byte[32];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5hash = md.digest();
		return convertToHex(md5hash);
	}

	private static String convertToHex(byte[] data) 
	{
  	StringBuffer buf = new StringBuffer();
    for (int i = 0; i < data.length; i++) 
    {
    	int halfbyte = (data[i] >>> 4) & 0x0F;
      int two_halfs = 0;
      do 
      {
	    	if ((0 <= halfbyte) && (halfbyte <= 9))
	      	buf.append((char) ('0' + halfbyte));
	      else
	        buf.append((char) ('a' + (halfbyte - 10)));
	      halfbyte = data[i] & 0x0F;
      } 
      while(two_halfs++ < 1);
    }
    return buf.toString();
  }

 
 	/**
	* Convenience method to convert a byte to a hex string.
	*
	* @param data the byte to convert
	* @return String the converted byte
	*/
	public static String byteToHex(byte data)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(toHexChar((data>>>4)&0x0F));
		buf.append(toHexChar(data&0x0F));
		return buf.toString();
	}

	/**
	* Convenience method to convert a byte array to a hex string.
	*
	* @param data the byte[] to convert
	* @return String the converted byte[]
	*/
	public static String bytesToHex(byte[] data)
	{
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++)
		{
			buf.append(byteToHex(data[i]));
		}
		return buf.toString();
	}

	/**
	* Convenience method to convert an int to a hex char.
	*
	* @param i the int to convert
	* @return char the converted char
	*/
	public static char toHexChar(int i)
	{
		if ((0 <= i) && (i <= 9 ))
			return (char)('0' + i);
		else
			return (char)('a' + (i-10));
	} 
 
  /**
   * encrypt
   */
  private static byte[] encrypt(String key, String text) throws Exception {
    //Get Key From Out
    SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "DES");
    Cipher cipher = Cipher.getInstance("DES");
    cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, sksSpec);
    byte[] encrypted = cipher.doFinal(text.getBytes());
    return encrypted;
  }
 
  /**
   * decrypt
   */
  private static String decrypt(String key, byte[] encrypted) throws Exception {    
    SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "DES");
    Cipher cipher = Cipher.getInstance("DES");
    cipher.init(javax.crypto.Cipher.DECRYPT_MODE, sksSpec);
    byte[] decrypted = cipher.doFinal(encrypted);
    return new String(decrypted);
  }

}


/*
public class Hex
    {
    // Converts a string of hex digits into a byte array of those digits
    static public byte[] toByteArr(String no)
        {
        byte[] number = new byte[no.length()/2];
        int i;
        for (i=0; i<no.length(); i+=2)
            {
            int j = Integer.parseInt(no.substring(i,i+2), 16);
            number[i/2] = (byte)(j & 0x000000ff);
            }
        return number;
        }
 
    static public void printHex(byte[] b)   {printHex(b, b.length);}
 
    static public void printHex(short[] b)  {printHex(b, b.length);}
 
    static public void printHex(int[] b)    {printHex(b, b.length);}
 
 
    static public void printHex(String label, byte[] b)  {printHex(label, b, b.length);}
 
    static public void printHex(String label, short[] b) {printHex(label, b, b.length);}
 
    static public void printHex(String label, int[] b)   {printHex(label, b, b.length);}
 
 
    static public String toHexF(String label, byte[] b)  {return toHexF(label, b, b.length);}
 
    static public String toHexF(String label, short[] b) {return toHexF(label, b, b.length);}
 
    static public String toHexF(String label, int[] b)   {return toHexF(label, b, b.length);}
 
 
    static public String toHexF(int[] b)   {return toHexF(b, b.length);}
 
    static public String toHexF(short[] b) {return toHexF(b, b.length);}
 
    static public String toHexF(byte[] b)  {return toHexF(b, b.length);}
 
 
    static public String toHex(byte[] b)  {return toHex(b, b.length);}
 
    static public String toHex(short[] b) {return toHex(b, b.length);}
 
    static public String toHex(int[] b)   {return toHex(b, b.length);}
    static public void printHex(String label, byte[] b, int len)
        {
        System.out.println(label);
        printHex(b, len);
        }
 
    static public void printHex(String label, short[] b, int len)
        {
        System.out.println(label);
        printHex(b, len);
        }
 
    static public void printHex(String label, int[] b, int len)
        {
        System.out.println(label);
        printHex(b, len);
        }
 
 
    static public void printHex(byte[] b, int len)   {System.out.print(toHexF(b, len));}
 
    static public void printHex(short[] b, int len)  {System.out.print(toHexF(b, len));}
 
    static public void printHex(int[] b, int len)    {System.out.print(toHexF(b, len));}
 
 
    static public String toHexF(String label, int[] b, int len)
        {
        return label + "\n" + toHexF(b, len);
        }
 
    static public String toHexF(String label, short[] b, int len)
        {
        return label + "\n" + toHexF(b, len);
        }
 
    static public String toHexF(String label, byte[] b, int len)
        {
        return label + "\n" + toHexF(b, len);
        }
 
 
    static public String toHexF(byte[] b, int len)
        {
        StringBuffer s = new StringBuffer("");
        int i;
 
        if (b==null) return "<null>";
                    
        for (i=0; i<len; i++)
            {
            s.append(" " + toHex(b[i]));
            if      (i%16 == 15) s.append("\n");
            else if (i% 8 ==  7) s.append(" ");
            else if (i% 4 ==  3) s.append(" ");
            }
        if (i%16 != 0) s.append("\n");
 
        return s.toString();
        }
 
    static public String toHexF(short[] b, int len)
        {
        StringBuffer s = new StringBuffer("");
        int i;
 
        if (b==null) return "<null>";
                    
        for (i=0; i<len; i++)
            {
            s.append(" " + toHex(b[i]));
            if      (i%16 ==  7) s.append("\n");
            else if (i% 4 ==  3) s.append(" ");
            }
        if (i%8 != 0) s.append("\n");
 
        return s.toString();
        }
 
    static public String toHexF(int[] b, int len)
        {
        StringBuffer s = new StringBuffer("");
        int i;
 
        if (b==null) return "<null>";
 
        for (i=0; i<len; i++)
            {
            s.append(" " + toHex(b[i]));
            if (i%4 == 3) s.append("\n");
            }
        if (i%4 != 0) s.append("\n");
        return s.toString();
        }
 
 
    static public String toHex(int[] b, int len)
        {
        if (b==null) return "";
        StringBuffer s = new StringBuffer("");
        int i;
        for (i=0; i<len; i++)
            s.append(toHex(b[i]));
        return s.toString();
        }
 
    static public String toHex(short[] b, int len)
        {
        if (b==null) return "";
        StringBuffer s = new StringBuffer("");
        int i;
        for (i=0; i<len; i++)
            s.append(toHex(b[i]));
        return s.toString();
        }
 
    static public String toHex(byte[] b, int len)
        {
        if (b==null) return "";
        StringBuffer s = new StringBuffer("");
        int i;
        for (i=0; i<len; i++)
            s.append(toHex(b[i]));
        return s.toString();
        }
 
 
    static public String toHex(byte b)
        {
        Integer I = new Integer((((int)b) << 24) >>> 24);
        int i = I.intValue();
 
        if ( i < (byte)16 )
            return "0"+Integer.toString(i, 16);
        else
            return     Integer.toString(i, 16);
        }
 
    static public String toHex(short i)
        {
        byte b[] = new byte[2];
        b[0] = (byte)((i & 0xff00) >>>  8);
        b[1] = (byte)((i & 0x00ff)       );
 
        return toHex(b[0])+toHex(b[1]);
        }
 
    static public String toHex(int i)
        {
        byte b[] = new byte[4];
        b[0] = (byte)((i & 0xff000000) >>> 24);
        b[1] = (byte)((i & 0x00ff0000) >>> 16);
        b[2] = (byte)((i & 0x0000ff00) >>>  8);
        b[3] = (byte)((i & 0x000000ff)       );
 
        return toHex(b[0])+toHex(b[1])+toHex(b[2])+toHex(b[3]);
        }
    }
    */
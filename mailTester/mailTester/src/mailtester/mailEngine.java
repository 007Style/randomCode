/*
 * mailEngine.java
 *
 * Created on October 30, 2006, 11:44 PM
 * Copyright (C) 2006, 2007, 2008 Trigger Email Marketing, LLC.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mailTester;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.text.*;

/**
 *
 * @author d
 */
public class mailEngine {
    
    private String AUTH_USER = null;
    private String AUTH_PWD  = null;
    
    // Creates a new instance of mailEngine
    public mailEngine() {}
    
    // postMail : Sends email using internal smtp server...
    public void postMail(String sender, String rPath, String recipients[], String[] bcc,  String replyaddr, String subject, String message , String from, String eFormat) throws Exception
    {
        try
        {
            MimeMessage msg = org.masukomi.aspirin.core.SimpleMimeMessageGenerator.getNewMimeMessage();
            //Address[] fromA = {from};
            //msg.setFrom(new Address());
            msg.setFrom(new InternetAddress(from));
            InternetAddress rplyA = new InternetAddress(replyaddr);
            InternetAddress[] rplyAS = {rplyA};
            msg.setReplyTo(rplyAS);
            if(sender != null)
            {
                msg.setSender(new InternetAddress(sender));
            }
            if(recipients != null)
            {
                InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
                for (int i = 0; i < recipients.length; i++)
                {
                    addressTo[i] = new InternetAddress(recipients[i]);
                    msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
                }
            }
            //msg.setRecipients(Message.RecipientType.TO, addressTo);
            if(bcc != null)
            {
                InternetAddress[] addressToBCC = new InternetAddress[bcc.length];
                for (int i = 0; i < bcc.length; i++)
                {
                    addressToBCC[i] = new InternetAddress(bcc[i]);
                    msg.addRecipient(Message.RecipientType.BCC, addressToBCC[i]);
                }   
                //msg.setRecipients(Message.RecipientType.BCC, addressToBCC);
            }
            //msg.setRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(recipient) });
            msg.setSubject(subject);
            if(eFormat.equals("multipart/alternative"))
            {
                try
                {
                    // Create an "Alternative" Multipart message
                    Multipart mp = new MimeMultipart("alternative");
                    String textfile = "";  String htmlfile = "";
                    int indexHTML = message.indexOf("--boundary1 Content-Type: text/html");  //indexHTML = indexHTML + 35;
                    int indexTEXT = message.indexOf("--boundary1 Content-Type: text/plain"); //indexTEXT = indexTEXT + 36;
                    int indexEND  = message.indexOf("--boundary1--");
                    if(indexTEXT < indexHTML)
                    {
                        textfile = message.substring(indexTEXT + 36, indexHTML);
                        htmlfile = message.substring(indexHTML + 35, indexEND);
                    }
                    else if(indexTEXT > indexHTML)
                    {
                        textfile = message.substring(indexTEXT + 36, indexEND);
                        htmlfile = message.substring(indexHTML + 35, indexTEXT);
                    }   
                    else
                    {
                        throw new Exception("BAD multipart text parse...\n");
                    }
                    // TEXT part
                    MimeBodyPart bp1 = new MimeBodyPart();  
                    bp1.setContent(textfile, "text/plain");
                    mp.addBodyPart(bp1);
                    // HTML part
                    BodyPart bp2 = new MimeBodyPart();
                    bp2.setContent(htmlfile, "text/html");
                    mp.addBodyPart(bp2);
                    // Set the content for the message and transmit
                    msg.setContent(mp);
                }
                catch(Exception e)
                {
                    throw new Exception("BAD multipart text parse... " + e);
                }
            }
            else
            {
                msg.setContent(message, eFormat);
            }
            if(!ProgramConfig.getperfTest()){ org.masukomi.aspirin.core.MailQue.queMail(msg); }
        }
        catch(Exception e)
        {
            throw new Exception("postMail : Sends email using internal smtp server... " + recipients[0] + " " + e);
        }
    }
        
    // postMail : Sends email using no authentication and an smtp server...  smtpPort=25
    public void postMail(String sender, String rPath, String[] recipients, String[] bcc, String replyaddr, String subject, String message , String from, String smtp, String smtpPort, String eFormat, Date dateTime, String listUnsub) throws Exception
    {
        try
        {
            // postMail : Sends email using no authentication and an smtp server...  smtpPort=25  ||  Passes message_id
            postMail(sender, rPath, recipients, bcc, replyaddr, subject, message , from, smtp, smtpPort, eFormat, getMyMessageID(), dateTime, listUnsub);
        }
        catch(Exception e){ throw e; }
    }
    // postMail : Sends email using no authentication and an smtp server...  smtpPort=25
    public void postMail(String sender, String rPath, String recipients[ ], String[] bcc, String replyaddr, String subject, String message , String from, String smtp, String smtpPort, String eFormat, String message_id, Date dateTime, String listUnsub) throws Exception
    {
        try
        {
            boolean debug = false;
            //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", smtp);
            props.put("mail.smtp.port", smtpPort);
            if(rPath != null)
            {
                props.put("mail.smtp.from", rPath);
            }
            // create some properties and get the default Session
            Session session = Session.getInstance(props, null);
            session.setDebug(debug);
            // create a message
            MimeMessage msg = new myMimeMessage(session, message_id);
            msg.saveChanges();
            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);
            InternetAddress rplyA = new InternetAddress(replyaddr);
            InternetAddress[] rplyAS = {rplyA};
            msg.setReplyTo(rplyAS);
            if(sender != null)
            {
                msg.setSender(new InternetAddress(sender));
            }
            if(listUnsub != null)
            {
                if(listUnsub.equalsIgnoreCase("-1") || listUnsub.equalsIgnoreCase("NONE")){}
                else{ msg.addHeader("List-Unsubscribe", listUnsub); }
            }
            else
            {
                listUnsub = getListUnsub(message_id);
                if(listUnsub != null){ msg.addHeader("List-Unsubscribe", listUnsub); }
            }
            if(dateTime != null)
            {
                //msg.setSentDate(dateSQL(dateTime));
                msg.setSentDate(dateTime);
            }
            if(recipients != null)
            {
                InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
                for (int i = 0; i < recipients.length; i++)
                {
                    addressTo[i] = new InternetAddress(recipients[i]);
                    //System.out.println("addressTo[" + i + "] " + addressTo[i]); System.out.flush();
                    msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
                }
            }
            //msg.setRecipients(Message.RecipientType.TO, addressTo);
            if(bcc != null)
            {
                InternetAddress[] addressToBCC = new InternetAddress[bcc.length];
                for (int i = 0; i < bcc.length; i++)
                {
                    addressToBCC[i] = new InternetAddress(bcc[i]);
                    //System.out.println("addressToBCC[" + i + "] " + addressToBCC[i]); System.out.flush();
                    msg.addRecipient(Message.RecipientType.BCC, addressToBCC[i]);
                }   
                //msg.setRecipients(Message.RecipientType.BCC, addressToBCC);
            }
            // Optional : You can also set your custom headers in the Email if you Want
            //msg.addHeader("MyHeaderName", "myHeaderValue");
            // Setting the Subject and Content Type
            msg.setSubject(subject);
            if(eFormat.equals("multipart/alternative"))
            {
                try
                {
                    // Create an "Alternative" Multipart message
                    Multipart mp = new MimeMultipart("alternative");
                    String textfile = "";  String htmlfile = "";
                    int indexHTML = message.indexOf("--boundary1 Content-Type: text/html");  //indexHTML = indexHTML + 35;
                    int indexTEXT = message.indexOf("--boundary1 Content-Type: text/plain"); //indexTEXT = indexTEXT + 36;
                    int indexEND  = message.indexOf("--boundary1--");
                    if(indexTEXT < indexHTML)
                    {
                        textfile = message.substring(indexTEXT + 36, indexHTML);
                        htmlfile = message.substring(indexHTML + 35, indexEND);
                    }
                    else if(indexTEXT > indexHTML)
                    {
                        textfile = message.substring(indexTEXT + 36, indexEND);
                        htmlfile = message.substring(indexHTML + 35, indexTEXT);
                    }   
                    else
                    {
                        throw new Exception("BAD multipart text parse...");
                    }
                    // TEXT part
                    MimeBodyPart bp1 = new MimeBodyPart();  
                    bp1.setContent(textfile, "text/plain");
                    mp.addBodyPart(bp1);
                    // HTML part
                    BodyPart bp2 = new MimeBodyPart();
                    bp2.setContent(htmlfile, "text/html");
                    mp.addBodyPart(bp2);
                    // Set the content for the message and transmit
                    msg.setContent(mp);
                }
                catch(Exception e)
                {
                    //e.printStackTrace();
                    throw new Exception("BAD multipart text parse... " + e);
                }
            }
            else
            {
                msg.setContent(message, eFormat);
            }
            if(!ProgramConfig.getperfTest()){ Transport.send(msg); }
        }
        /*
        catch(com.sun.mail.smtp.SMTPSendFailedException e)
        {
            System.out.println("************* com.sun.mail.smtp.SMTPSendFailedException *****************");
            e.printStackTrace();
            System.out.println(e);
            System.out.println(e.getCommand());
            System.out.println(e.getReturnCode());
            System.out.println(e.getMessage());
            System.out.println("************* com.sun.mail.smtp.SMTPSendFailedException *****************");
        }
        */
        catch(Exception e)
        {
            //e.printStackTrace();
            throw new Exception("postMail : Sends email using no authentication and an smtp server... " + recipients[0] + " " + e);
        }
    }
        
    // postMail : Sends email using authentication and an smtp server...  smtpPort=25
    public void postMail(String sender, String rPath, String[] recipients, String[] bcc, String replyaddr, String subject, String message, String from, String smtp, String userName, String passWord, String smtpPort, String eFormat, Date dateTime, String listUnsub) throws Exception
    {
        try
        {
            postMail(sender, rPath, recipients, bcc, replyaddr, subject, message, from, smtp, userName, passWord, smtpPort, eFormat, getMyMessageID(), dateTime, listUnsub);
        }
        catch(Exception e){ throw e; }
    }
        
    // postMail : Sends email using authentication and an smtp server...  smtpPort=25  ||  Passes message_id
    public void postMail(String sender, String rPath, String recipients[ ], String[] bcc, String replyaddr, String subject, String message, String from, String smtp, String userName, String passWord, String smtpPort, String eFormat, String message_id, Date dateTime, String listUnsub) throws Exception
    {
        try
        {
            boolean debug = false;
            
            //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", smtp);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", smtpPort);
            if(rPath != null)
            {
                props.put("mail.smtp.from", rPath);
            }
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(debug);
            // create a message
            MimeMessage msg = new myMimeMessage(session, message_id);
            msg.saveChanges();
            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);
            InternetAddress rplyA = new InternetAddress(replyaddr);
            InternetAddress[] rplyAS = {rplyA};
            msg.setReplyTo(rplyAS);
            if(sender != null)
            {
                msg.setSender(new InternetAddress(sender));
            }
            if(listUnsub != null)
            {
                if(listUnsub.equalsIgnoreCase("-1") || listUnsub.equalsIgnoreCase("NONE")){}
                else{ msg.addHeader("List-Unsubscribe", listUnsub); }
            }
            else
            {
                listUnsub = getListUnsub(message_id);
                if(listUnsub != null){ msg.addHeader("List-Unsubscribe", listUnsub); }
            }
            if(dateTime != null)
            {
                //msg.setSentDate(dateSQL(dateTime));
                msg.setSentDate(dateTime);
            }
            if(recipients != null)
            {
                InternetAddress[] addressTo = new InternetAddress[recipients.length];
                for (int i = 0; i < recipients.length; i++)
                {
                    addressTo[i] = new InternetAddress(recipients[i]);
                    msg.addRecipient(Message.RecipientType.TO, addressTo[i]);
                }   
            }
            //msg.setRecipients(Message.RecipientType.TO, addressTo);
            if(bcc != null)
            {
                InternetAddress[] addressToBCC = new InternetAddress[bcc.length];
                for (int i = 0; i < bcc.length; i++)
                {
                    addressToBCC[i] = new InternetAddress(bcc[i]);
                    msg.addRecipient(Message.RecipientType.BCC, addressToBCC[i]);
                }   
                //msg.setRecipients(Message.RecipientType.BCC, addressToBCC);
            }
            // Setting the Subject and Content Type
            msg.setSubject(subject);
            if(eFormat.equals("multipart/alternative"))
            {
                try
                {
                    // Create an "Alternative" Multipart message
                    Multipart mp = new MimeMultipart("alternative");
                    String textfile = "";  String htmlfile = "";
                    int indexHTML = message.indexOf("--boundary1 Content-Type: text/html");  //indexHTML = indexHTML + 35;
                    int indexTEXT = message.indexOf("--boundary1 Content-Type: text/plain"); //indexTEXT = indexTEXT + 36;
                    int indexEND  = message.indexOf("--boundary1--");
                    if(indexTEXT < indexHTML)
                    {
                        textfile = message.substring(indexTEXT + 36, indexHTML);
                        htmlfile = message.substring(indexHTML + 35, indexEND);
                    }
                    else if(indexTEXT > indexHTML)
                    {
                        textfile = message.substring(indexTEXT + 36, indexEND);
                        htmlfile = message.substring(indexHTML + 35, indexTEXT);
                    }   
                    else
                    {
                        throw new Exception("BAD multipart text parse...");
                    }
                    // TEXT part
                    MimeBodyPart bp1 = new MimeBodyPart();  
                    bp1.setContent(textfile, "text/plain");
                    mp.addBodyPart(bp1);
                    // HTML part
                    BodyPart bp2 = new MimeBodyPart();
                    bp2.setContent(htmlfile, "text/html");
                    mp.addBodyPart(bp2);
                    // Set the content for the message and transmit
                    msg.setContent(mp);
                }
                catch(Exception e)
                {
                    throw new Exception("BAD multipart text parse... " + e);
                }
            }
            else
            {
                msg.setContent(message, eFormat);
            }
            if(!ProgramConfig.getperfTest()){ Transport.send(msg); }
        }
        catch(Exception e)
        {
            throw new Exception("postMail : Sends email using authentication and an smtp server... " + recipients[0] + " " + e);
        }
    }
        
    public static String getMyMessageID()
    {
        Long id = new Long(0);
        double d = java.lang.Math.random();
        id = new Long((long) (d * Long.MAX_VALUE));
        StringBuffer buffer = new StringBuffer();
        buffer.append('<');
        buffer.append(id);
        buffer.append('.');
        buffer.append(System.currentTimeMillis());
        if(ProgramConfig.getmessageID_Domain().equals(""))
        {
            buffer.append('@');
            buffer.append(new Integer((int) (java.lang.Math.random() * Integer.MAX_VALUE)));
        }
        else
        {
            buffer.append("." + new Integer((int) (java.lang.Math.random() * Integer.MAX_VALUE)));
            buffer.append("@" + ProgramConfig.getmessageID_Domain());
        }
        buffer.append('>');
        //buffer.append('.');
        //buffer.append("EMail.");
        //buffer.append(address);
        return buffer.toString();
    }
    
    public static String getListUnsub(String message_id) throws Exception
    {
        try
        {
            int idAT = message_id.lastIndexOf("@");
            String listU = "<" + "mailto:" + message_id.substring(1, idAT) + "@" + ProgramConfig.getlistUnsubscribe_Domain() + ">";
            return listU;
        }
        catch(Exception e)
        {
            throw new Exception("postMail : getListUnsub failure... " + message_id + " " + e);
        }    
    }

    /**
    * SimpleAuthenticator is used to do simple authentication when the SMTP server requires it.
    */
    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(AUTH_USER, AUTH_PWD);
        }
    }
    
    /**
    * Objet MimeMessage étendu pour changer l'entête Message-ID 
    */
    private final static class myMimeMessage extends MimeMessage
    {
        protected Session session;
        protected String message_id;
	
	/**
	 * Constructeur!
	 * 
	 * @param _session	javax.mail.Session
	 */
	protected myMimeMessage(Session _session, String _message_id)
	{
		super(_session);
		this.session = _session;
                this.message_id = _message_id;
	}
        
	// code other constructor that needed!
        
	/**
	 * Modification du Message-ID entête (appeler par updateHeaders!)
	 */
        protected void updateMessageID() throws MessagingException 
        {
            setHeader("Message-ID", message_id);
            //setHeader("Message-ID", getMyMessageID(session));
            //setHeader("X-Mailer", "EMail_2.0"); // ajout du mailer...
        }
    } // fin de classe

    public Date dateSQL(String sql_date) throws Exception
    {
        try 
        {     
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return (Date)formatter.parse(sql_date);
        } 
        catch (Exception e)
        {
            throw e;
        }   
    }
}
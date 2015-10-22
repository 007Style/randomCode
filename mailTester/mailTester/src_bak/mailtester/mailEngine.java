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
    public void postMail(String recipients[], String[] bcc,  String replyaddr, String subject, String message , String from, String eFormat, String sender, String rPath) throws Exception
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
        else
        {
            msg.setContent(message, eFormat);
        }
        org.masukomi.aspirin.core.MailQue.queMail(msg);
    }
    
    // postMail : Sends email using no authentication and an smtp server...  smtpPort=25
    public void postMail(String recipients[ ], String[] bcc, String replyaddr, String subject, String message , String from, String smtp, String smtpPort, String eFormat, String sender, String rPath) throws Exception
    {
        System.out.println("postMail : Sends email using no authentication and an smtp server... START  ||  " + smtp);
        boolean debug = false;
        //Set the host smtp address
        Properties props = new Properties();
        props.clear();
        System.out.println("****************  HOST: " + smtp);
        props.put("mail.smtp.host", smtp);
        System.out.println("------------ " + smtp + " ++++ " + props.get("mail.smtp.host"));
        props.put("mail.smtp.port", smtpPort);
        // create some properties and get the default Session
        //Session session = Session.getDefaultInstance(props, null);
        
        //Properties prop = new Properties();
        if(rPath != null)
        {
            props.put("mail.smtp.from", rPath);
        }
        //props.put("mail.smtp.host", mailserver);
        //Session session = Session.getInstance(props, null);
        
        
        
        Session session = Session.getInstance(props, null);
        session.setDebug(debug);
        // create a message
        //MimeMessage msg = new javax.mail.internet.MimeMessage(session);
        MimeMessage msg = new myMimeMessage(session, "trig@trigger");
        msg.saveChanges();
        
        
        
        
        /*if(rPath != null)
        {
            System.out.println("ADDING rPath");
            msg.addHeader("Return-Path", rPath);
        }*/
        
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
            
        msg.addRecipients(Message.RecipientType.TO, recipients[0]);
        /*
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
         * */
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
        else
        {
            msg.setContent(message, eFormat);
        }
        Transport.send(msg);
        System.out.println("postMail : Sends email using no authentication and an smtp server... FINISH");
    }
    
    // postMail : Sends email using authentication and an smtp server...  smtpPort=25
    public void postMail(String recipients[ ], String[] bcc, String replyaddr, String subject, String message, String from, String smtp, String userName, String passWord, String smtpPort, String eFormat, String sender, String rPath) throws Exception
    {
        boolean debug = false;

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtpPort);
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(debug);
        
        if(rPath != null)
        {
            props.put("mail.smtp.from", rPath);
        }
        
        // create a message
        MimeMessage msg = new javax.mail.internet.MimeMessage(session);
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
        else
        {
            msg.setContent(message, eFormat);
        }
        Transport.send(msg);
    }
    
    /**
    * Objet MimeMessage �tendu pour changer l'ent�te Message-ID 
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
	 * Modification du Message-ID ent�te (appeler par updateHeaders!)
	 */
        protected void updateMessageID() throws MessagingException 
        {
            setHeader("Message-ID", message_id);
            //setHeader("Message-ID", getMyMessageID(session));
            //setHeader("X-Mailer", "EMail_2.0"); // ajout du mailer...
        }
    } // fin de classe

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
}


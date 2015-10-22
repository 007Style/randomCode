/*
 * JBiff  : A simple program to notify you when new mail has arrived,
 *          specifically using the IMAP protocol.  Minimal error 
 *          checking is done to reduce code size.  
 *          To run this code, you will need mail.jar and activation.jar 
 *          from JavaMail and Java Activation Framework (JAF) respectively.  
 *          This code draws heavily on the "Monitoring a Mailbox"
 *          example in the JavaMail API v 1.1 (Appendix B, page 71)
 *
 * Author : Chris Ryan (cryan@plugged.net.au)
 *          Plugged In Software
 *
 * Copyright (c) 1999 Plugged In Software Pty Ltd.
 * Released under the GNU GPL - see http://www.gnu.org/copyleft/gpl.html
 *
 * Usage  : JBiff <username> <password> <host> <mailbox> <frequency, in seconds>
 *          e.g. java JBiff fnerk foobar mailin inbox 60
 *
 *          inbox refers to the username's default mailbox.  It's case
 *          insensitive. 
 */

import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class JBiff extends Frame {

  // Labels to print some info onto...
  public Label display_label;
  
  /*
   * Constructor.  Creates the various AWT components required
   * in the system.
   */
  public JBiff() {

    // create the simple GUI
    display_label = new Label("Please wait...",Label.LEFT);
    add(display_label);
    pack();
    setSize(600,30);
    display_label.setSize(600,30);
    setTitle("JBiff");
    setVisible(true);

    // create a handler to close the application
    addWindowListener(new WindowAdapter() {

      public void windowClosing(WindowEvent w) {

        // time to leave
        System.exit(0);

      }
 
    }); 

  }

  public final static void main(String[] args) throws Exception {

    // Check that the right number of parameters have been passed
    if (args.length != 5) {

      System.out.println("JBiff: Monitor the state of an IMAP mailbox.");
      System.out.println("usage: java JBiff <username> <password> <host> <mailbox> <frequency (sec)>");
      System.exit(1);

    }

    // Get the parameters from the command line.
    String username = args[0];
    String password = args[1];
    String host = args[2];
    String mailbox = args[3];  // use inbox for user's default/main box
    int frequency;
    try {

      frequency = Integer.parseInt(args[4]) * 1000;

    } catch (NumberFormatException nfe) {

      // bad number, just check every 5 mins.
      frequency = 300000;

      System.err.println("Your frequency value was bogus.  Using 5 minutes instead");

    }

    // create the gui system
    JBiff jbiff = new JBiff();

    /*
     * Get the system properties.  A number of properties can be
     * set that are useful to the JavaMail system, including
     * mail.host.  If mail.host is not set, JavaMail will use
     * localhost for the default mail server.
     */
    Properties system_properties = System.getProperties();

    // get the default JavaMail Session instance
    Session session = Session.getDefaultInstance(system_properties,null); 

    // switch on the session debugging to see what's happening
    //session.setDebug(true);

    try {

      // get the Store object for IMAP
      Store store = session.getStore("imap");

      // connect to the specified host
      store.connect(host,username,password);

      // get the Folder for this mailbox
      Folder folder = store.getFolder(mailbox);

      // ensure we got an existing folder that holds messages
      // not a sub folder!
      if (folder == null || !folder.exists() || folder.getType() == Folder.HOLDS_FOLDERS) {

        System.err.println("Invalid folder, or folder does not exist, or folder holds other folders!");
        System.exit(1);

      }

      // loop forever until the user kills us
      while (true) {

        /*
         * While the operations below can be done on a closed folder,
         * it's best to have the folder open, since each operation
         * would open & close the folder itself - not efficient!
         *
         * Another option would be to open the folder and register
         * a listener for a MessageCountEvent, however this would
         * require the folder to be held continually open in
         * READ_WRITE mode.  This could conflict with another
         * program trying to access the folder, e.g. a mail reader.
         */
        folder.open(Folder.READ_ONLY);

        // Get the current total number of messages
        int total_nr_messages = folder.getMessageCount();

        // Get the current number of unread messages
        int unread_nr_messages = folder.getUnreadMessageCount();

        // get the number of new messages
        int new_nr_messages = folder.getNewMessageCount();

        // display a status message...
        jbiff.display_label.setText("You have "+new_nr_messages+" new messages, "+unread_nr_messages+" unread messages and "+total_nr_messages+" total messages in your mailbox: "+mailbox);

        // close the folder, expunging messages is not our problem.
        folder.close(false);

        // try to sleep for a while
        try {

          Thread.sleep(frequency);

        } catch (InterruptedException ie) {

          // just ignore and continue on...

        }
        

      }

    } catch (MessagingException me) {

      System.err.println("Error: "+me.getMessage());
      System.exit(1);

    }

  }


}

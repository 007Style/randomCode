import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;

public class DeleteMessageExample {
  public static void main (String args[]) throws Exception {
    //String host = args[0];
    //String username = args[1];
    //String password = args[2];

    String host = "triggeremails.com";
    String username = "test";
    String password = "tr1ggertr1gger";

    // Get session
    Session session = Session.getInstance(System.getProperties(), null);

    // Get the store
    Store store = session.getStore("pop3");
    store.connect(host, username, password);

    // Get folder
    Folder folder = store.getFolder("INBOX");
    folder.open(Folder.READ_WRITE);

    BufferedReader reader = new BufferedReader (
      new InputStreamReader(System.in));

    // Get directory
    Message message[] = folder.getMessages();
    for (int i=0, n=message.length; i<n; i++) {
       System.out.println(i + ": " + message[i].getFrom()[0] 
         + "\t" + message[i].getSubject());

       System.out.println("Do you want to delete message? [YES to delete]");
       String line = reader.readLine();
       // Mark as deleted if appropriate
       if ("YES".equals(line)) {
         message[i].setFlag(Flags.Flag.DELETED, true);
       }
    }

    // Close connection 
    folder.close(true);
    store.close();
  }
}

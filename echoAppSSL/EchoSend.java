import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class EchoSend extends Thread 
{
	private SSLSocket sslsocket = null;
	private OutputStream outputstream = null;
	//private OutputStreamWriter outputstreamwriter = null;
	//private BufferedWriter bufferedwriter = null;
	private ObjectOutputStream OOS = null;
	
	public void sendPacket(Object o) throws Exception
	{
		try
		{
			//Bufferedwriter.write(String + '\n');
      //BufferedWriter.flush();
      OOS.writeObject(o);
    }
    catch (Exception e){ throw e; }
	}
	
	public EchoSend(SSLSocket sslS) throws Exception
	{
		try
		{
			sslsocket = sslS;
			outputstream = sslsocket.getOutputStream();
    	//outputstreamwriter = new OutputStreamWriter(outputstream);
    	//bufferedwriter = new BufferedWriter(outputstreamwriter);
    	OOS = new ObjectOutputStream(outputstream);
   	}
    catch (Exception e){ throw e; }
	}
	
	public void run()
  {
		// NOT IMPLEMENTED
	}

}
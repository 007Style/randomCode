import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.IOException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class EchoRecv extends Thread
{
	private SSLSocket sslsocket = null;
	private InputStream inputstream = null;
	//private InputStreamReader inputstreamreader = null;
	//private BufferedReader bufferedreader = null;
	private ObjectInputStream OIS = null;
	private final Object nullObject  = null;
	
	public Object recvPacket()
	{
		try
		{
			//return (Object) bufferedreader.readLine();
			return  OIS.readObject();
		}
		catch (Exception exception){ /*exception.printStackTrace();*/ }
		return nullObject;
	}
	
	public EchoRecv(SSLSocket sslS)
	{
		try
		{
			sslsocket = sslS;
			inputstream = sslsocket.getInputStream();
  		//inputstreamreader = new InputStreamReader(inputstream);
  		//bufferedreader = new BufferedReader(inputstreamreader);
  		OIS = new ObjectInputStream(inputstream);
  	}
		catch (Exception exception){ /*exception.printStackTrace();*/ }
	}
	
	public void run()
  {
		// NOT IMPLEMENTED
	}
}
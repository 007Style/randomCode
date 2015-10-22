import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class EchoClient 
{
	private int port = 32479;
	private String host = "localhost";
	private SSLSocketFactory sslsocketfactory = null;
	private SSLSocket sslsocket = null;
	private EchoSend ES = null;
	private EchoRecv ER = null;
	
	public void startEchoSend(){ ES.start(); }
	public void startEchoRecv(){ ER.start(); }
	public EchoSend getSendThread(){ return ES; }
	public EchoRecv getRecvThread(){ return ER; }

	public EchoClient(String h, int p) throws Exception
	{
		host = h;
		port = p;
		try
    {
			sslsocketfactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
    	sslsocket = (SSLSocket)sslsocketfactory.createSocket(host, port);
    	ES = new EchoSend(sslsocket);
      ER = new EchoRecv(sslsocket);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw e;
    }
	}
	
  public static void main(String [] arstring)
  {
    try
    {
    	System.out.println("1"); System.out.flush();
    	EchoClient eC = new EchoClient("localhost", 32479);
    	System.out.println("2"); System.out.flush();
      InputStream inputstream = System.in;
      InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
      BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

      //OutputStream outputstream = sslsocket.getOutputStream();
      //OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
      //BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

      String string = null;
      while ((string = bufferedreader.readLine()) != null)
      {
        //bufferedwriter.write(string + '\n');
        //bufferedwriter.flush();
        eC.ES.sendPacket(new String(string));
      }
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
  }
}

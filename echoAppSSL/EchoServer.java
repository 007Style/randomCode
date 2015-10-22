import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class EchoServer extends Thread
{
	private int port = 32479;
	private SSLServerSocketFactory sslserversocketfactory = null;
	private SSLServerSocket sslserversocket = null;
	private SSLSocket sslsocket = null;
	private EchoSend ES = null;
	private EchoRecv ER = null;
	
	public void startEchoSend(){ ES.start(); }
	public void startEchoRecv(){ ER.start(); }
	public EchoSend getSendThread(){ return ES; }
	public EchoRecv getRecvThread(){ return ER; }
	
	public EchoServer() throws Exception {}
	
	public EchoServer(int p) throws Exception
	{
		port = p;
	}
	
	public void serverConnect() throws Exception{ try{ serverConnect(port); }catch(Exception e){ throw e; } }
	
	public void serverConnect(int p) throws Exception
	{
		try
    {
      sslserversocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
      sslserversocket = (SSLServerSocket)sslserversocketfactory.createServerSocket(p);
      sslsocket = (SSLSocket)sslserversocket.accept();
      ES = new EchoSend(sslsocket);
      ER = new EchoRecv(sslsocket);
    }
    catch(Exception e)
    {
      //exception.printStackTrace();
      throw e;
    }	
	}
	
	public void run(int p)
	{
		try
		{
			serverConnect(p);
		} catch(Exception e){}
	}
	
	public void run()
	{
		try
		{
			serverConnect(port);
		} catch(Exception e){}
	}
	
  public static void main(String [] arstring)
  {
  	EchoServer eS = null;
  	try
  	{
			eS = new EchoServer();
			eS.serverConnect();
		} catch(Exception e){}
    //String string = null;
    while(true) //((string = bufferedreader.readLine()) != null)
    {
    	if(eS.sslsocket != null)
    	{
    		System.out.println(((String)eS.ER.recvPacket()).toString());
      	//System.out.println(string);
       	//System.out.flush();
      }
    }
  }
}

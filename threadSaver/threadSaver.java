



public class threadSaver extends Thread
{
	
	aThread[] thArray = null;

	public threadSaver()
	{
		aThread aT = new aThread();
		System.out.println(aT.getState());
		aT.start();
		addWait();
		System.out.println(aT.getState());
		aT.stopExec(false);
		addWait();
		System.out.println(aT.getState());
		//System.out.println(aT.getState());		
	}
	
	public threadSaver(int threads)
	{
		for(int j=0; j< threads; j++)
		{
			 //thArray[j] = new killer();
			 //thArray[j].start();
			 (new aThread()).start();
		}
	}
	
	public static void main(String args[])
	{
		int thNum = 1;
		try
		{
			thNum = Integer.parseInt(args[0]);
		}
		catch(Exception e){}
		aThread[] thArray = new aThread[thNum];
		//threadSaver tS = new threadSaver(thNum);
		threadSaver tS2 = new threadSaver();
	}

	public void addWait()
	{
		try{ java.lang.Thread.sleep(2000); }  catch(java.lang.InterruptedException e){}
	}
}
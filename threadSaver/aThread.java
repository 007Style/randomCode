



public class aThread extends Thread 
{
	boolean goStop = true;
	
	public aThread()
	{
		
	}	

	public void stopExec(boolean b)
	{
		goStop = b;
	}
	
	public void run()
	{
		killsCPU();
	}
	
	private void killsCPU()
	{
		
		int k = 0;
		while(goStop)
		{
			for(int i=0; i<=100000000; i++)
			{
				k++;
			}
			for(int i=0; i<=100000000; i++)
			{
				k--;
			}
		}
	}
	
}
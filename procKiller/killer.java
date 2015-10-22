



public class killer extends Thread 
{
	
	public killer()
	{
		
	}	
	
	public void run()
	{
		killsCPU();
	}
	
	private void killsCPU()
	{
		int k = 0;
		while(true)
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




public class procKiller extends Thread
{
	
	killer[] thArray = null;
	
	public procKiller(int threads)
	{
		for(int j=0; j< threads; j++)
		{
			 //thArray[j] = new killer();
			 //thArray[j].start();
			 (new killer()).start();
		}
	}
	
	public void run()
  {
  	while(tEnd != true)
    {
      if(!threadWork())
      {
          //try{ java.lang.Thread.sleep(ProgramConfig.getfileWaitTime()); }  catch(java.lang.InterruptedException e){}
          try{ this.wait(); }catch(Exception e){}  
      }
    }
  }    
	
	public static void main(String args[])
	{
		try
		{
			int thNum = Integer.parseInt(args[0]);
		}
		catch(Exception e){}
		if((thNum == null) || (thNum.equalsIgnoreCase("S"))
		{
			
		}
		else
		{
			killer[] thArray = new killer[thNum];
			procKiller pK = new procKiller(thNum);
		}
	}
}
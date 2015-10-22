import java.util.*;
import java.text.*;
import java.io.*;

public class cTrace
{	
    private static final String tS = "";
	public static String line = "";
	public static FileInputStream fis;
    public static DataInputStream in;
    public static BufferedReader br;
	public static Hashtable<String, String> ticketHash = null;
	public static Vector ticketVec = null;
	public static Vector ticnumVec = null;
	
	public cTrace()
    {
        
    }
	
	public static void readLine()
	{
		try
		{
			String classSt = br.readLine();
			if(classSt == null){ line = null; }
			line = classSt;
		}
		catch(Exception e){ line = null; }
	}
	
	public static void parseLine()
	{
		int index = 0;
		int endex = 0;
		String ticket = "";
		String tracer = "";
		index = line.indexOf(":", 0);
		endex = line.indexOf(" ", index+2);
		ticket = line.substring(index+2, endex);
		//System.out.println(index + " " + endex + " " + line.substring(index+1, endex));
		if(ticket.equals("DESTROYED:"))
		{
			ticket = line.substring(endex+1);
			//System.out.println("D ticket: " + ticket);
			////Destroy Action
			ticketHash.remove(ticket);
		}
		else
		{
			tracer = line.substring(endex+2);
			//System.out.println("ticket: " + ticket);
			//System.out.println("tracer: " + tracer);
			////Instanciate Action
			ticketHash.put(ticket, tracer);
		}
	}
	
	public static void computeResult()
	{
		for (String val : ticketHash.values() ) 
		{
			////ticketVec
			int tInd = ticketVec.indexOf(val);
			if(tInd == -1)  // Does not contain value
			{
				ticketVec.add(val);
				ticnumVec.add(1);
				System.out.println("New Ticket Num(1):");
			}
			else  //  Contains value
			{
				int num = ((Integer)ticnumVec.get(tInd)).intValue();
				num++;
				ticnumVec.set(tInd, num);
				System.out.println("Ticket Plus: " + num);
			}
		}
	}
	
	public static void printResult()
	{
		for(int i=0; i<ticketVec.size(); i++)
		{
			int count = ((Integer)ticnumVec.remove(i)).intValue(); 
			String trace = (String)ticketVec.remove(i);
			System.out.println(count + " " + trace);
		}
	}
    
    public static void main(String[] args)
	{
		try
		{
			fis = new FileInputStream("./dbEngine2.log");
			in = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(in));
			ticketHash = new Hashtable();
			ticketVec = new Vector();
			ticnumVec = new Vector();
		}
		catch(Exception e){}	
		int debugCount = 0;
		while(line != null)
		{
			readLine();
			parseLine();
			debugCount++;
			if(debugCount == 100){ /*System.exit(0);*/ break; }
		}
		computeResult();
		printResult();
	}
	
	public class ticPair
	{	
		int count = 0;
		String trace = "";
		public ticPair(){}
	}
}
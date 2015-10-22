import java.util.*;
import java.text.*;

public class thDate
{	

		private int dayOfWeek(String dayOfWeek)
    {
        if(dayOfWeek.equalsIgnoreCase("SUNDAY")){ return 1; }
        else if(dayOfWeek.equalsIgnoreCase("MONDAY")){ return 2; }
        else if(dayOfWeek.equalsIgnoreCase("TUESDAY")){ return 3; }
        else if(dayOfWeek.equalsIgnoreCase("WEDNESDAY")){ return 4; }
        else if(dayOfWeek.equalsIgnoreCase("THURSDAY")){ return 5; }
        else if(dayOfWeek.equalsIgnoreCase("FRIDAY")){ return 6; }
        else if(dayOfWeek.equalsIgnoreCase("SATURDAY")){ return 7; }
        else{ return 0; }
    }
    
    private String formatDate(Calendar time) throws Exception
    {
        Date format_date = time.getTime();
        try 
        {    
            DateFormat formatter = new SimpleDateFormat("EEEE, MMMM d");
            return formatter.format(format_date);
        } 
        catch (Exception e)
        {
            //String error = (fW.timeDate() + " EBOAC mailParser failure, possible reason: formatDate() failed... \n");
            //throw new Exception(error);
        }  
        return null; 
    }
    
    private String th_date(String op, String[] args) throws Exception
    {
        Calendar cal = Calendar.getInstance();
        if(op.equalsIgnoreCase("ADD"))
        {
            int additionNum = 0;
            try
            {
                additionNum = Integer.parseInt(args[0]);
            }
            catch(Exception e)
            {
                //String error = (fW.timeDate() + " EBOAC mailParser failure, possible reason:  th_date() parseInt failure... \n");
                //throw new Exception(error); 
            }
            cal.add(Calendar.DAY_OF_YEAR, additionNum);
            return formatDate(cal);
        }
        else if(op.equalsIgnoreCase("NEXT"))
        {
        	/*
            for(int i=0; i<7; i++)
            {
                if(cal.get(Calendar.DAY_OF_WEEK) == dayOfWeek(args[0]))
                {
                    return formatDate(cal);
                }
                cal.roll(Calendar.DAY_OF_WEEK, true);
            }
            */
            //cal.add(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_WEEK) + dayOfWeek(args[0]));
            if(cal.get(Calendar.DAY_OF_WEEK) > dayOfWeek(args[0]))
            { 
                cal.add(Calendar.DAY_OF_YEAR, dayOfWeek(args[0]) - cal.get(Calendar.DAY_OF_WEEK) + 7);
                return formatDate(cal); 
            }
            else if(cal.get(Calendar.DAY_OF_WEEK) < dayOfWeek(args[0]))
            { 
                cal.add(Calendar.DAY_OF_YEAR, dayOfWeek(args[0]) - cal.get(Calendar.DAY_OF_WEEK));
                return formatDate(cal); 
            }
            else
            {
                return formatDate(cal); 
            }
            //return formatDate(cal);
            //String error = (fW.timeDate() + " EBOAC mailParser failure, possible reason: NO th_date resolution... \n");
            //throw new Exception(error);
            //return null;
        }
        else
        {
            //String error = (fW.timeDate() + " EBOAC mailParser failure, possible reason:  NO ACORN \"th_date\" TAGS FOUND... \n");
            //throw new Exception(error);
            return null;
        }
    }
    
    public static void main(String[] args)
		{
				try
				{
					thDate thD = new thDate();
					String[] test = new String[] {"4"};
					System.out.println(thD.th_date("ADD", test));
					thDate thD2 = new thDate();
					String[] test2 = new String[] {"sunday"};
					System.out.println(thD2.th_date("NEXT", test2));
				}
				catch(Exception e){}
				
		}
}
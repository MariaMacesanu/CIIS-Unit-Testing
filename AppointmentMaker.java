import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class AppointmentMaker {
	static Appointment[][] calendar = new Appointment[5][8];

	public static void main(String[] args) throws FileNotFoundException { 
		/*Appointment app = new Appointment("mm12", "Interview");
		System.out.println(app.toString()); */
		
		Scanner file = new Scanner(new File("AppointmentList.txt"));
		ArrayList<Appointment> apps = new ArrayList<Appointment>();
		int num = file.nextInt();
		//instantiate a list of all the appointments to be made
		for(int i = 0; i < num; i++)
		{
			String s = file.next();
			//System.out.println(s);
			boolean b;
			if(s.contains("false"))
				b = false;
			else
				b=true;
			//System.out.println(b);
			
			String[] stuff = s.split("_");
			if(stuff.length == 2)
				apps.add(new Appointment(stuff[0],stuff[1]));
			if(stuff.length == 5)
				apps.add(new Appointment( stuff[0],stuff[1],stuff[2],stuff[3],b));
			//System.out.println(apps.getLast().toString());
		}
		
		
		while(!apps.isEmpty())
		{
			int day=0,time=0,time2=0;
			day = dayIndex(apps.get(0).getDay());
			//System.out.println(apps.get(0).getUsername());
			if(apps.get(0).getServiceType().equals("Interview")) 
			{
				time = singleTimeIndex(apps.get(0).getTime()); 
				if(calendar[day][time] == null)
					calendar[day][time] = apps.get(0);
				else
				{
					if(apps.get(0).getFlexibility())
					{
						reschedule(apps.get(0));
						time = singleTimeIndex(apps.get(0).getTime()); 
						day = dayIndex(apps.get(0).getDay());
						//System.out.println(apps.get(0).getTime());
						calendar[day][time] = apps.get(0);
						
					}
				}
			}
			else if(apps.get(0).getServiceType().equals("Consultation")) 
			{
				time = doubleTimeIndex(apps.get(0).getTime())[0]; 
				time2 = doubleTimeIndex(apps.get(0).getTime())[1]; 
				if(calendar[day][time] == null && calendar[day][time2] == null)
				{
					//System.out.println(apps.get(0).getUsername() + apps.get(0).getTime());
					calendar[day][time] = apps.get(0);
					calendar[day][time+1] = apps.get(0);
				}	
				else
				{
					if(apps.get(0).getFlexibility())
					{
						reschedule(apps.get(0));
						time = doubleTimeIndex(apps.get(0).getTime())[0]; 
						time2 = doubleTimeIndex(apps.get(0).getTime())[1];
						day = dayIndex(apps.get(0).getDay());
						//System.out.println(apps.get(0).getTime());
						calendar[day][time] = apps.get(0);
						calendar[day][time2] = apps.get(0);
						
					}
				}
			}
			
			apps.remove(0);
			//System.out.println(calendar[day][time].toString());
		}
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(j<7 && calendar[i][j] != null && calendar[i][j+1] != null && calendar[i][j].equals(calendar[i][j+1]))
					j++;
				if(calendar[i][j] != null)
					System.out.println(calendar[i][j].toString());
			}
		}
		

	}
	
	public static void reschedule(Appointment a)
	{
		int day = dayIndex(a.getDay());
		for(int i = 0; i < 8; i++)
		{
			if(a.getServiceType().equals("Interview"))
			{
				if(calendar[day][i] == null)
				{
					a.setTime(indexToTime(i));
					break;
				}
			}
			if(a.getServiceType().equals("Consultation"))
			{
				if(i<7 && calendar[day][i] == null && calendar[day][i+1]==null)
				{
					a.setTime(indexToTime(i));
					break;
				}
			}
			if(i==7) {
				a.setDay(indexToDay(day+1));
				a.setTime("12:00");
				} 
		}
	}
	
	public static int singleTimeIndex(String s)
	{
		int x = 0;
		if(s=="12:00")
			x=0;
		else if(s=="12:30")
			x=1;
		else if(s=="1:00")
			x=2;
		else if(s=="1:30")
			x=3;
		else if(s=="2:00")
			x=4;
		else if(s=="2:30")
			x=5;
		else if(s=="3:00")
			x=6;
		else if(s=="3:30")
			x=7;
		return x;
	}
	public static int[] doubleTimeIndex(String s)
	{
		int[] x = new int[2];
		if(s=="12:00")
		{	x[0] = 0;
			x[1] = 1;}
		else if(s=="12:30")
		{	x[0] = 1;
			x[1] = 2;}	
		else if(s=="1:00")
		{	x[0] = 2;
			x[1] = 3;}	
		else if(s=="1:30")
		{	x[0] = 3;
			x[1] = 4;}	
		else if(s=="2:00")
		{	x[0] = 4;
			x[1] = 5;}	
		else if(s=="2:30")
		{	x[0] = 5;
			x[1] = 6;}	
		else if(s=="3:00")
		{	x[0] = 6;
			x[1] = 7;}
		else if(s=="3:30")
			x[0] = 0;
		return x;
	}
	public static int dayIndex(String s)
	{
		int x = 0;
		if(s.equals("Monday"))
			x = 0;
		else if(s.equals("Tuesday"))
			x=1;
		else if(s.equals("Wednesday"))
			x=2;
		else if(s.equals("Thursday"))
			x=3;
		else if(s.equals("Friday"))
			x=4;
		return x;
		
	}
	public static String indexToTime(int x)
	{
		String time = "";
		if(x == 0)
			time = "12:00";
		else if(x == 1)
			time = "12:30";
		else if(x == 2)
			time = "1:00";
		else if(x == 3)
			time = "1:30";
		else if(x == 4)
			time = "2:00";
		else if(x == 5)
			time = "2:30";
		else if(x == 6)
			time = "3:00";
		else if(x == 7)
			time = "3:30";
		return time;
	}
	public static String indexToDay(int x)
	{
		String s = "";
		if(x==0)
			s="Monday";
		else if(x==1)
			s="Tuesday";
		else if(x==2)
			s="Wednesday";
		else if(x==3)
			s="Thursday";
		else if(x==4)
			s="Friday";
		return s;
	}

}

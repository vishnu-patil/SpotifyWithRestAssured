package Utility;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateAndTime {

	public static String getDateTime()
	{
		LocalDate date = LocalDate.now();
		
		LocalTime time = LocalTime.now().withNano(0);
		
		String dateTime = ""+date +"/"+ time;
		
		return dateTime;
	}
	
}

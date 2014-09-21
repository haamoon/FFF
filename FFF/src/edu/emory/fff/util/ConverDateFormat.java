package edu.emory.fff.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConverDateFormat {
	
	
	public static String convertToString(Calendar cal){
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		Date d = cal.getTime();
		String date_str = formatter.format(d);
		return date_str;
		
	}
	public static Calendar convertToCalendar(String date) throws ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(formatter.parse(date));
		return calendarDate;
		
	}
}

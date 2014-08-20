package com.ebay.gese.epm.db;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTest {
	
	public static void main (String [] args) throws ParseException{
		Date dNow = new Date();
		//System.out.println("curent date is " + dNow.toString());

		DateFormat formatter = new SimpleDateFormat("dd-MMM-yy.HH:mm:ss");
		String uiDate = formatter.format(dNow);
		//System.out.println("ui format date is " + uiDate);
		
		//DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		//Date d1 = df.parse("08-Apr-14.21:29:23");
		//System.out.println(d1.toString());
		
		//Convert UI format and db format
		DateFormat inputDateFormat = new SimpleDateFormat("dd-MMM-yy.HH:mm:ss");
		DateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dbDate = outputDateFormat.format(inputDateFormat.parse("08-Apr-14.21:29:23"));
		//System.out.println("db format date "+ dbDate);
		
		//compare dates
		//Date d1 = outputDateFormat.parse("2014-04-08 09:53:44");
		Date d1 = outputDateFormat.parse("2014-04-08 09:53:44");
		//Date d2 = outputDateFormat.parse(dbDate);
		Date d2 = formatter.parse("08-Apr-14.21:29:23");
		System.out.println("d1 is "+ d1.toString());
		System.out.println("d2 is "+ d2.toString());
		if(d1.after(d2)){
    		System.out.println("D1 is after D2");
    	}

    	if(d1.before(d2)){
    		System.out.println("D1 is before D2");
    	}

    	if(d1.equals(d2)){
    		System.out.println("Date1 is equal Date2");
    	}
		
	}

}

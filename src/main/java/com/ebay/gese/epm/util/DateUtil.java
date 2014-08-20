package com.ebay.gese.epm.util;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

/*
 * Util Methods for date manipulation
 */
public class DateUtil {
	
	public static boolean compareTimeStamps(String uiTime, String dbTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date uiDate = null;
		Date dbDate = null;
		try {
			uiDate = df.parse(uiTime);
			dbDate = df1.parse(dbTime); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (uiDate.after(dbDate)) {
			//System.out.println("API timestamp is newer!!");
			return true;		
		} else {
			//System.out.println("API timestamp is older!!");
			return false;
		}
	}
	
	public static String normalizeDate(String apiDate) {
		DateFormat df = new SimpleDateFormat("dd-MMM-yy.HH:mm:ss");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date uiDate = null;
		String dbDate = null;
		try {
			uiDate = df.parse(apiDate);
			dbDate = df1.format(uiDate); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbDate;
	}
	
	public static String normalizeDBDate(String dbDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date origDate = null;
		String normalizedDate = null;
		try {
			origDate = df.parse(dbDate);
		    normalizedDate = df1.format(origDate); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return normalizedDate;
	}
	
	public static boolean compareDBTimeStamps(String currentTime, String dbTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = null;
		Date dbDate = null;
		try {
			currentDate = df.parse(currentTime);
			dbDate = df1.parse(dbTime); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (currentDate.after(dbDate)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static Timestamp getCurrentTime() {
		java.util.Date date= new java.util.Date();
		return new Timestamp(date.getTime());
	}
	
	public static void main (String [] args) {
		//System.out.println(getCurrentTime());
		System.out.println(normalizeDate("15-May-14.12:42:10"));
	}

}

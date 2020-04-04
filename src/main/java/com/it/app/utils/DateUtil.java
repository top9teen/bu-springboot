package com.it.app.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static String YYYYMMDD = "yyyy/MM/dd";
	public static String DD_MM_YYYY = "dd-MM-yyyy";
	public static String YYYY_MM_DD = "yyyy-MM-dd";
	public static String MMDDYYYY = "MM/dd/yyyy";
	public static String DDMMYYYY = "dd/MM/yyyy";
	public static Date stringToDate(String date, String format) {
		  
	    Date date1 = null;
	    if(StringUtil.isNotNull(date)) {
			try {
				date1 = new SimpleDateFormat(format,Locale.ENGLISH).parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    }

		return date1;
	}
	
	public static String dateToString(Date date, String format) {
	    if(StringUtil.isNull(format)) {
	    	format = YYYY_MM_DD;
	    }
	    DateFormat df = new SimpleDateFormat(format,Locale.ENGLISH);
	    String todayAsString = df.format(date);
		return todayAsString;
	}
	


	
    public static String stringFormat(String date, String formFormat, String toFormat) {
		String result = null;
		if (null == toFormat) {
			formFormat = DateUtil.DD_MM_YYYY;;
		}
		if (null != date) {
			if (date.substring(6, 8).equals("00") || date.substring(4, 6).equals("00")) {
				result = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
			} else {
				SimpleDateFormat fromUser = new SimpleDateFormat(formFormat);
				SimpleDateFormat myFormat = new SimpleDateFormat(toFormat);
				try {
					result = myFormat.format(fromUser.parse(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}
    
}

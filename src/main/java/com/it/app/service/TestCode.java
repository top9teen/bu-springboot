package com.it.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestCode {
	public static void main(String[] args) throws ParseException {
		  
		  String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
		  String dateStr = "2019-03-05T12:08:56.235";
		  SimpleDateFormat format = new SimpleDateFormat(pattern,Locale.US);
		  Date date = format.parse(dateStr);
		  System.out.println(date);
	}
}

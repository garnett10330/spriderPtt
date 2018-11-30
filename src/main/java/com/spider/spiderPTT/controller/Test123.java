package com.spider.spiderPTT.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test123 {

	public static void main(String[] args) {
        String dateStr = "7/10";
  	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
	  	Date date;
	    try {
	        date = sdf.parse(dateStr);
	        System.out.println(date);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    int year = cal.get(Calendar.YEAR);
	    System.out.println(year);
	    String abc = year+"/"+dateStr;
	    System.out.println(new SimpleDateFormat("yyyy").format(new Date()));
	}

}

package com.eromanga.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TimeMessage {
	private String date;
	public TimeMessage(){
		this.date = getTime();
	}
	public String getDate() {
		return date;
	}
	private String getTime(){  
        long time=System.currentTimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date=new Date(time);  
        return format.format(date);  
    }
}

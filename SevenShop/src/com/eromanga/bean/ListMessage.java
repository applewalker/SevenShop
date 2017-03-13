package com.eromanga.bean;



import java.sql.Date;
import java.text.SimpleDateFormat;

public class ListMessage {
	private int type;
	private Date date;
	private String sdate;
	private String content;
	public ListMessage(){
		
	}
	public ListMessage(int _type,String _content){
		this.setType(_type);
		this.setContent(_content);
		getTime();
		
	}
	private void getTime(){  
        long time=System.currentTimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        date=new Date(time);  
        setSdate(format.format(date));  
        
    }
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}  
}

package com.eromanga.bean;


import com.eromanga.utils.Prefs;

public class UserRequest extends TimeMessage{
	private String key = Prefs.API_KEY;
	private String info;
	private String userid = Prefs.USER_ID;
	public UserRequest (String msg) {
		this.info = msg;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}

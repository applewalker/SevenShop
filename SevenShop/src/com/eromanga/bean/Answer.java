package com.eromanga.bean;


public class Answer extends TimeMessage{
	private int code;
	private String text;
	public Answer () {
		
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}

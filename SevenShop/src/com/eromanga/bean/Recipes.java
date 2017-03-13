package com.eromanga.bean;

import java.util.List;

public class Recipes extends Answer{
	private List<Course> list;
	private CharSequence chs;
	public static class Course{
		public String name;
		public String icon;
		public String info;
		public String detailurl;
	}
	public List<Course> getList() {
		return list;
	}
	public void setList(List<Course> list) {
		this.list = list;
	}
	public CharSequence getChs() {
		return chs;
	}
	public void setChs(CharSequence chs) {
		this.chs = chs;
	}
	
}

package com.eromanga.bean;

import java.util.List;

public class News extends Answer{
	private List<Article> list;
	private CharSequence chs;
	public static class Article{
		public String article;
		public String source;
		public String icon;
		public String detailurl;
		
	}
	public List<Article> getList() {
		return list;
	}
	public void setList(List<Article> list) {
		this.list = list;
	}
	public CharSequence getChs() {
		return chs;
	}
	public void setChs(CharSequence chs) {
		this.chs = chs;
	}

}

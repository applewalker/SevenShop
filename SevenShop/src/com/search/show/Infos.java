package com.search.show;

import java.util.List;

public class Infos {
	private String	newsLast = "0";
	private int	type = 0;
	private List<goodsInfo>	newsInfos;

	public String getNewsLast() {
		return newsLast;
	}

	public void setNewsLast(String newsLast) {
		this.newsLast = newsLast;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<goodsInfo> getNewsInfos() {
		return newsInfos;
	}

	public void setNewsInfos(List<goodsInfo> newsInfos) {
		this.newsInfos = newsInfos;
	}

}

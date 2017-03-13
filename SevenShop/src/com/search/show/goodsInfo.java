package com.search.show;

public class goodsInfo {

	private int height;
	private String albid = "";
	private String msg = "";
	private String isrc = "";
	private String price = "";

	public int getWidth(){
		return 200;
	}
	public String getAlbid() {
		return albid;
	}

	public void setAlbid(String albid) {
		this.albid = albid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getPrice(){
		return price;
	}
	
	public void setPrice(String price){
		
		this.price=price;
	}

}

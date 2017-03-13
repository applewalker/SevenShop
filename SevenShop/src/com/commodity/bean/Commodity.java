package com.commodity.bean;

public class Commodity {
	private int id;				//编号
	private String picurl; 	    //商品图片
	private String name;		//名称
	private float price;		//价格
	private float expressPrice; //运费
	private int sales;			//销量
	private String area;		//产地
	private String intro;       //简介
	private String shop;		//店铺
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getExpressPrice() {
		return expressPrice;
	}
	public void setExpressPrice(float expressPrice) {
		this.expressPrice = expressPrice;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
}

package com.shop.entity;

import java.security.PrivilegedActionException;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

public class Goods {
	
	private int goods_id;
	private String goods_name;
	private int type_id;
	private String image_path;
	private int fare;
	private int salesvolume;
	private String place;
	private String introduction;
	private float mainprice;
    private int user_id;
	private List<Scharacheristtype> type;
	
	
	

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public List<Scharacheristtype> getType() {
		return type;
	}
	public void setType(List<Scharacheristtype> type) {
		this.type = type;
	}
	public float getMainprice() {
		return mainprice;
	}
	public void setMainprice(float mainprice) {
		this.mainprice = mainprice;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public int getSalesvolume() {
		return salesvolume;
	}
	public void setSalesvolume(int salesvolume) {
		this.salesvolume = salesvolume;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	

}

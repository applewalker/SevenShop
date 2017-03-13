package com.cart.bean;

public class AddressBean {
	
	private int address_id;         //
	private String name;			//收货人姓名
	private int user_id;
	private String smearedAddress;	//区地址信息
	private String detailAddress;	//详细地址信息
	
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSmearedAddress() {
		return smearedAddress;
	}
	public void setSmearedAddress(String smearedAddress) {
		this.smearedAddress = smearedAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	
	
	
	
}

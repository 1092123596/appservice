package com.xdtech.platform.pay.bean;

public class PayNow {
	
	private int id;
	
	private int shopId;
	
	private String img;
	
	private String productName;
	
	private int payPrice;
	
	private int carCount;
	
	private int price;
	
	private int carLevel;
	
	public int getCarLevel() {
		return carLevel;
	}

	public void setCarLevel(int carLevel) {
		this.carLevel = carLevel;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public int getCarCount() {
		return carCount;
	}

	public void setCarCount(int carCount) {
		this.carCount = carCount;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}

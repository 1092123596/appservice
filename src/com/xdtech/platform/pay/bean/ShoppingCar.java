package com.xdtech.platform.pay.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 购物车
 * @author Xuc
 *
 * @date 2014-11-12 下午02:23:28
 */
@Entity
@Table(name = "mt_shoppingcar")
public class ShoppingCar {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 用户ID
	 */
	private String userid;
	/**
	 * 服务id
	 */
	private int projectId;
	/**
	 * 服务名称
	 */
	private String projectName;
	/**
	 * 服务id
	 */
	private int productId;
	/**
	 * 服务名称
	 */
	private String productName;
	/**
	 * 服务商id
	 */
	private int shopId;
	/**
	 * 服务商名称
	 */
	private String shopName;
	
	/**
	 * 数量
	 */
	private int carCount;
	/**
	 * 车型ID
	 */
	private int styleId;
	/**
	 * 车型名称
	 */
	private String styleName;
	/**
	 * 支付方式
	 */
	private int paytype;
	
	/**
	 * 0工时产品，1仅工时
	 */
	private int servicetype;
	/**
	 * 删除标识
	 */
	private int deleted;
	/**
	 * 店面价格
	 */
	private int dmprice;
	/**
	 * 网购价格
	 */
	private int wgprice;
	/**
	 * 定金价格
	 */
	private int djprice;
	/**
	 * 创建时间
	 */
	private Date datetime;
	/**
	 * 车型，价格区间
	 */
	private int parm;
	/**
	 * 产品图片
	 */
	private String img;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getCarCount() {
		return carCount;
	}
	public void setCarCount(int carCount) {
		this.carCount = carCount;
	}
	public int getStyleId() {
		return styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public int getServicetype() {
		return servicetype;
	}
	public void setServicetype(int servicetype) {
		this.servicetype = servicetype;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getDmprice() {
		return dmprice;
	}
	public void setDmprice(int dmprice) {
		this.dmprice = dmprice;
	}
	public int getWgprice() {
		return wgprice;
	}
	public void setWgprice(int wgprice) {
		this.wgprice = wgprice;
	}
	public int getDjprice() {
		return djprice;
	}
	public void setDjprice(int djprice) {
		this.djprice = djprice;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getParm() {
		return parm;
	}
	public void setParm(int parm) {
		this.parm = parm;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}

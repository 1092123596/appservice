
package com.xdtech.platform.minormt.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 4s店实体类
 * 
 * @author mj
 */
@Entity
@Table(name = "gc_shop")
public class Shop implements java.io.Serializable {

	/**
	 * 4s店id
	 */
	private int shopId;

	/**
	 * 4s店名称（门店名称，简称）
	 */
	private String shopName;

	/**
	 * 省编号
	 */
	private String provinceNo;

	/**
	 * 省名称
	 */
	private String provinceName;

	/**
	 * 市编号
	 */
	private String cityNo;

	/**
	 * 市名称
	 */
	private String cityName;

	/**
	 * 地址
	 */
	private String addr;

	/**
	 * 销售电话
	 */
	private String saleTel;

	/**
	 * 联系电话
	 */
	private String contactNo;
	/**
	 * 联系电话2
	 */
	private String contactNo_2;

	/**
	 * 主营品牌
	 */
	private String brand;

	/**
	 * Logo
	 */
	private String logo;

	/**
	 * 介绍
	 */
	private String intro;

	/**
	 * 上传图片
	 */
	private String image;

	/**
	 * 星级
	 */
	private int levels;

	/**
	 * 信息显示状态，0为不显示，1为显示
	 */
	private int showFlag;

	/**
	 * 期限
	 */
	private int limitTime;

	/**
	 * 订金方式
	 */
	private int orderStyle;

	/**
	 * 订金内容
	 */
	private BigDecimal orderDetail;

	/**
	 * 状态
	 */
	private int state;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createby;

	/**
	 * 删除标识
	 */
	private int deleted;
	/**
	 * 关键字
	 */
	private String keyWord;
	/**
	 * 关键字匹配
	 */
	private String keyWords;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 签约4S店
	 */
	private int coopshop;
	/**
	 * 排序4S店
	 */
	private int descshop;
	
	private String taskId;
	/**
	 * 4S店所在商圈
	 */
	private String focus;
	/**
	 * 二级域名
	 */
	private String ernet;
	/**
	 * 是否4S店  类别   0：维保服务，1：卖车
	 */
	private Integer is4sShop;
	/**
	 * 商圈
	 */
	private String bussArea;
	/**
	 * 企业形象LOGO
	 */
	private String entertainLogo;
	/**
	 * 零配件
	 */
	private String accessory;
	/**
	 * 接受支付方式
	 */
	private String buyway;
	/**
	 * 主营业务
	 */
	private String professionalBuss;
	/**
	 * 父级经销商ID  为0则是总店
	 */
	private Integer fatherShopId;
	
	/**
	 * wifi 0没有 1有
	 */
	private Integer wifi;
	/**
	 * 免费茶水 0没有 1有
	 */
	private Integer tea;
	/**
	 * 休息区  0没有 1有
	 */
	private Integer rest;
	/**
	 * 支持刷卡  0没有 1有
	 */
	private Integer swipeInCard;
	/**
	 * 联系人电话
	 */
	private String phone;
	/**
	 * 联系人邮箱
	 */
	private String email;
	/**
	 * 机构图
	 */
	private String img;
	/**
	 * 营业时间早
	 */
	private String mtime;
	/**
	 * 营业时间晚
	 */
	private String atime;
	/**
	 * 机构简称（企业全称）
	 */
	private String shortName;
	/**
	 * 查询服务列表页  最近几人购买
	 * @return
	 */
	private int buyNum;	
	/**
	 * 查询所有服务列表页   几人评价
	 * @return
	 */
	private int viewNum;	
	/**
	 * 产品评分
	 * @return
	 */
	private double productNum;
	/**
	 * 经度
	 * @return
	 */
	private String lng;
	/**
	 * 纬度
	 * @return
	 */
	private String lat;
	/**
	 * 类别   0：维修厂，1：4S店
	 * @return
	 */
	private Integer classification;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getProvinceNo() {
		return this.provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityNo() {
		return this.cityNo;
	}
 
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getSaleTel() {
		return this.saleTel;
	}

	public void setSaleTel(String saleTel) {
		this.saleTel = saleTel;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLevels() {
		return this.levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public int getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(int showFlag) {
		this.showFlag = showFlag;
	}

	public int getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public int getOrderStyle() {
		return orderStyle;
	}

	public void setOrderStyle(int orderStyle) {
		this.orderStyle = orderStyle;
	}

	public BigDecimal getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(BigDecimal orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCoopshop() {
		return coopshop;
	}

	public void setCoopshop(int coopshop) {
		this.coopshop = coopshop;
	}

	public int getDescshop() {
		return descshop;
	}

	public void setDescshop(int descshop) {
		this.descshop = descshop;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}
	
	public Integer getIs4sShop() {
		return is4sShop;
	}
	public void setIs4sShop(Integer is4sShop) {
		this.is4sShop = is4sShop;
	}
	public String getBussArea() {
		return bussArea;
	}
	public void setBussArea(String bussArea) {
		this.bussArea = bussArea;
	}

	public String getEntertainLogo() {
		return entertainLogo;
	}

	public void setEntertainLogo(String entertainLogo) {
		this.entertainLogo = entertainLogo;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	public Integer getFatherShopId() {
		return fatherShopId;
	}

	public void setFatherShopId(Integer fatherShopId) {
		this.fatherShopId = fatherShopId;
	}
	public String getBuyway() {
		return buyway;
	}
	public void setBuyway(String buyway) {
		this.buyway = buyway;
	}

	public String getProfessionalBuss() {
		return professionalBuss;
	}

	public void setProfessionalBuss(String professionalBuss) {
		this.professionalBuss = professionalBuss;
	}

	public String getContactNo_2() {
		return contactNo_2;
	}

	public void setContactNo_2(String contactNo_2) {
		this.contactNo_2 = contactNo_2;
	}

	public Integer getWifi() {
		return wifi;
	}

	public void setWifi(Integer wifi) {
		this.wifi = wifi;
	}

	public Integer getTea() {
		return tea;
	}

	public void setTea(Integer tea) {
		this.tea = tea;
	}

	public Integer getRest() {
		return rest;
	}

	public void setRest(Integer rest) {
		this.rest = rest;
	}

	public Integer getSwipeInCard() {
		return swipeInCard;
	}

	public void setSwipeInCard(Integer swipeInCard) {
		this.swipeInCard = swipeInCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String getAtime() {
		return atime;
	}

	public void setAtime(String atime) {
		this.atime = atime;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Transient
	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	@Transient
	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	@Transient
	public double getProductNum() {
		return productNum;
	}
	public void setProductNum(double productNum) {
		this.productNum = productNum;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLng() {
		return lng;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLat() {
		return lat;
	}

	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getErnet() {
		return ernet;
	}

	public void setErnet(String ernet) {
		this.ernet = ernet;
	}
	public Integer getClassification() {
		return classification;
	}
	public void setClassification(Integer classification) {
		this.classification = classification;
	}
}

package com.xdtech.platform.minormt.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 大小保养实体
 * 
 * @author hyh 网购价 :webprice
 * 
 */
@Entity
@Table(name = "mt_minormt")
public class MtMinormt {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 保养名称
	 */
	private String productname;
	/**
	 * 服务名称ID
	 */
	private int projectid;
	/**
	 * 4S店ID
	 */
	private int shopid;
	/**
	 * 机油产品ID
	 */
	private int engproductid;
	/**
	 * 机滤产品ID
	 */
	private int filproductid;
	/**
	 * 空滤产品ID
	 */
	private int fkproductid;
	/**
	 * 燃油滤芯产品ID
	 */
	private int frproductid;
	/**
	 * 品牌ID
	 */
	private int brandid;
	/**
	 * 服务名称
	 */
	private String brandname;
	/**
	 * 车系ID
	 */
	private int deptid;
	/**
	 * 车系名称
	 */
	private String deptname;

	/**
	 * 车型ID
	 */
	private int styleid;
	/**
	 * 车型名称
	 */
	private String stylename;
	/**
	 * 购买次数
	 */
	private int number;

	/**
	 * 启用禁用
	 */
	private int state;
	/**
	 * 删除
	 */
	private int deleted;
	/**
	 * 店面价格(最终店面价)
	 */
	private int dmprice;
	/**
	 * 网购价格(最终网购价)
	 */
	private int webprice;
	/**
	 * 店面工时价格
	 */
	private int hdmprice;
	/**
	 * 网购工时价格
	 */
	private int hwprice;
	/**
	 * 类型
	 */
	private int servicetype;
	/**
	 * 优惠价格
	 */
	private int yhprice;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 优惠价格
	 */
	private int flag;
	/**
	 * 产品上架、下架,0为上架、1为下架,默认为0
	 */
	private int carriage;
	/**
	 * 详细
	 */
	private String explicit;

	/**
	 * 适用商家
	 */
	private String applyShop;

	/**
	 * 父品牌ID
	 */
	private int prevMenu;

	/**
	 * 机油类型   1:原厂 ,2:品牌
	 */
	private int engineType;
	
	/**
	 * 机滤类型 1:原厂 ,2:品牌
	 */
	private int filterType;
	
	/**
	 * 是不是4S店 1:是 ,2:否
	 */
	private int is4sShop;
	
	/**
	 * 价格区间
	 */
	private int priceRange;
	
	/**
	 * 区域Id
	 */
	private String areaNo;
	
	/**
	 * 是否先显示通用产品 0:否,1:是
	 */
	private int isTongYong;
	/**
	 * 积分状态：1适用积分比例，2适用全部积分
	 */
	private int integraltype;
	/**
	 * 积分状态：1适用积分比例，2适用全部积分
	 */
	private String img;
	/**
	 * 一句话介绍
	 */
	private String introduce;
	
	
	private int orderByPriceType;
	
	/*private int orderByBuyNumType;
	
	private int orderByViewNumType;*/
	/**
	 * 服务详细模板id
	 */
	private int template;
	
	/**
	 * 消费积分比例
	 */
	private BigDecimal percentage = new BigDecimal(0);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public int getEngproductid() {
		return engproductid;
	}

	public void setEngproductid(int engproductid) {
		this.engproductid = engproductid;
	}

	public int getFilproductid() {
		return filproductid;
	}

	public void setFilproductid(int filproductid) {
		this.filproductid = filproductid;
	}

	public int getBrandid() {
		return brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public int getStyleid() {
		return styleid;
	}

	public void setStyleid(int styleid) {
		this.styleid = styleid;
	}

	public String getStylename() {
		return stylename;
	}

	public void setStylename(String stylename) {
		this.stylename = stylename;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

	public int getWebprice() {
		return webprice;
	}

	public void setWebprice(int webprice) {
		this.webprice = webprice;
	}

	public int getYhprice() {
		return yhprice;
	}

	public void setYhprice(int yhprice) {
		this.yhprice = yhprice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getHdmprice() {
		return hdmprice;
	}

	public void setHdmprice(int hdmprice) {
		this.hdmprice = hdmprice;
	}

	public int getHwprice() {
		return hwprice;
	}

	public void setHwprice(int hwprice) {
		this.hwprice = hwprice;
	}

	public int getServicetype() {
		return servicetype;
	}

	public void setServicetype(int servicetype) {
		this.servicetype = servicetype;
	}

	public int getFkproductid() {
		return fkproductid;
	}

	public void setFkproductid(int fkproductid) {
		this.fkproductid = fkproductid;
	}

	public int getFrproductid() {
		return frproductid;
	}

	public void setFrproductid(int frproductid) {
		this.frproductid = frproductid;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getCarriage() {
		return carriage;
	}

	public void setCarriage(int carriage) {
		this.carriage = carriage;
	}

	public String getExplicit() {
		return explicit;
	}

	public void setExplicit(String explicit) {
		this.explicit = explicit;
	}

	public String getApplyShop() {
		return applyShop;
	}

	public void setApplyShop(String applyShop) {
		this.applyShop = applyShop;
	}

	@Transient
	public int getPrevMenu() {
		return prevMenu;
	}

	public void setPrevMenu(int prevMenu) {
		this.prevMenu = prevMenu;
	}
	@Transient
	public int getEngineType() {
		return engineType;
	}
	public void setEngineType(int engineType) {
		this.engineType = engineType;
	}
	@Transient
	public int getFilterType() {
		return filterType;
	}
	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}
	@Transient
	public int getIs4sShop() {
		return is4sShop;
	}
	public void setIs4sShop(int is4sShop) {
		this.is4sShop = is4sShop;
	}
	@Transient
	public int getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(int priceRange) {
		this.priceRange = priceRange;
	}
	@Transient
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	@Transient
	public int getIsTongYong() {
		return isTongYong;
	}
	public void setIsTongYong(int isTongYong) {
		this.isTongYong = isTongYong;
	}
	@Transient
	public int getOrderByPriceType() {
		return orderByPriceType;
	}

	public void setOrderByPriceType(int orderByPriceType) {
		this.orderByPriceType = orderByPriceType;
	}

	public void setIntegraltype(int integraltype) {
		this.integraltype = integraltype;
	}

	public int getIntegraltype() {
		return integraltype;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImg() {
		return img;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getIntroduce() {
		return introduce;
	}

	public int getTemplate() {
		return template;
	}

	public void setTemplate(int template) {
		this.template = template;
	}
	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
}

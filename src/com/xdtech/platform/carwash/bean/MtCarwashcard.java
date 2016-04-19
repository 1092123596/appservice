package com.xdtech.platform.carwash.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 洗车实体类
 * 
 * @author hyh
 * 
 */
@Entity
@Table(name = "mt_carwashcard")
public class MtCarwashcard {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 4s店id
	 */
	private int shopId;
	/**
	 * 服务id
	 */
	private int projectId;
	/**
	 * 服务名称
	 */
	private String projectName;
	/**
	 * 小型车洗车店面价
	 */
	private BigDecimal xhprice;
	/**
	 * 小型车洗车网购价
	 */
	private BigDecimal xhwprice;
	/**
	 * SUV洗车店面价
	 */
	private BigDecimal shprice;
	/**
	 * SUV洗车网购价
	 */
	private BigDecimal shwprice;
	/**
	 * 商务/面包洗车店面价
	 */
	private BigDecimal mhprice;
	/**
	 * 商务/面包洗车网购价
	 */
	private BigDecimal mhwprice;
	/**
	 * 小型车洗车店面洗车次数
	 */
	private int xhnumber;
	/**
	 * 小型车洗车网购洗车次数
	 */
	private int xhwnumber;
	/**
	 * SUV洗车店面洗车次数
	 */
	private int shnumber;
	/**
	 * SUV洗车网购洗车次数
	 */
	private int shwnumber;
	/**
	 * 商务/面包洗车店面洗车次数
	 */
	private int mhnumber;
	/**
	 * 商务/面包洗车网购洗车次数
	 */
	private int mhwnumber;
	/**
	 * 一句话介绍
	 */
	private String explicit;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 启用禁用
	 */
	private int state;
	/**
	 * 删除标识
	 */
	private int deleted;
	/**
	 * 单次洗车/洗车
	 */
	private int servicetype;
	/**
	 * 小型车洗车赠送服务
	 */
	private String xpresent;
	/**
	 * 中型车洗车赠送服务
	 */
	private String zpresent;
	/**
	 * SUV洗车赠送服务
	 */
	private String spresent;
	/**
	 * 商务/面包洗车赠送服务
	 */
	private String mpresent;
	/**
	 * 活动标识 0普通 1活动
	 */
	private int flag;
	/**
	 * 产品上架、下架,0为上架、1为下架,默认为0
	 */
	private int carriage;
	/**
	 * 文本编译器详细
	 */
	private String detail;
	/**
	 * 适用商家
	 */
	private String applyShop;
	/**
	 * 洗车标题
	 */
	private String title;
	/**
	 * 图片
	 */
	private String img;
	/**
	 * 消费积分比例
	 */
	private BigDecimal percentage = new BigDecimal(0);
	
	private BigDecimal wprice;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public BigDecimal getXhprice() {
		return xhprice;
	}

	public void setXhprice(BigDecimal xhprice) {
		this.xhprice = xhprice;
	}

	public BigDecimal getXhwprice() {
		return xhwprice;
	}

	public void setXhwprice(BigDecimal xhwprice) {
		this.xhwprice = xhwprice;
	}

	public BigDecimal getShprice() {
		return shprice;
	}

	public void setShprice(BigDecimal shprice) {
		this.shprice = shprice;
	}

	public BigDecimal getShwprice() {
		return shwprice;
	}

	public void setShwprice(BigDecimal shwprice) {
		this.shwprice = shwprice;
	}

	public BigDecimal getMhprice() {
		return mhprice;
	}

	public void setMhprice(BigDecimal mhprice) {
		this.mhprice = mhprice;
	}

	public BigDecimal getMhwprice() {
		return mhwprice;
	}

	public void setMhwprice(BigDecimal mhwprice) {
		this.mhwprice = mhwprice;
	}

	public int getXhnumber() {
		return xhnumber;
	}

	public void setXhnumber(int xhnumber) {
		this.xhnumber = xhnumber;
	}

	public int getXhwnumber() {
		return xhwnumber;
	}

	public void setXhwnumber(int xhwnumber) {
		this.xhwnumber = xhwnumber;
	}

	public int getShnumber() {
		return shnumber;
	}

	public void setShnumber(int shnumber) {
		this.shnumber = shnumber;
	}

	public int getShwnumber() {
		return shwnumber;
	}

	public void setShwnumber(int shwnumber) {
		this.shwnumber = shwnumber;
	}

	public int getMhnumber() {
		return mhnumber;
	}

	public void setMhnumber(int mhnumber) {
		this.mhnumber = mhnumber;
	}

	public int getMhwnumber() {
		return mhwnumber;
	}

	public void setMhwnumber(int mhwnumber) {
		this.mhwnumber = mhwnumber;
	}

	public String getExplicit() {
		return explicit;
	}

	public void setExplicit(String explicit) {
		this.explicit = explicit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public int getServicetype() {
		return servicetype;
	}

	public void setServicetype(int servicetype) {
		this.servicetype = servicetype;
	}

	public String getXpresent() {
		return xpresent;
	}

	public void setXpresent(String xpresent) {
		this.xpresent = xpresent;
	}

	public String getZpresent() {
		return zpresent;
	}

	public void setZpresent(String zpresent) {
		this.zpresent = zpresent;
	}

	public String getSpresent() {
		return spresent;
	}

	public void setSpresent(String spresent) {
		this.spresent = spresent;
	}

	public String getMpresent() {
		return mpresent;
	}

	public void setMpresent(String mpresent) {
		this.mpresent = mpresent;
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

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDetail() {
		return detail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getApplyShop() {
		return applyShop;
	}

	public void setApplyShop(String applyShop) {
		this.applyShop = applyShop;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
	
	@Transient
	public BigDecimal getWprice() {
		return wprice;
	}

	public void setWprice(BigDecimal wprice) {
		this.wprice = wprice;
	}
}

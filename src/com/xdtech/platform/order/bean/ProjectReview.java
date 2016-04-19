package com.xdtech.platform.order.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 经销商服务项目表
 * 
 * @author szz
 */
@Entity
@Table(name = "mt_projectReview")
public class ProjectReview{
	/**
	 * 主键
	 */
	private String prId;
	/**
	 * 服务ID
	 */
	private Integer apId;
	/**
	 * 服务产品id
	 */
	private Integer pdId;
	/**
	 * 服务产品名称
	 */
	private String pdName;
	/**
	 * 经销商id
	 */
	private Integer shopId;
	/**
	 * 经销商名称
	 */
	private String shopName;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 服务项目名称
	 */
	private String apName;
	/**
	 * 服务态度
	 */
	private Integer apIntegral;
	/**
	 * 评论内容
	 */
	private String apContent;
	/**
	 * 评论时间
	 */
	private Date createTime;
	/**
	 * 删除状态标识
	 */
	private Integer deleted;
	/**
	 * 套餐评论id
	 */
	private Integer minsetid;
	/**
	 * 施工效果
	 */
	private Integer apIntegral1;
	/**
	 * 性价比
	 */
	private Integer apIntegral2;
	/**
	 * 工作效率
	 */
	private Integer apIntegral3;
	/**
	 * 施工环境
	 */
	private Integer apIntegral4;
	/**
	 * 详细链接
	 */
	private String url;
	
	/**
	 * 流水表Id
	 */
	private String orderFormId;
	/**
	 * 购买时间
	 */
	private String goumaiTime;
	/**
	 * 车型
	 */
	private String stylename;
	/**
	 * 服务名称
	 */
	private String projectname;
	/**
	 * 产品名称
	 */
	private String producttname;
	/**
	 * 用户头像
	 */
	private String userimg;
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(name = "prId", unique = true, nullable = false, insertable = true, updatable = true)
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	public Integer getApId() {
		return apId;
	}
	public void setApId(Integer apId) {
		this.apId = apId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getApName() {
		return apName;
	}
	public void setApName(String apName) {
		this.apName = apName;
	}
	public Integer getApIntegral() {
		return apIntegral;
	}
	public void setApIntegral(Integer apIntegral) {
		this.apIntegral = apIntegral;
	}
	public String getApContent() {
		return apContent;
	}
	public void setApContent(String apContent) {
		this.apContent = apContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getMinsetid() {
		return minsetid;
	}
	public void setMinsetid(Integer minsetid) {
		this.minsetid = minsetid;
	}
	public Integer getApIntegral1() {
		return apIntegral1;
	}
	public void setApIntegral1(Integer apIntegral1) {
		this.apIntegral1 = apIntegral1;
	}
	public Integer getApIntegral2() {
		return apIntegral2;
	}
	public void setApIntegral2(Integer apIntegral2) {
		this.apIntegral2 = apIntegral2;
	}
	public Integer getApIntegral3() {
		return apIntegral3;
	}
	public void setApIntegral3(Integer apIntegral3) {
		this.apIntegral3 = apIntegral3;
	}
	public Integer getApIntegral4() {
		return apIntegral4;
	}
	public void setApIntegral4(Integer apIntegral4) {
		this.apIntegral4 = apIntegral4;
	}
	public Integer getPdId() {
		return pdId;
	}
	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@Transient
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOrderFormId() {
		return orderFormId;
	}
	public void setOrderFormId(String orderFormId) {
		this.orderFormId = orderFormId;
	}
	@Transient
	public String getGoumaiTime() {
		return goumaiTime;
	}
	public void setGoumaiTime(String goumaiTime) {
		this.goumaiTime = goumaiTime;
	}
	@Transient
	public String getStylename() {
		return stylename;
	}
	public void setStylename(String stylename) {
		this.stylename = stylename;
	}
	@Transient
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	@Transient
	public String getProducttname() {
		return producttname;
	}
	public void setProducttname(String producttname) {
		this.producttname = producttname;
	}
	@Transient
	public String getUserimg() {
		return userimg;
	}
	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
	
}

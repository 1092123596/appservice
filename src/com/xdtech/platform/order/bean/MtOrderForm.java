package com.xdtech.platform.order.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 维保订单详细表
 * 
 * @author szz
 */
@Entity
@Table(name = "mt_orderForm")
public class MtOrderForm {
	/**
	 * 主键
	 */
	private String ofId;
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 服务项目id
	 */
	private Integer projectid;
	/**
	 * 经销商id
	 */
	private Integer shopId;
	/**
	 * 经销商产品id
	 */
	private Integer productid;
	/**
	 * 经销商名称
	 */
	private String shopname;
	/**
	 * 商品工时店面价
	 */
	private BigDecimal workDmHours;
	/**
	 * 工时费网购价
	 */
	private BigDecimal workWgHours;
	/**
	 * 配件店面价
	 */
	private BigDecimal pjDmHours;
	/**
	 * 配件网购价
	 */
	private BigDecimal pjWgHours;

	/**
	 * 经销商服务项目名称
	 */
	private String projectname;
	/**
	 * 经销商商品名称
	 */
	private String productname;
	/**
	 * 购买数量
	 */
	private Integer pmount;
	/**
	 * 支付方式(0在线全款，支付1订金，2免费预约)
	 */
	private String ofType;
	/**
	 * 赠送180币
	 */
	private Integer projectBi;
	/**
	 * 服务项目原价
	 */
	private BigDecimal ofCostPrice;
	/**
	 * 服务项目优惠价
	 */
	private BigDecimal ofVIPrice;
	/**
	 * 验证码
	 */
	private String verification;
	/**
	 * 删除标识(0正常数据，1删除，2订单过期，3取消订单);
	 */
	private Integer deleted;
	/**
	 * 支付金额
	 */
	private BigDecimal payPrice;
	/**
	 * 车型ID
	 */
	private Integer styleId;
	/**
	 * 车型名称
	 */
	private String styleName;
	private Integer apid;
	/**
	 * 订单状态 0 未支付 1已支付 2已消费
	 */
	private Integer orderState;
	/**
	 * 预约时间
	 */
	private Date ordertime;
	/**
	 * 详细页链接
	 */
	private String urlinfo;
	/**
	 * 服务图片
	 */
	private String img;
	/**
	 * 时间差
	 */
	private String orderservice;

	/**
	 * 用户评论数量
	 * 
	 * @return
	 */
	private int comcount;
	/**
	 * 商家二级域名
	 * 
	 * @return
	 */
	private String ernet;
	/**
	 * 机油
	 * 
	 * @return
	 */
	private String jiyou;
	/**
	 * 机滤
	 * 
	 * @return
	 */
	private String jilv;
	/**
	 * 空滤
	 * 
	 * @return
	 */
	private String konglv;
	/**
	 * 通用产品和普通产品的区别 0为普通产品，1为通用产品
	 */
	private Integer generaltype;
	/**
	 * 产品个数编号，选择产品数量多少，有多少产品编号
	 * 
	 * @return
	 */
	private Integer productnumber;
	/**
	 * 燃滤
	 * 
	 * @return
	 */
	private String ranlv;
	/**
	 * 0普通产品，1秒杀产品
	 * 
	 * @return
	 */
	private int miaosha;
	/**
	 * 订单消费时间
	 */
//	private Date consumeTime;
	private String consumeTime;
	/**
	 * 结算状态(0:未结算 1:手动已结算 2:自动结算 3:申请退款 4:确认退款)
	 */
	private int financeState;
	
	/**
	 * 服务订金比率
	 */
	private Integer subscription;
	/**
	 * 评论内容
	 */
	private String apContent;
	
	/**
	 * 消费积分
	 */
	private int integral;
	
	/**
	 * 汽车级别，小保养为0
	 */
	private int carLevel;

	/**
	 * 是否评论状态
	 */
	private int commentState;
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(name = "ofId", unique = true, nullable = false, insertable = true, updatable = true)
	public String getOfId() {
		return ofId;
	}

	public void setOfId(String ofId) {
		this.ofId = ofId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getProjectid() {
		return projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public BigDecimal getWorkDmHours() {
		return workDmHours;
	}

	public void setWorkDmHours(BigDecimal workDmHours) {
		this.workDmHours = workDmHours;
	}

	public BigDecimal getWorkWgHours() {
		return workWgHours;
	}

	public void setWorkWgHours(BigDecimal workWgHours) {
		this.workWgHours = workWgHours;
	}

	public BigDecimal getPjDmHours() {
		return pjDmHours;
	}

	public void setPjDmHours(BigDecimal pjDmHours) {
		this.pjDmHours = pjDmHours;
	}

	public BigDecimal getPjWgHours() {
		return pjWgHours;
	}

	public void setPjWgHours(BigDecimal pjWgHours) {
		this.pjWgHours = pjWgHours;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Integer getPmount() {
		return pmount;
	}

	public void setPmount(Integer pmount) {
		this.pmount = pmount;
	}

	public String getOfType() {
		return ofType;
	}

	public void setOfType(String ofType) {
		this.ofType = ofType;
	}

	public Integer getProjectBi() {
		return projectBi;
	}

	public void setProjectBi(Integer projectBi) {
		this.projectBi = projectBi;
	}

	public BigDecimal getOfCostPrice() {
		return ofCostPrice;
	}

	public void setOfCostPrice(BigDecimal ofCostPrice) {
		this.ofCostPrice = ofCostPrice;
	}

	public BigDecimal getOfVIPrice() {
		return ofVIPrice;
	}

	public void setOfVIPrice(BigDecimal ofVIPrice) {
		this.ofVIPrice = ofVIPrice;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getApid() {
		return apid;
	}

	public void setApid(Integer apid) {
		this.apid = apid;
	}

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	@Transient
	public String getUrlinfo() {
		return urlinfo;
	}

	public void setUrlinfo(String urlinfo) {
		this.urlinfo = urlinfo;
	}

	@Transient
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Transient
	public String getOrderservice() {
		return orderservice;
	}

	public void setOrderservice(String orderservice) {
		this.orderservice = orderservice;
	}

	@Transient
	public int getComcount() {
		return comcount;
	}

	public void setComcount(int comcount) {
		this.comcount = comcount;
	}

	@Transient
	public String getErnet() {
		return ernet;
	}

	public void setErnet(String ernet) {
		this.ernet = ernet;
	}

	@Transient
	public String getJiyou() {
		return jiyou;
	}

	public void setJiyou(String jiyou) {
		this.jiyou = jiyou;
	}

	@Transient
	public String getJilv() {
		return jilv;
	}

	public void setJilv(String jilv) {
		this.jilv = jilv;
	}

	@Transient
	public String getKonglv() {
		return konglv;
	}

	public void setKonglv(String konglv) {
		this.konglv = konglv;
	}

	@Transient
	public String getRanlv() {
		return ranlv;
	}

	public void setRanlv(String ranlv) {
		this.ranlv = ranlv;
	}
	@Transient
	public String getApContent() {
		return apContent;
	}

	public void setApContent(String apContent) {
		this.apContent = apContent;
	}

	public Integer getGeneraltype() {
		return generaltype;
	}

	public void setGeneraltype(Integer generaltype) {
		this.generaltype = generaltype;
	}

	public Integer getProductnumber() {
		return productnumber;
	}

	public void setProductnumber(Integer productnumber) {
		this.productnumber = productnumber;
	}

	public int getMiaosha() {
		return miaosha;
	}

	public void setMiaosha(int miaosha) {
		this.miaosha = miaosha;
	}

//	public Date getConsumeTime() {
//		return consumeTime;
//	}
//	
//	public void setConsumeTime(Date consumeTime) {
//		this.consumeTime = consumeTime;
//	}
	public String getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(String consumeTime) {
		this.consumeTime = consumeTime;
	}

	public int getFinanceState() {
		return financeState;
	}

	public void setFinanceState(int financeState) {
		this.financeState = financeState;
	}

	public Integer getSubscription() {
		return subscription;
	}

	public void setSubscription(Integer subscription) {
		this.subscription = subscription;
	}
	
	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}
	
	public int getCarLevel() {
		return carLevel;
	}

	public void setCarLevel(int carLevel) {
		this.carLevel = carLevel;
	}

	public int getCommentState() {
		return commentState;
	}

	public void setCommentState(int commentState) {
		this.commentState = commentState;
	}
	

	
	
	
}

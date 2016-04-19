package com.xdtech.platform.order.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


/**
 * 维保订单表
 * 
 * @author szz
 */
@Entity
@Table(name = "mt_order")
public class MtOrder {
	/**
	 * 主键
	 */
	private String orderId;
	/**
	 * 经销商id
	 */
	private Integer shopId;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 车型id
	 */
	private Integer styleId;
	/**
	 * 车型名称
	 */
	private String styleName;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 经销商名称
	 */
	private String shopName;
	/**
	 * 赠送180币
	 */
	private Integer orderBi;
	/**
	 * 订单类型(9:微信订单,8:移动端支付订单)
	 */  
	private Integer orderType;
	/** 
	 * 用户真实姓名
	 */
	private String orderRealName;
	/**
	 * 用户邮箱
	 */
	private String orderEmail;
	/**
	 * 用户电话
	 */
	private String orderPhone;
	/**
	 * 订单状态(0未支付，1已支付；消费状态存mt_orderform表)
	 */
	private Integer orderState;
	/**
	 * 下单时间
	 */
	private Date orderCreteTime;
	/**
	 * 订单金额
	 */
	private BigDecimal orderCostPrice;
	/**
	 * 订单优惠金额
	 */
	private BigDecimal orderVIPPrice;
	/**
	 * 订单支付金额
	 */
	private BigDecimal payPrice;
	/**
	 * 订单备注
	 */
	private String orderRemark;
	/**
	 * 删除标识(0正常数据，1删除，2订单过期，3取消订单);
	 */
	private Integer deleted;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 购买数量
	 */
	private Integer pcount;
	/**
	 * 取消订单时间
	 */
	private Date deltime;
	/**
	 * 支付时间
	 */
	private Date overtime;
	/**
	 * 订单详细集合
	 */
	private List<MtOrderForm> oflist;
	/**
	 * 预约时间 用在总后台
	 */
	private Date ordertime;
	
	/**
	 * 开始时间
	 */
	private String starttime;
	/**
	 * 结束时间
	 */
	private String endtime;
	/**
	 * 时间差
	 */
	private String servicetime;
	/**
	 * 购买方式
	 */
	private String ofType;
	/**
	 * 服务项目类型
	 */
	private int projectId;
	/**
	 * 服务项目类型名字
	 */
	private String projectname;
	/**
	 * 平安请求流水号
	 */
	private String reqTxnId;
	
	/**
	 * 流水表list
	 */
	private List<MtOrderForm> orderFormList;
	
	/**
	 * 支付方式  1:支付宝、2:财付通、3:银联、4:微信、5：浦发(SPDB，支付宝纯网关)、6:广发(GDB,支付宝纯网关)
	 */
	private int payType;
	/**
	 * 通过专属银行通道付款给与立减金额
	 */
	private BigDecimal cutPrice;
	/**
	 * 支付流水号，在系统中唯一且与订单号一一对应，目前仅支付宝支付需要使用
	 */
	private String payNo;
	
	/**
	 * 消费积分
	 */
	private int integral;
	
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(name = "orderId", unique = true, nullable = false, insertable = true, updatable = true)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getOrderBi() {
		return orderBi;
	}
	public void setOrderBi(Integer orderBi) {
		this.orderBi = orderBi;
	}
	public String getOrderRealName() {
		return orderRealName;
	}
	public void setOrderRealName(String orderRealName) {
		this.orderRealName = orderRealName;
	}
	public String getOrderEmail() {
		return orderEmail;
	}
	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}
	public String getOrderPhone() {
		return orderPhone;
	}
	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Date getOrderCreteTime() {
		return orderCreteTime;
	}
	public void setOrderCreteTime(Date orderCreteTime) {
		this.orderCreteTime = orderCreteTime;
	}
	public BigDecimal getOrderCostPrice() {
		return orderCostPrice;
	}
	public void setOrderCostPrice(BigDecimal orderCostPrice) {
		this.orderCostPrice = orderCostPrice;
	}
	public BigDecimal getOrderVIPPrice() {
		return orderVIPPrice;
	}
	public void setOrderVIPPrice(BigDecimal orderVIPPrice) {
		this.orderVIPPrice = orderVIPPrice;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getStyleId() {
		return styleId;
	}
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public BigDecimal getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	public Integer getPcount() {
		return pcount;
	}
	public void setPcount(Integer pcount) {
		this.pcount = pcount;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Transient
	public List<MtOrderForm> getOflist() {
		return oflist;
	}
	public void setOflist(List<MtOrderForm> oflist) {
		this.oflist = oflist;
	}
	public Date getDeltime() {
		return deltime;
	}
	public void setDeltime(Date deltime) {
		this.deltime = deltime;
	}
	public Date getOvertime() {
		return overtime;
	}
	public void setOvertime(Date overtime) {
		this.overtime = overtime;
	}
	@Transient
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
	@Transient
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	@Transient
	public String getOfType() {
		return ofType;
	}
	public void setOfType(String ofType) {
		this.ofType = ofType;
	}
	
	@Transient
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	@Transient
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	@Transient
	public String getServicetime() {
		return servicetime;
	}
	public void setServicetime(String servicetime) {
		this.servicetime = servicetime;
	}
	public String getReqTxnId() {
		return reqTxnId;
	}
	public void setReqTxnId(String reqTxnId) {
		this.reqTxnId = reqTxnId;
	}
	@Transient
	public List<MtOrderForm> getOrderFormList() {
		return orderFormList;
	}
	public void setOrderFormList(List<MtOrderForm> orderFormList) {
		this.orderFormList = orderFormList;
	}
	
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public BigDecimal getCutPrice() {
		return cutPrice;
	}
	public void setCutPrice(BigDecimal cutPrice) {
		this.cutPrice = cutPrice;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
}

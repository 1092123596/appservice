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
 * 商家服务 最新实体
 * 
 * @author ssb
 * 
 */
@Entity
@Table(name = "mt_agencyproject")
public class MtAgencyProject {
	/**
	 * 主键
	 */
	private Integer apid;
	/**
	 * 商家id
	 */
	private Integer shopId;
	/**
	 * 商家名称
	 */
	private String shopName;
	/**
	 * 项目id
	 */
	private Integer projectId;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 删除标志
	 */
	private Integer deleted;
	/**
	 * 是否审核(0审核通过，1未审核，默认为0)
	 */
	private Integer verify;
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 * 详情
	 */
	private String explicit;
	/**
	 * 描述
	 */
	private String description;
	/**
	 *生成时间
	 */
	private Date createTime;
	/**
	 * 服务订金比率
	 */
	private Integer subscription;
	/**
	 * 是否需要预约
	 */
	private int bespeak;
	/**
	 * 在线申请加价
	 */
	private Integer bespeaks;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 维保服务图片
	 */
	private String img;
	/**
	 * 对比图
	 */
	private String imgUrls;
	/**
	 * 施工时间 0 立等可取 1 1小时
	 */
	private int worktime;
	/**
	 * 4s店维保服务最低价
	 */
	private String minprice;

	/**
	 * 消费积分比例
	 */
	private BigDecimal percentage;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getApid() {
		return apid;
	}

	public void setApid(Integer apid) {
		this.apid = apid;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getExplicit() {
		return explicit;
	}

	public void setExplicit(String explicit) {
		this.explicit = explicit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSubscription() {
		return subscription;
	}

	public void setSubscription(Integer subscription) {
		this.subscription = subscription;
	}

	public Integer getBespeaks() {
		return bespeaks;
	}

	public void setBespeaks(Integer bespeaks) {
		this.bespeaks = bespeaks;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getBespeak() {
		return bespeak;
	}

	public void setBespeak(int bespeak) {
		this.bespeak = bespeak;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public int getWorktime() {
		return worktime;
	}

	public void setWorktime(int worktime) {
		this.worktime = worktime;
	}

	@Transient
	public String getMinprice() {
		return minprice;
	}

	public void setMinprice(String minprice) {
		this.minprice = minprice;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
}

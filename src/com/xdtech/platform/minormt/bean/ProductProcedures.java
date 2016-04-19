package com.xdtech.platform.minormt.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 4s店维保服务流程
 * @author zl
 *
 */
@Entity
@Table(name = "mt_productprocedures")
public class ProductProcedures {
	/**
	 * PK
	 */
	private int id;
	/**
	 * FK
	 */
	private int projectId;
	/**
	 * 服务流程名称
	 */
	private String names;
	/**
	 * 详细
	 */
	private String explicit;
	/**
	 * 流程图
	 */
	private String imgUrl;
	/**
	 * 关键字详细
	 */
	private int orders;
	/**
	 * 显示/隐藏
	 */
	private int showtag;
	/**
	 * 删除标识
	 */
	private int deleted;
	/**
	 * 添加时间
	 */
	private Date createTime;
	
	
	
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
	public int getShowtag() {
		return showtag;
	}
	public void setShowtag(int showtag) {
		this.showtag = showtag;
	}
	public String getExplicit() {
		return explicit;
	}
	public void setExplicit(String explicit) {
		this.explicit = explicit;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

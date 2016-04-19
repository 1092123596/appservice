package com.xdtech.platform.carwash.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 维保服务流程
 * @author
 *
 */
@Entity
@Table(name = "mt_projectprocedures")
public class ProjectProcedures {
	/**
	 * PK
	 */
	private int id;
	/**
	 * FK
	 */
	private int apid;
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
	 * 删除标识
	 */
	private int deleted;
	/**
	 * 添加时间
	 */
	private Date createTime;
	/**
	 * 服务详细模板id
	 */
	private int template;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApid() {
		return apid;
	}
	public void setApid(int apid) {
		this.apid = apid;
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
	public int getTemplate() {
		return template;
	}
	public void setTemplate(int template) {
		this.template = template;
	}
}

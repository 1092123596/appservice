package com.xdtech.platform.user.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 车系实体类
 * 
 * @author ls
 */
@Entity
@Table(name = "gc_vehicledept")
public class Cars {
	/**
	 * 车系ID
	 */
	private int deptId;
	/**
	 * 车系名称
	 */
	private String deptName;
	/**
	 * 品牌ID
	 */
	private int brandId;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * LOGO
	 */
	private String logo;
	/**
	 * 介绍
	 */
	private String intro;
	/**
	 * 创建人
	 */
	private String createby;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 删除标识
	 */
	private int deleted;
	
	private String keyWord;
	
	private String description;
	/**
	 * 维保车系首页标题
	 */
	private String title;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}

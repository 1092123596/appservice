package com.xdtech.platform.user.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 品牌实体类
 * 
 * @author ls
 */
@Entity
@Table(name = "gc_vehiclebrand")
public class Brand {
	/**
	 * 主键
	 */
	private int brandId;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 国别
	 */
	private String brandStyle;
	/**
	 * 首字母
	 */
	private String initial;
	/**
	 * LOGO
	 */
	private String logo;
	/**
	 * 介绍
	 */
	private String intro;
	/**
	 * 上级菜单
	 */
	private int prevMenu;
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

	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 关键字
	 */
	private String keyWord;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandStyle() {
		return brandStyle;
	}

	public void setBrandStyle(String brandStyle) {
		this.brandStyle = brandStyle;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
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

	public int getPrevMenu() {
		return prevMenu;
	}

	public void setPrevMenu(int prevMenu) {
		this.prevMenu = prevMenu;
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}

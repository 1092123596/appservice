package com.xdtech.platform.user.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "gc_usercarinfo")
public class UserCarInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4473125266527002473L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 品牌ID
	 */
	private String brandId;
	/**
	 * 车系
	 */
	private String deptId;
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 0:未选中，1:选中
	 */
	private int isSelected;
	
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(int isSelected) {
		this.isSelected = isSelected;
	}
}

package com.xdtech.platform.user.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 用户实体
 * @author shendelei
 */
@Entity
@Table(name = "gc_user")
public class User implements java.io.Serializable{
	
	private static final long serialVersionUID = 1954714267719091659L;

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 注册用户名
	 */
	private String username;

	/***
	 * 真实姓名
	 */
	private String trueName;

	/**
	 * 联系方式
	 */
	private String contactNo;

	/**
	 * 密码(加密)
	 */
	private String password;
	/**
	 * 密码(未加密)
	 */
	private String passwordShow;
	/**
	 * 是否有车
	 */
	private Integer havecar;

	/**
	 * 品牌id
	 */
	private String brandId;

	/**
	 * 品牌名称
	 */
	private String brandName;

	/**
	 * 车系id
	 */
	private String deptId;

	/**
	 * 车系名称
	 */
	private String deptName;

	/**
	 * 车型id
	 */
	private String styleId;

	/**
	 * 车型名称
	 */
	private String styleName;

	/**
	 * 省编号
	 */
	private String provinceNo;

	/**
	 * 省名称
	 */
	private String provinceName;

	/**
	 * 城市编号
	 */
	private String cityNo;

	/**
	 * 城市名称
	 */
	private String cityName;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 状态
	 */
	private Integer state;

	/**
	 * 星级
	 */
	private Integer levels;

	/**
	 * 婚姻状况
	 */
	private Integer civilState;

	/**
	 * 每月实际收入：（税后）
	 */
	private BigDecimal trueIncome;

	/**
	 * 职位
	 */
	private Integer position;
	/**
	 * 工作年限
	 */
	private Integer workTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后登录时间
	 */
	private Date lastTime;

	/**
	 * 删除标识
	 */
	private Integer deleted;
	
	/**
	 * 开心网id
	 */
	private String kaiXinUid;

	/**
	 * QQid
	 */
	private String qqUid;
	
	/**
	 * 新浪微博uid
	 * @return
	 */
	private String sinaUid;
	
	/**
	 * 网易微博Uid
	 */
	private String net163Uid;

	/**
	 * 电话
	 */
	private String telePhone;
	/**
	 * 用户头像
	 */
	private String userimg;
	/**
	 * 购车时间
	 */
	private Date styleTime;
	/**
	 * 车牌号
	 */
	private String styleBrand;
	/**
	 * 用户类型
	 */
	private Integer type;
	/**
	 * 微信openid
	 */
	private String wxOpenId;
	/**
	 * 用户可用积分
	 */
	private int integral;
	/**
	 * qq号
	 */
	private String qqNumber;
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(name = "userId", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "username", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "brandName", unique = false, nullable = true, insertable = true, updatable = true, length = 240)
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(name = "deptName", unique = false, nullable = true, insertable = true, updatable = true, length = 240)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

	@Column(name = "styleName", unique = false, nullable = true, insertable = true, updatable = true, length = 240)
	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	@Column(name = "provinceNo", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}

	@Column(name = "provinceName", unique = false, nullable = true, insertable = true, updatable = true, length = 120)
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "cityNo", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	@Column(name = "cityName", unique = false, nullable = true, insertable = true, updatable = true, length = 120)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "trueName", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	@Column(name = "contactNo", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getContactNo() {
		return contactNo;
	}

	public Integer getHavecar() {
		return havecar;
	}

	public void setHavecar(Integer havecar) {
		this.havecar = havecar;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer getCivilState() {
		return civilState;
	}

	public void setCivilState(Integer civilState) {
		this.civilState = civilState;
	}

	public BigDecimal getTrueIncome() {
		return trueIncome;
	}

	public void setTrueIncome(BigDecimal trueIncome) {
		this.trueIncome = trueIncome;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getKaiXinUid() {
		return kaiXinUid;
	}

	public void setKaiXinUid(String kaiXinUid) {
		this.kaiXinUid = kaiXinUid;
	}
	public String getQqUid() {
		return qqUid;
	}

	public void setQqUid(String qqUid) {
		this.qqUid = qqUid;
	}

	public String getSinaUid() {
		return sinaUid;
	}

	public void setSinaUid(String sinaUid) {
		this.sinaUid = sinaUid;
	}
	
	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getNet163Uid() {
		return net163Uid;
	}

	public void setNet163Uid(String net163Uid) {
		this.net163Uid = net163Uid;
	}

	public String getUserimg() {
		return userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public String getPasswordShow() {
		return passwordShow;
	}

	public void setPasswordShow(String passwordShow) {
		this.passwordShow = passwordShow;
	}

	public Date getStyleTime() {
		return styleTime;
	}

	public void setStyleTime(Date styleTime) {
		this.styleTime = styleTime;
	}

	public String getStyleBrand() {
		return styleBrand;
	}

	public void setStyleBrand(String styleBrand) {
		this.styleBrand = styleBrand;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

}

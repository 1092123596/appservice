package com.xdtech.platform.glosscoating.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 封釉镀膜镀晶实体
 * 
 * @author szz 网购价：小型车wprice+hwprice, 中型车zwprice+zhwprice, SUV swprice+shwprice,
 *         商务/面包 bwprice+bhwprice,
 * 
 */
@Entity
@Table(name = "mt_glosscoating")
public class MtGlossCoating {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 4S店ID
	 */
	private int shopid;
	/**
	 * 服务名称ID
	 */
	private int projectid;
	/**
	 * 服务名称
	 */
	private String projectname;
	/**
	 * 品牌ID
	 */
	private int brandid;
	/**
	 * 服务名称
	 */
	private String brandname;
	/**
	 * 型号id
	 */
	private int modelid;
	/**
	 * 型号名称
	 */
	private String modelname;
	/**
	 * 功能
	 */
	private String ufunction;
	/**
	 * 启用禁用
	 */
	private int state;
	/**
	 * 删除
	 */
	private int deleted;
	/**
	 * 一句话介绍
	 */
	private String explicit;
	/**
	 * 配件店面价格
	 */
	private BigDecimal dprice;
	/**
	 * 配件网购价格
	 */
	private BigDecimal wprice;
	/**
	 * 工时店面价格
	 */
	private BigDecimal hprice;
	/**
	 * 工时网购价格
	 */
	private BigDecimal hwprice;
	/**
	 * 购买类型
	 */
	private Integer servicetype;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 中型车
	 */
	private BigDecimal zdprice;
	private BigDecimal zwprice;
	private BigDecimal zhprice;
	private BigDecimal zhwprice;
	/**
	 * suv
	 */
	private BigDecimal sdprice;
	private BigDecimal swprice;
	private BigDecimal shprice;
	private BigDecimal shwprice;
	/**
	 * 商务面包
	 */
	private BigDecimal bdprice;
	private BigDecimal bwprice;
	private BigDecimal bhprice;
	private BigDecimal bhwprice;
	/**
	 * 物理状态
	 */
	private String physicsstate;
	/**
	 * 产品上架、下架,0为上架、1为下架,默认为0
	 */
	private int carriage;
	/**
	 * 图片
	 */
	private String img;
	/**
	 * 表投字段
	 */
	private String title;
	
	/**
	 * 消费积分比例
	 */
	private BigDecimal percentage = new BigDecimal(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public int getBrandid() {
		return brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public int getModelid() {
		return modelid;
	}

	public void setModelid(int modelid) {
		this.modelid = modelid;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public String getUfunction() {
		return ufunction;
	}

	public void setUfunction(String ufunction) {
		this.ufunction = ufunction;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getExplicit() {
		return explicit;
	}

	public void setExplicit(String explicit) {
		this.explicit = explicit;
	}

	public BigDecimal getDprice() {
		return dprice;
	}

	public void setDprice(BigDecimal dprice) {
		this.dprice = dprice;
	}

	public BigDecimal getWprice() {
		return wprice;
	}

	public void setWprice(BigDecimal wprice) {
		this.wprice = wprice;
	}

	public BigDecimal getHprice() {
		return hprice;
	}

	public void setHprice(BigDecimal hprice) {
		this.hprice = hprice;
	}

	public BigDecimal getHwprice() {
		return hwprice;
	}

	public void setHwprice(BigDecimal hwprice) {
		this.hwprice = hwprice;
	}

	public Integer getServicetype() {
		return servicetype;
	}

	public void setServicetype(Integer servicetype) {
		this.servicetype = servicetype;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getZdprice() {
		return zdprice;
	}

	public void setZdprice(BigDecimal zdprice) {
		this.zdprice = zdprice;
	}

	public BigDecimal getZwprice() {
		return zwprice;
	}

	public void setZwprice(BigDecimal zwprice) {
		this.zwprice = zwprice;
	}

	public BigDecimal getZhprice() {
		return zhprice;
	}

	public void setZhprice(BigDecimal zhprice) {
		this.zhprice = zhprice;
	}

	public BigDecimal getZhwprice() {
		return zhwprice;
	}

	public void setZhwprice(BigDecimal zhwprice) {
		this.zhwprice = zhwprice;
	}

	public BigDecimal getSdprice() {
		return sdprice;
	}

	public void setSdprice(BigDecimal sdprice) {
		this.sdprice = sdprice;
	}

	public BigDecimal getSwprice() {
		return swprice;
	}

	public void setSwprice(BigDecimal swprice) {
		this.swprice = swprice;
	}

	public BigDecimal getShprice() {
		return shprice;
	}

	public void setShprice(BigDecimal shprice) {
		this.shprice = shprice;
	}

	public BigDecimal getShwprice() {
		return shwprice;
	}

	public void setShwprice(BigDecimal shwprice) {
		this.shwprice = shwprice;
	}

	public BigDecimal getBdprice() {
		return bdprice;
	}

	public void setBdprice(BigDecimal bdprice) {
		this.bdprice = bdprice;
	}

	public BigDecimal getBwprice() {
		return bwprice;
	}

	public void setBwprice(BigDecimal bwprice) {
		this.bwprice = bwprice;
	}

	public BigDecimal getBhprice() {
		return bhprice;
	}

	public void setBhprice(BigDecimal bhprice) {
		this.bhprice = bhprice;
	}

	public BigDecimal getBhwprice() {
		return bhwprice;
	}

	public void setBhwprice(BigDecimal bhwprice) {
		this.bhwprice = bhwprice;
	}

	public String getPhysicsstate() {
		return physicsstate;
	}

	public void setPhysicsstate(String physicsstate) {
		this.physicsstate = physicsstate;
	}

	public int getCarriage() {
		return carriage;
	}

	public void setCarriage(int carriage) {
		this.carriage = carriage;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
}

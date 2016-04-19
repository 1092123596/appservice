package com.xdtech.platform.waxing.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 打蜡实体
 * 
 * @author ssb	网购价：小型车wprice+hwprice,  中型车zwprice+zhwprice, SUV swprice+shwprice, 商务/面包 bwprice+bhwprice,
 */
@Entity
@Table(name = "mt_waxing")
public class MtWaxing {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 经销商id
	 */
	private int shopId;
	/**
	 * 项目id
	 */
	private int projectId;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 品牌id
	 */
	private int brandId;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 型号id
	 */
	private int modelid;
	/**
	 * 型号名称
	 */
	private String modelname;
	/**
	 * 物理状态名称
	 */
	private String physicalstateName;
	/**
	 * 功能名称
	 */
	private String functionName;
	/**
	 * 小型车配件店面价
	 */
	private BigDecimal dprice;
	/**
	 * 小型车配件网购价
	 */
	private BigDecimal wprice;
	/**
	 * 小型车工时店面价
	 */
	private BigDecimal hprice;
	/**
	 * 小型车工时网购价
	 */
	private BigDecimal hwprice;
	/**
	 * suv配件店面价
	 */
	private BigDecimal sdprice;
	/**
	 * suv配件网购价
	 */
	private BigDecimal swprice;
	/**
	 * suv工时店面价
	 */
	private BigDecimal shprice;
	/**
	 * suv工时网购价
	 */
	private BigDecimal shwprice;
	/**
	 * 中型车配件店面价
	 */
	private BigDecimal zdprice;
	/**
	 * 中型车配件网购价
	 */
	private BigDecimal zwprice;
	/**
	 * 中型车工时店面价
	 */
	private BigDecimal zhprice;
	/**
	 * 中型车工时网购价
	 */
	private BigDecimal zhwprice;
	/**
	 * 商务车配件店面价
	 */
	private BigDecimal bdprice;
	/**
	 * 商务车配件网购价
	 */
	private BigDecimal bwprice;
	/**
	 * 商务车工时店面价
	 */
	private BigDecimal bhprice;
	/**
	 * 商务车工时网购价
	 */
	private BigDecimal bhwprice  = new BigDecimal(0);
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 启用禁用标志
	 */
	private int state;
	/**
	 * 删除标志
	 */
	private int deleted;
	/**
	 * 一句话广告
	 */
	private String explicit;
	/**
	 * 产品加工时或只有工时标志
	 */
	private int servicetype;
	/**
	 * 活动标识   0普通    1活动
	 */
	private int flag;
	/**
	 *  产品上架、下架,0为上架、1为下架,默认为0
	 */
	private int carriage;
	/**
	 * 产品图片
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
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
	public String getPhysicalstateName() {
		return physicalstateName;
	}
	public void setPhysicalstateName(String physicalstateName) {
		this.physicalstateName = physicalstateName;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
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
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	public int getServicetype() {
		return servicetype;
	}
	public void setServicetype(int servicetype) {
		this.servicetype = servicetype;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
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

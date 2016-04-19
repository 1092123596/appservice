package com.xdtech.platform.glosscoating.action;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.web.action.BaseAction;
import com.xdtech.platform.glosscoating.service.GlossService;
import com.xdtech.platform.minormt.bean.ProductProcedures;
import com.xdtech.platform.minormt.service.MinormtService;

/**
 * @Description:app服务器端镀晶，镀膜，封釉action
 * @author hyh
 * @date 2015-3-13 上午10:07:41
 */
public class GlossAction extends BaseAction {
	@Resource
	private GlossService glossService;
	@Resource
	private MinormtService minormtService;
	private int start;
	private int row;
	private String shopName;
	/**
	 * shopid
	 */
	private int shopid;
	/**
	 * 服务id
	 */
	private int pjid;
	/**
	 * 产品id
	 */
	private int productId;
	/**
	 * 产品详细id
	 */
	private int id;
	/**
	 * 商家经纬度
	 */
	private String lng;
	private String lat;

	public void glossShoplist() {
		List<HashMap> glossShopList = glossService.findGlossShopList(start, row, pjid, shopName,lng,lat);
		int count = glossService.findGlossShopCount(shopName, pjid);
		writeValue(glossShopList, ConstanData.SUCCESSCODE, null, count);
	}

	/**
	 * @Description:查询商家首页 镀晶，镀膜，封釉产品列表
	 * @author
	 * @date 2015-3-30 上午11:00:09
	 */
	public void listShopGloss() {
		List<HashMap<String, Object>> list = glossService.findGlossByShopId(start, row, shopid, pjid);
		int count = glossService.findGlossCount(shopid, pjid);
		writeValue(list, ConstanData.SUCCESSCODE, null, count);
	}

	/**
	 *产品详细
	 */
	public void productDetail() {
		List<ProductProcedures> list = glossService.findProdDetail(id);
		int reviewNum = minormtService.findInfoReviewNum(pjid, productId);
		String rating = minormtService.getRating(shopid, pjid, productId);
		writeValue(list, ConstanData.SUCCESSCODE, null, 0, reviewNum, rating);
	}
	/**
	 * @Description:地图显示洗车所有商家 
	 * @author hyh
	 * @date 2015-4-16 下午03:32:30
	 */
	public void findAllShop(){
		List<HashMap<String,Object>> list=glossService.findAllShop();	
		writeValue(list,ConstanData.SUCCESSCODE, null,0);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getPjid() {
		return pjid;
	}

	public void setPjid(int pjid) {
		this.pjid = pjid;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}

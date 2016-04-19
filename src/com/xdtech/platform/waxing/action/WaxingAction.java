package com.xdtech.platform.waxing.action;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.reflect.TypeToken;
import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.web.action.BaseAction;
import com.xdtech.platform.core.web.entity.ResultData;
import com.xdtech.platform.waxing.bean.MtWaxing;
import com.xdtech.platform.waxing.service.WaxingService;

/**
 * @Description:app服务器端打蜡action
 * @author hyh
 * @date 2015-3-13 上午10:07:41
 */
public class WaxingAction extends BaseAction {
	@Resource
	private WaxingService waxingService;

	private int start;

	private int row;
	private int pjid;
	private int shopid;
	private String shopName;
	/**
	 * 商家经纬度
	 */
	private String lng;
	private String lat;

	public void waxingShoplist() {
		pjid = 113;
		List<HashMap> waxingShopList = waxingService.findWaxingShopList(start, row, pjid, shopName,lng,lat);
		int count = waxingService.findWaxingShopCount(shopName, pjid);
		writeValue(waxingShopList, ConstanData.SUCCESSCODE, null, count, 0, null, new TypeToken<ResultData<MtWaxing>>() {
		});
	}

	/**
	 * @Description:查询商家首页 打蜡产品列表
	 * @author
	 * @date 2015-3-30 上午11:00:09
	 */
	public void listShopWaxing() {
		pjid = 113;
		List<HashMap> list = waxingService.findWaxingByShopId(start, row, shopid, pjid);
		int count = waxingService.findGlossCount(shopid, pjid);
		writeValue(list, ConstanData.SUCCESSCODE, null, count, 0, null, new TypeToken<ResultData<MtWaxing>>() {
		});
	}
	/**
	 * @Description:地图显示洗车所有商家 
	 * @author hyh
	 * @date 2015-4-16 下午03:32:30
	 */
	public void findAllShop(){
		List<HashMap<String,Object>> list=waxingService.findAllShop();	
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

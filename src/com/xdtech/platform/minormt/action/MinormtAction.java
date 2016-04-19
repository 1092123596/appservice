package com.xdtech.platform.minormt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.web.action.BaseAction;
import com.xdtech.platform.minormt.service.MinormtService;

public class MinormtAction extends BaseAction{
	
	@Resource
	private MinormtService minormtService;
	
	private int start;
	
	private int row;
	
	private String shopName;
	
	private int shopId;
	
	private int engproductid;
	
	private int filproductid;
	
	private int productId;
	
	private String reviewflag;
	
	private String lng;
	
	private String lat;
	
	private int deptId;
	
	private int projectId;
	

	/**
	 * 获得小保养商户列表
	 * @author shendelei
	 * @date 2015-3-31 下午2:26:31
	 */
	public void minormtShopList(){
		List<HashMap> minormtShopList = minormtService.findMinormtShopList(start, row, shopName, lng, lat, deptId);
		int count  = minormtService.findMinormtShopCount(shopName, deptId);
		writeValue(minormtShopList, ConstanData.SUCCESSCODE, null, count);
	}
	
	/**
	 * 获得小保养商户列表前三条
	 * @author shendelei
	 * @date 2015-3-31 下午2:26:31
	 */
	public void minormtMainShopList(){
		List<HashMap> minormtShopList = minormtService.findMinormtShopList(shopName, lng, lat);
		writeValue(minormtShopList, ConstanData.SUCCESSCODE, null, 0);
	}
	
	/**
	 * 获得小保养商品列表
	 * @author shendelei
	 * @date 2015-3-31 下午2:26:47
	 */
	public void minormtList(){
		List<HashMap> minormtList = minormtService.findMinormtList(shopId,start,row);
		int count  = minormtService.findMinormtCount(shopId);
		writeValue(minormtList, ConstanData.SUCCESSCODE, null, count);
	}
	
	/**
	 * 获得小保养商品详细内容列表
	 * @author shendelei
	 * @date 2015-3-31 下午2:27:03
	 */
	public void minormtInfo(){
		List<HashMap> engineList = minormtService.findEngineInfo(engproductid);
		List<HashMap> filterList = minormtService.findFilterInfo(filproductid);
		List<List<HashMap>> productProceduresList = new ArrayList<List<HashMap>>();
		productProceduresList.add(engineList);
		productProceduresList.add(filterList);
		int reviewNum = minormtService.findInfoReviewNum(11814, productId);
		String rating = minormtService.getRating(shopId,11814,productId);
		writeValue(productProceduresList, ConstanData.SUCCESSCODE, null, 0, reviewNum, rating);
	}
	
	/**
	 * 获得评论列表
	 * @author shendelei
	 * @date 2015-3-31 下午2:27:27
	 */
	public void minormtReviewList(){
		List<HashMap> reviewList = minormtService.findReviewList(projectId, productId, start, row, reviewflag);
		int count  = minormtService.findInfoReviewNum(projectId, productId);
		writeValue(reviewList, ConstanData.SUCCESSCODE, null, count);
	}
	
	
	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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
	
	public int getEngproductid() {
		return engproductid;
	}

	public void setEngproductid(int engproductid) {
		this.engproductid = engproductid;
	}
	
	public int getFilproductid() {
		return filproductid;
	}

	public void setFilproductid(int filproductid) {
		this.filproductid = filproductid;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getReviewflag() {
		return reviewflag;
	}

	public void setReviewflag(String reviewflag) {
		this.reviewflag = reviewflag;
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

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}

package com.xdtech.platform.personalcenter.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.util.string.ConstantString;
import com.xdtech.platform.core.web.action.BaseAction;
import com.xdtech.platform.personalcenter.service.MtorderService;
import com.xdtech.platform.user.bean.User;

public class MtorderAction extends BaseAction{

	@Resource
	private MtorderService mtorderservice;
	private int orderState;
	private int start;
	private int brandId;
	private String initial;
	
	private int row;
	/**
	 * 我的消费码
	 * */
	public void orderList(){
		List<HashMap> orderlist = mtorderservice.findMtorderList(start,row,orderState);
		int count = mtorderservice.findMtorderCount(orderState);
		writeValue(orderlist, ConstanData.SUCCESSCODE, null, count);
	}
	/**
	 * 我的积分
	 * */
	public void jifenList(){
		List<HashMap> orderlist = mtorderservice.findJifenList(start,row,orderState);
		for(int i=0;i<orderlist.size();i++){
			User userinfo = mtorderservice.getUserByuserId(orderlist.get(i).get("userId").toString());
			if(null!=userinfo){
				orderlist.get(i).put("INTEGRAL", userinfo.getIntegral());
			}
		}
		int count = mtorderservice.findJifenCount(orderState);
		writeValue(orderlist, ConstanData.SUCCESSCODE, null, count);
	}
	
	/**
	 * 根据品牌首字母获取品牌列表
	 */
	public void getInitialBrand() {
		User user = (User) request.getSession().getAttribute(ConstantString.GadUser);
		if(null != user){
			List<HashMap> list = mtorderservice.getVehicleBrand(initial);
			int count = mtorderservice.getVehicleBrandCount(initial);
//			writeValue(list, ConstanData.SUCCESSCODE, null, count);
			writeValue(list, ConstanData.SUCCESSCODE, "", count, 0, request.getSession().getId());
		}else{
			writeValue(null, ConstanData.FAILURECODE, "getInitialBrand", 1, 0, request.getSession().getId());
		}
		
	}
	/**
	 * 根据车型品牌id得到车系
	 * 
	 * @return
	 */
	public void getDept() {
		User user = (User) request.getSession().getAttribute(ConstantString.GadUser);
		if(null != user){
			List<HashMap> list = mtorderservice.getVehicleDept(brandId);
			int count = mtorderservice.getVehicleDeptCount(brandId);
			writeValue(list, ConstanData.SUCCESSCODE, "", count, 0, request.getSession().getId());
		}else{
			writeValue(null, ConstanData.FAILURECODE, "getDept", 1, 0, request.getSession().getId());
		}
		
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
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
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
}

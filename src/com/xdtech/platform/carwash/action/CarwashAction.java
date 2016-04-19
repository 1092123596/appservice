package com.xdtech.platform.carwash.action;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import com.xdtech.platform.carwash.bean.ProjectProcedures;
import com.xdtech.platform.carwash.service.CarwashService;
import com.xdtech.platform.core.util.dao.DCriteriaPageSupport;
import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.web.action.BaseAction;
import com.xdtech.platform.minormt.service.MinormtService;
/**
 * @Description:app服务器端洗车action 
 * @author hyh
 * @date 2015-3-13 上午10:07:41
 */
public class CarwashAction extends BaseAction {
	@Resource
	private CarwashService carwashService;
	@Resource
	private MinormtService minormtService;
	private int start;
	private int rows;
	/**
	 * 商家名称
	 */
	private String shopName;
	/**
	 * 产品id
	 */
	private int id;
	/**
	 * 商家id
	 */
	private int shopId;
	/**
	 * 服务id
	 */
	private int projectId;
	/**
	 * 商家经纬度
	 */
	private String lng;
	private String lat;
	/**
	 * @Description:洗车商家列表
	 * @author hyh
	 * @date 2015-3-18 下午03:56:00
	 */
	public void list(){
		DCriteriaPageSupport<HashMap<String, Object>> list=carwashService.list(start, rows,shopName,lng,lat);
		Long count=list.getTotalCount();
		writeValue(list,  ConstanData.SUCCESSCODE, null, Integer.parseInt(String.valueOf(count)));
	}
	/**
	 * @Description:查询商家首页洗车服务 
	 * @author hyh
	 * @date 2015-3-20 上午11:00:09
	 */
	public void listShopCarwash(){
		List<HashMap<String,Object>> list=carwashService.findCarwashByShopId(start, rows,shopId);
		int count=carwashService.findCarwashCount(shopId);
		writeValue(list,ConstanData.SUCCESSCODE, null,count);
	}
	/**
	 * @Description:洗车详细页服务详细 
	 * @author hyh
	 * @date 2015-3-24 下午01:32:31
	 */
	public void listProjInfo(){
		List<ProjectProcedures> list = carwashService.findProjectDetail(shopId, projectId);	
		int reviewNum = minormtService.findInfoReviewNum(projectId, id);
		String rating = minormtService.getRating(shopId,projectId,id);
		writeValue(list, ConstanData.SUCCESSCODE, null,  0, reviewNum, rating);	
	}
	/**
	 * @Description:地图显示洗车所有商家 
	 * @author hyh
	 * @date 2015-4-16 下午03:32:30
	 */
	public void findAllShop(){
		List<HashMap<String,Object>> list=carwashService.findAllShop();	
		writeValue(list,ConstanData.SUCCESSCODE, null,0);
	}
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
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

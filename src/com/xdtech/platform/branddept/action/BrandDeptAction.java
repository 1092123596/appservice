package com.xdtech.platform.branddept.action;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.xdtech.platform.branddept.service.BrandDeptService;
import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.web.action.BaseAction;

public class BrandDeptAction extends BaseAction{

	@Resource
	private BrandDeptService brandDeptService;
	
	private int brandId;
	
	/**
	 * 品牌列表
	 * @author shendelei
	 * @date 2015-4-2 上午9:34:30
	 */
	public void brandList(){
		List<HashMap> brandList =  brandDeptService.getBrand();
		writeValue(brandList, ConstanData.SUCCESSCODE, null, 0);
	}
	
	/**
	 * 车系列表
	 * @author shendelei
	 * @date 2015-4-2 上午9:34:44
	 */
	public void deptList(){
		List<HashMap> deptList =  brandDeptService.getDept(brandId);
		writeValue(deptList, ConstanData.SUCCESSCODE, null, 0);
	}
	
	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
}

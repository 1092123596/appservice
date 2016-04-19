package com.xdtech.platform.branddept.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xdtech.platform.core.service.BaseService;

@Service
public class BrandDeptService extends BaseService{
	
	/**
	 * 查询汽车品牌
	 * @author shendelei
	 * @date 2015-4-2 上午9:28:48
	 * @return
	 */
	public List<HashMap> getBrand() {
		StringBuilder sql = new StringBuilder();
		sql.append("select v.brandName as brandName,v.brandId as brandId,v.initial as initial,v.prevMenu as prevMenu,v.logo as logo from mt_zz z,gc_vehiclebrand v where v.deleted=0 and z.deleted=0 and z.brandid=v.brandid");
		sql.append(" group by z.brandname order by v.initial asc");
		return dao.getAllBySQL(sql.toString());
	}
	
	/**
	 * 查询车系
	 * @author shendelei
	 * @date 2015-4-2 上午9:28:42
	 * @param brandid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap> getDept(int brandId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select brandName as brandName,brandId as brandId, deptName as deptName,deptId as deptId from gc_vehicledept where deleted=0 and brandid=" + brandId + " group by deptName order by deptName asc");
		return this.getDao().getAllBySQL(sql.toString());
	}
}

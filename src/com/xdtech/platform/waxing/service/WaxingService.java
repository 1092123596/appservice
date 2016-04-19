package com.xdtech.platform.waxing.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.xdtech.platform.core.service.BaseService;
import com.xdtech.platform.waxing.bean.MtWaxing;

@Service
public class WaxingService extends BaseService {
	/**
	 * 查询打蜡所有商户
	 * 
	 * @author mt_glosscoating
	 * @date
	 * @return
	 */
	public List<HashMap> findWaxingShopList(int start, int row, int pjid, String shopName, String lng1, String lat1) {
		StringBuffer sql = new StringBuffer();
		if (StringUtils.isEmpty(lng1)) {
			lng1 = "0";
		}
		if (StringUtils.isEmpty(lat1)) {
			lat1 = "0";
		}
		sql.append("select m.wprice as webprice ,m.dprice as dmprice,m.title,m.projectname,s.shopName,s.shopId,s.addr,s.entertainLogo,s.logo,s.description as description,s.lng,s.lat,s.contactNo,s.levels,concat(s.mtime,'——',s.atime) as businessTime,s.professionalBuss,(ACOS(SIN(" + lat1 + " / 180 * PI()) * SIN(s.lat / 180 * PI()) + COS(" + lat1 + " / 180 * PI()) * COS(s.lat / 180 * PI()) * COS((" + lng1 + " - s.lng) / 180 * PI())) * 180 / PI() * 60 * 1.1515 * 1.609344) as distance from (select * from mt_waxing where projectid=" + pjid + " and deleted=0 order by hwprice) as m,gc_shop as s " + "where m.shopId=s.shopId and m.servicetype=0 and m.state=0 and m.carriage=0 and m.deleted=0 and s.is4sShop=1 and s.state=1 and s.deleted =0 ");
		if (!StringUtils.isEmpty(shopName)) {
			try {
				sql.append("and s.shopName like '%" + URLDecoder.decode(shopName, "UTF-8") + "%' ");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		sql.append("group by m.shopId");
		
		if(!"0".equalsIgnoreCase(lng1) && !"0".equalsIgnoreCase(lat1)){
			sql.append(" order by distance");
		}
		sql.append(" limit "+start+","+row);
		List<HashMap> minormtShopList = this.getDao().getAllPreBySQL(sql.toString(), null);
		return minormtShopList;
	}

	/**
	 * 查询打蜡商户的总数
	 * 
	 * @author shendelei
	 * @date 2015-3-11 下午4:43:36
	 * @param shopName
	 * @return
	 */
	public int findWaxingShopCount(String shopName, int pjid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(distinct(m.shopId)) as count from mt_waxing as m,gc_shop as s " + "where m.shopId=s.shopId and m.servicetype=0 and m.state=0 and m.carriage=0 and  m.projectid=" + pjid + " and m.deleted=0 and s.is4sShop=1 and s.state=1 and s.deleted =0 ");
		if (!StringUtils.isEmpty(shopName)) {
			try {
				sql.append("and s.shopName like '%" + URLDecoder.decode(shopName, "UTF-8") + "%' ");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<HashMap> countList = this.getDao().getAllPreBySQL(sql.toString(), null);
		int count = Integer.parseInt(countList.get(0).get("count").toString());
		return count;
	}

	/**
	 * @Description:根据shopId查询打蜡信息
	 * @author
	 * @date 2015-3-30 上午10:46:33
	 * @param shopid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap> findWaxingByShopId(int start, int rows, int shopid, int pjid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from mt_waxing m where m.servicetype=0 and m.state=0 and m.carriage=0 and  m.projectid=" + pjid + " and m.deleted=0 and m.shopid=" + shopid + " limit " + start + "," + rows);
		System.out.println(sql.toString());
		return this.getDao().getAllPreBySQL(sql.toString(), null);
	}

	/**
	 * @Description:统计商家打蜡务数量
	 * @author
	 * @date 2015-3-30 上午10:57:54
	 * @param shopId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findGlossCount(int shopid, int pjid) {
		StringBuffer sql = new StringBuffer();
		int count = 0;
		sql.append("select count(m.id) as count from mt_waxing m where m.servicetype=0 and m.state=0 and m.carriage=0 and  m.projectid=" + pjid + " and m.deleted=0 and m.shopid=" + shopid + "");
		List<HashMap<String, Object>> list = this.getDao().getAllPreBySQL(sql.toString(), null);
		if (list != null && list.size() > 0) {
			count = Integer.parseInt(list.get(0).get("count").toString());
		}
		return count;
	}

	/**
	 * @Description: 根据主键查询打蜡
	 * @param id
	 * @return
	 */
	public MtWaxing findWaxingById(int id) {
		return (MtWaxing) this.getDao().findIObjectByPK(MtWaxing.class, id);
	}
	/**
	 * @Description: 查询打蜡所有商家
	 * @author hyh
	 * @date 2015-4-16 下午03:29:27
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> findAllShop() {
		StringBuffer sql = new StringBuffer();
		sql.append("select s.shopName as shopName,s.addr as addr,s.contactNo as contactNo,s.lng,s.lat from gc_shop s,mt_waxing m where m.shopId=s.shopId and m.servicetype=0 and m.state=0 and m.carriage=0 and m.deleted=0 and s.is4sShop=1 and s.state=1 and s.deleted =0 group by m.shopId");
		return this.getDao().getAllPreBySQL(sql.toString(), null);
	}
}

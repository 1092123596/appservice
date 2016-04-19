package com.xdtech.platform.carwash.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.xdtech.platform.carwash.bean.MtCarwashcard;
import com.xdtech.platform.carwash.bean.ProjectProcedures;
import com.xdtech.platform.core.service.BaseService;
import com.xdtech.platform.core.util.dao.DCriteriaPageSupport;

/**
 * @Description:洗车service
 * @author hyh
 * @date 2015-3-13 上午10:08:37
 */
@Service
public class CarwashService extends BaseService {
	/**
	 * @Description:洗车商家列表
	 * @author hyh
	 * @date 2015-3-13 上午11:00:18
	 * @param start
	 * @param rows
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DCriteriaPageSupport<HashMap<String, Object>> list(int start, int rows, String shopName, String lng1, String lat1) {
		DCriteriaPageSupport<HashMap<String, Object>> list = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlCount = new StringBuffer();
		if (StringUtils.isEmpty(lng1)) {
			lng1 = "0";
		}
		if (StringUtils.isEmpty(lat1)) {
			lat1 = "0";
		}
		sql.append("select m.xhprice as dmprice,m.xhwprice as webprice,s.shopId as shopId,s.shopName as shopName,s.addr as addr,s.description as description,s.contactNo as contactNo,s.levels as levels," + "s.professionalBuss as professionalBuss,s.logo as logo,s.entertainLogo as entertainLogo,concat(s.mtime,'——',s.atime) as businessTime," + "(ACOS(SIN(" + lat1 + " / 180 * PI()) * SIN(s.lat / 180 * PI()) + COS(" + lat1 + " / 180 * PI()) * COS(s.lat / 180 * PI()) * COS((" + lng1 + " - s.lng) / 180 * PI())) * 180 / PI() * 60 * 1.1515 * 1.609344) as distance,s.lng,s.lat from (select * from mt_carwashcard order by xhwprice) as m,gc_shop as s " + "where m.shopId=s.shopId and m.servicetype=1 and m.state=0 and m.carriage=0 and m.deleted=0 and s.is4sShop=1 and s.state=1 and s.deleted =0 ");
		if (StringUtils.isNotEmpty(shopName)) {
			try {
				sql.append(" and s.shopName like '%" + URLDecoder.decode(shopName, "UTF-8") + "%' ");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		sql.append("group by m.shopId");
		if (!"0".equalsIgnoreCase(lng1) && !"0".equalsIgnoreCase(lat1)) {
			sql.append(" order by distance");
		}
		sql.append(" limit " + start + "," + rows);

		sqlCount.append("select count(distinct(m.shopId)) as count from mt_carwashcard as m,gc_shop as s " + "where m.shopId=s.shopId and m.servicetype=1 and m.state=0 and m.carriage=0 and m.deleted=0 and s.is4sShop=1 and s.state=1 and s.deleted =0 ");
		if (StringUtils.isNotEmpty(shopName)) {
			try {
				sqlCount.append(" and s.shopName like '%" + URLDecoder.decode(shopName, "UTF-8") + "%' ");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		Long totalCount = Long.valueOf(((Map<String, Object>) this.getDao().getAllPreBySQL(sqlCount.toString(), null).get(0)).get("count") + "");
		list = new DCriteriaPageSupport<HashMap<String, Object>>(this.getDao().getAllPreBySQL(sql.toString(), null), totalCount);
		return list;
	}

	/**
	 * @Description:根据shopId查询洗车信息
	 * @author hyh
	 * @date 2015-3-20 上午10:46:33
	 * @param shopId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> findCarwashByShopId(int start, int rows, int shopId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from mt_carwashcard m where m.servicetype=1 and m.state=0 and m.carriage=0 and m.deleted=0 and m.shopId=" + shopId + " limit " + start + "," + rows);
		return this.getDao().getAllPreBySQL(sql.toString(), null);
	}

	/**
	 * @Description:统计商家洗车服务数量
	 * @author hyh
	 * @date 2015-3-20 上午10:57:54
	 * @param shopId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCarwashCount(int shopId) {
		StringBuffer sql = new StringBuffer();
		int count = 0;
		sql.append("select count(m.id) as count from mt_carwashcard m where m.servicetype=1 and m.state=0 and m.carriage=0 and m.deleted=0 and m.shopId=" + shopId);
		List<HashMap<String, Object>> list = this.getDao().getAllPreBySQL(sql.toString(), null);
		if (list != null && list.size() > 0) {
			count = Integer.parseInt(list.get(0).get("count").toString());
		}
		return count;
	}

	/**
	 * @Description: 查询洗车服务详细
	 * @author hyh
	 * @date 2015-3-18 下午04:43:14
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProjectProcedures> findProjectDetail(int shopId, int projectId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select p from MtAgencyProject m,ProjectProcedures p where m.apid=p.apid and m.shopId=" + shopId + " and m.projectId=" + projectId + " and m.deleted=0 and p.deleted=0 and m.verify=0 order by p.orders");
		return this.getDao().findAllyHql(hql.toString(), null);
	}

	/**
	 * @Description: 根据主键查询洗车
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MtCarwashcard findCarwashById(int id) {
		return (MtCarwashcard) this.getDao().findIObjectByPK(MtCarwashcard.class, id);
	}

	/**
	 * @Description: 查询洗车所有商家
	 * @author hyh
	 * @date 2015-4-16 下午03:29:27
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> findAllShop() {
		StringBuffer sql = new StringBuffer();
		sql.append("select s.shopName as shopName,s.addr as addr,s.contactNo as contactNo,s.lng,s.lat from gc_shop s,mt_carwashcard m where m.shopId=s.shopId and m.servicetype=1 and m.state=0 and m.carriage=0 and m.deleted=0 and s.is4sShop=1 and s.state=1 and s.deleted =0 group by m.shopId");
		return this.getDao().getAllPreBySQL(sql.toString(), null);
	}
}

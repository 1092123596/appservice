package com.xdtech.platform.personalcenter.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xdtech.platform.core.service.BaseService;
import com.xdtech.platform.core.util.string.ValidateObject;
import com.xdtech.platform.user.bean.Brand;
import com.xdtech.platform.user.bean.Cars;
import com.xdtech.platform.user.bean.User;
import com.xdtech.platform.user.bean.UserCarInfo;

@Service
public class MtorderService extends BaseService{
	
	/**
	 * 查询我的消费码
	 * @author yuanqq
	 * @date 2015-3-23 下午9:57:24
	 * @param orderState  0 未支付 1已支付 2已消费
	 * @return
	 */
	public List<HashMap> findMtorderList(int start, int row,int orderState){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.orderNo,a.orderCreteTime,b.verification,b.orderState,b.payPrice from mt_orderForm b left join mt_order a on a.orderId = b.orderId where a.reqTxnId is null and b.orderState!=0 ");
		if(orderState==1){
			sql.append("and b.orderState=1");
		}else if(orderState==2){
			sql.append("and b.orderState=2");
		}
		sql.append(" limit "+start+","+row);
		List<HashMap> mtorderList = this.getDao().getAllPreBySQL(sql.toString(),null);
		return mtorderList;
	}
	/**
	 * 根据userid查找用户信息
	 * 
	 * @param RegisterUser RegisterUser
	 * @return
	 */
	public User getUserByuserId(String id) {
		User rs = (User) dao.findIObjectByPK(User.class, id);
		return rs;
	}
	/**
	 * 查询我的消费码数量
	 * @author yuanqq
	 * @date 2015-3-23 下午9:57:24
	 * @param orderState  0 未支付 1已支付 2已消费
	 * @return
	 */
	public int findMtorderCount(int orderState){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) as count from mt_orderForm b left join mt_order a on a.orderId = b.orderId where a.reqTxnId is null and b.orderState!=0 ");
		if(orderState==1){
			sql.append("and b.orderState=1");
		}else if(orderState==2){
			sql.append("and b.orderState=2");
		}
		List<HashMap> countList = this.getDao().getAllPreBySQL(sql.toString(),null);
		int count = Integer.parseInt(countList.get(0).get("count").toString());
		return count;
	}
	/**
	 * 查询我的积分信息
	 * @author yuanqq
	 * @date 2015-3-23 下午9:57:24
	 * @param orderState  0 未支付 1已支付 2已消费
	 * @return
	 */
	public List<HashMap> findJifenList(int start, int row,int orderState){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.userId as userId,a.orderNo as orderNo,a.orderCreteTime as orderCreteTime,b.verification as verification,b.orderState as orderState,b.integral as integral,b.consumeTime as consumeTime from mt_orderForm b left join mt_order a on a.orderId = b.orderId where a.reqTxnId is null and b.orderState!=0 ");
		if(orderState==1){
			sql.append("and b.orderState=1");
		}else if(orderState==2){
			sql.append("and b.orderState=2");
		}
		sql.append(" limit "+start+","+row);
		List<HashMap> mtorderList = this.getDao().getAllPreBySQL(sql.toString(),null);
		return mtorderList;
	}
	/**
	 * 查询积分条数
	 * @author yuanqq
	 * @date 2015-3-23 下午9:57:24
	 * @param orderState  0 未支付 1已支付 2已消费
	 * @return
	 */
	public int findJifenCount(int orderState){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) as count from mt_orderForm b left join mt_order a on a.orderId = b.orderId where a.reqTxnId is null and b.orderState!=0 ");
		if(orderState==1){
			sql.append("and b.orderState=1");
		}else if(orderState==2){
			sql.append("and b.orderState=2");
		}
		List<HashMap> countList = this.getDao().getAllPreBySQL(sql.toString(),null);
		int count = Integer.parseInt(countList.get(0).get("count").toString());
		return count;
	}
	
	/**
	 * 查询品牌(不包括有二级品牌的一级品牌)
	 * 
	 * @author yuanqq
	 * @date 2015-3-27上午09:55:11
	 * @param initial
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getVehicleBrandCount(String initial) {
		StringBuilder sql = new StringBuilder();
		// 查询所有品牌
		sql.append("select count(v.brandId) as count from gc_vehiclebrand v where v.deleted=0 and v.brandid!=1 and v.brandid not in (select distinct(prevMenu) from gc_vehiclebrand where prevMenu != 1 and deleted = 0)");
		if (initial != null && !"".equals(initial)) {
			sql.append(" and v.initial = '" + initial + "'");
		}
		sql.append(" group by v.brandname order by v.initial asc");
		List<HashMap> brandList = this.getDao().getAllPreBySQL(sql.toString(),null);
//		List<HashMap> brandList = dao.getAllBySQL(sql.toString());
//		sql.delete(0, sql.length());
//		if(!"".equals(brandList.size())&&0!=brandList.size()) {
//			return Integer.getInteger(brandList.get(0).get("COUNT").toString());
//		}
		return brandList.size();
	}
	/**
	 * 查询品牌(不包括有二级品牌的一级品牌)
	 * 
	 * @author yuanqq
	 * @date 2015-3-27上午09:55:11
	 * @param initial
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap> getVehicleBrand(String initial) {
		StringBuilder sql = new StringBuilder();
		// 查询所有品牌
		sql.append("select v.brandName as brandname,v.brandId as brandid,v.initial as initial,v.prevMenu as prevMenu,v.logo as logo from gc_vehiclebrand v where v.deleted=0 and v.brandid!=1 and v.brandid not in (select distinct(prevMenu) from gc_vehiclebrand where prevMenu != 1 and deleted = 0)");
		if (initial != null && !"".equals(initial)) {
			sql.append(" and v.initial = '" + initial + "'");
		}
		sql.append(" group by v.brandname order by v.initial asc");
		List<HashMap> brandList = this.getDao().getAllPreBySQL(sql.toString(),null);
//		sql.delete(0, sql.length());
		
		return brandList;
	}

	/**
	 * 查询车系
	 * 
	 * @author yuanqq
	 * @date 2015-3-27上午09:55:17
	 * @param brandid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap> getVehicleDept(int brandid) {

		StringBuilder sql = new StringBuilder();
		sql.append("select brandName as brandname,brandId as brandid, deptName as deptName,deptId as deptId from gc_vehicledept where deleted=0 and brandid=" + brandid + " group by deptName order by deptName asc");
		return this.getDao().getAllPreBySQL(sql.toString(),null);
	}
	/**
	 * 查询车系
	 * 
	 * @author yuanqq
	 * @date 2015-3-27上午09:55:17
	 * @param brandid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getVehicleDeptCount(int brandid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) as count from gc_vehicledept where deleted=0 and brandid=" + brandid + " group by deptName order by deptName asc");
//		brandList = dao.getAllBySQL(sql.toString());
		List<HashMap> brandList = this.getDao().getAllPreBySQL(sql.toString(),null);
//		int count = Integer.parseInt(brandList.get(0).get("count").toString());
//		return count;
		return brandList.size();
	}
	/**
	 * 查询车系
	 * 
	 * @author yuanqq
	 * @date 2015-3-27上午09:55:17
	 * @param brandid
	 * @return
	 */
	public List<UserCarInfo> getBrandByBrandUserid(String userid) {
		StringBuilder hql = new StringBuilder();
		List paramList = new ArrayList();
		hql.append("FROM UserCarInfo where userid='"+userid+"' ");
		return this.getDao().findAllyHql(hql.toString(), paramList.toArray());
	}
	/**
	 * 查询车系
	 * 
	 * @author yuanqq
	 * @date 2015-3-27上午09:55:17
	 * @param brandid
	 * @return
	 */
	public UserCarInfo getBrandByBrandId(String brandId,String deptId,String userid) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserCarInfo.class);
		if (ValidateObject.hasValue(brandId)) {
			dc.add(Restrictions.eq("brandId", brandId.trim()));
		}
		if (ValidateObject.hasValue(deptId)) {
			dc.add(Restrictions.eq("deptId", deptId.trim()));
		}
		if (ValidateObject.hasValue(userid)) {
			dc.add(Restrictions.eq("userId", userid.trim()));
		}
		List<UserCarInfo> uciList = this.getDao().findAllByCriteria(dc);
		UserCarInfo uci = null;
		if(uciList != null && uciList.size() > 0){
			uci = uciList.get(0);
		}
		return uci;
	}
//	/**
//	 * 查询车系
//	 * 
//	 * @author yuanqq
//	 * @date 2015-3-27上午09:55:17
//	 * @param brandid
//	 * @return
//	 */
//	public UserCarInfo getBrandByBrandId(String userid) {
//	DetachedCriteria dc = DetachedCriteria.forClass(User.class);
////	if (ValidateObject.intValue(brandId)) {
////		dc.add(Restrictions.eq("brandId", brandId));
////	}
////	if (ValidateObject.intValue(deptId)) {
////		dc.add(Restrictions.eq("deptId", deptId));
////	}
//	if (ValidateObject.hasValue(userid)) {
//		dc.add(Restrictions.eq("userId", userid));
//	}
//	List<UserCarInfo> uciList = this.getDao().findAllByCriteria(dc);
//	UserCarInfo uci = null;
//	if(uciList != null && uciList.size() > 0){
//		uci = uciList.get(0);
//	}
//	return uci;
//	}
	/**
	 * 根据id查找品牌
	 * 
	 * @param id
	 *            品牌id
	 */
	public Brand getBrand(int id) {
//		// return (Brand) dao.getIObjectByPK(Brand.class,
		// id);
		return (Brand) dao.getHibernateTemplate().get(Brand.class, id);
	}
	/**
	 * 根据id查找车系
	 * 
	 * @param id
	 *            品牌id
	 */
	public Cars getDept(int id) {
//		// return (Brand) dao.getIObjectByPK(Brand.class,
		// id);
		return (Cars) dao.getHibernateTemplate().get(Cars.class, id);
	}
}

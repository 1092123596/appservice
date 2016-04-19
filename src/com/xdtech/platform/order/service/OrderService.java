package com.xdtech.platform.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.xdtech.platform.carwash.service.CarwashService;
import com.xdtech.platform.core.service.BaseService;
import com.xdtech.platform.glosscoating.service.GlossService;
import com.xdtech.platform.minormt.service.MinormtService;
import com.xdtech.platform.order.bean.MtOrder;
import com.xdtech.platform.order.bean.MtOrderForm;
import com.xdtech.platform.order.bean.ProjectReview;
import com.xdtech.platform.user.bean.User;
import com.xdtech.platform.waxing.service.WaxingService;

@Service
public class OrderService extends BaseService {
	@Resource
	private MinormtService minormtService;
	@Resource
	private CarwashService carwashService;
	@Resource
	private WaxingService waxingService;
	@Resource
	private GlossService glossService;

	/**
	 * 查询订单信息
	 * 
	 * @author
	 * @date mt_projectreview
	 * @return
	 */
//	public List<HashMap> findOrderList(int start, int row, int orderState, String userid) {
//		StringBuffer sql = new StringBuffer();
//		sql.append("select o.orderId,o.orderNo,o.orderCreteTime,o.orderRealName,o.orderPhone,o.orderState,o.payPrice,f.ofId ,f.productname,f.pmount,f.payPrice,f.productId,f.projectId,f.orderState as ofstate,f.commentState,f.payPrice as pprice,f.verification ");
//		if (orderState == -1) {// 全部
//			sql.append("from mt_order as o,mt_orderform as f where (o.orderState=0 or o.orderState=1) ");
//		} else if (orderState == 0 || orderState == 1) {// 待支付，已支付
//			sql.append("from mt_order as o,mt_orderform as f where o.orderState=" + orderState + " ");
//		} else if (orderState == 2) {// 已消费
//			sql.append("from mt_order as o,mt_orderform as f where f.orderState=" + orderState + " ");
//		} else if (orderState == 3) {// 未评论
//			sql.append("from mt_order as o,mt_orderform as f where f.commentState=0 and o.orderState=1 and f.orderState=2 ");
//		} else if (orderState == 4) {// 已评论
//			sql.append(",p.userName,p.apIntegral,p.apcontent from mt_order as o,mt_orderform as f,mt_projectreview p where f.commentState=1 and p.orderformId=f.ofId and p.deleted=0 ");
//		}
//		sql.append(" and o.userid ='" + userid + "' and o.deleted=0 and f.deleted=0 and o.orderId=f.orderId order by o.orderCreteTime desc limit " + start + "," + row);
//		List<HashMap> orderList = this.getDao().getAllPreBySQL(sql.toString(), null);
//
//		return orderList;
//	}

	/**
	 * 根据用户id查询订单
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> findOrderByUserId(String userId, int orderState, int start, int row) {
		StringBuffer sql = new StringBuffer();
		sql.append("select orderId as orderId,orderNo as orderNo,orderCreteTime as orderTime,payPrice as payPrice,orderRealName as realName,orderPhone as phone,orderState as orderState from mt_order where userId='" + userId + "' and orderState=" + orderState + " and deleted=0 order by orderTime desc limit " + start + "," + row);
		return this.getDao().getAllPreBySQL(sql.toString(), null);
	}

	/**
	 * 查询订单中的产品
	 * 
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> findOrderProduct(String orderId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ofId as orderformId,productname as productName,pmount as pmount,payPrice as wprice,pjDmHours as dprice,productId as productId,projectId as projectId,verification as verification,orderState as ofState,commentState as commentState  from mt_orderform where deleted=0 and orderId='" + orderId +"'");
		return this.getDao().getAllPreBySQL(sql.toString(), null);
	}

	/**
	 * 根据服务id和产品id查询产品图片
	 * 
	 * @param projectId
	 * @param productId
	 * @return
	 */
	public String findProdImg(int projectId, int productId) {
		String imageUrl = null;
		if (projectId == 11814) {
			if (minormtService.findMinormtById(productId) != null && StringUtils.isNotEmpty(minormtService.findMinormtById(productId).getImg())) {
				imageUrl = minormtService.findMinormtById(productId).getImg();
			}
		} else if (projectId == 59) {
			if (carwashService.findCarwashById(productId) != null && StringUtils.isNotEmpty(carwashService.findCarwashById(productId).getImg())) {
				imageUrl = carwashService.findCarwashById(productId).getImg();
			}
		} else if (projectId == 106 || projectId == 107 || projectId == 114) {
			if (glossService.findGlossById(productId) != null && StringUtils.isNotEmpty(glossService.findGlossById(productId).getImg())) {
				imageUrl = glossService.findGlossById(productId).getImg();
			}
		} else if (projectId == 113) {
			if (waxingService.findWaxingById(productId) != null && StringUtils.isNotEmpty(waxingService.findWaxingById(productId).getImg())) {
				imageUrl = waxingService.findWaxingById(productId).getImg();
			}
		}
		return imageUrl;
	}

	/**
	 * 查询已完成订单
	 * 
	 * @param userId
	 * @param start
	 * @param row
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> findCompletedOrder(String userId, int start, int row) {
		StringBuffer sql = new StringBuffer();
		sql.append("select o.orderId as orderId,o.orderNo as orderNo,o.orderCreteTime as orderTime,o.payPrice as payPrice,o.orderRealName as realName,o.orderPhone as phone,f.productname as productName,f.pmount as pmount,f.payPrice as wprice,f.pjDmHours as dprice,f.productId as productId,f.projectId as projectId,f.verification as verification,f.orderState as ofState,f.ofId as orderformId,f.commentState as commentState from mt_order o,mt_orderform f where o.orderId=f.orderId and o.userId='" + userId + "' and f.orderState=2 and o.deleted=0 and f.deleted=0 order by o.orderCreteTime desc limit " + start + "," + row);
		return this.getDao().getAllPreBySQL(sql.toString(), null);
	}

	/**
	 * 评论
	 * 
	 * @param userId
	 * @param start
	 * @param row
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> commentList(String userId, int start, int row, int state) {
		StringBuffer sql = new StringBuffer();
		if (state == 0) {// 未评论
			sql.append("select o.orderId as orderId,o.orderNo as orderNo,o.orderCreteTime as orderTime,o.payPrice as payPrice,f.ofId as orderformId,f.productname as productName,f.pmount as pmount,f.payPrice as wprice,f.pjDmHours as dprice,f.productId as productId,f.projectId as projectId,f.commentState as commentState from mt_order o,mt_orderform f where o.orderId=f.orderId and o.userId='" + userId + "' and f.orderState=2 and f.commentState=0 and o.deleted=0 and f.deleted=0 order by o.orderCreteTime desc limit " + start + "," + row);
		} else {// 已评论
			sql.append("select o.orderId as orderId,o.orderNo as orderNo,o.orderCreteTime as orderTime,o.payPrice as payPrice,f.productname as productName,f.pmount as pmount,f.payPrice as wprice,f.pjDmHours as dprice,f.productId as productId,f.projectId as projectId,f.commentState as commentState," + "p.userName as userName,p.apIntegral as apIntegral,p.apcontent as apcontent from mt_order o,mt_orderform f ,mt_projectreview p where o.orderId=f.orderId and p.orderformId=f.ofId and o.userId='" + userId + "' and f.orderState=2 and f.commentState=1 and o.deleted=0 and p.deleted=0 and f.deleted=0 order by o.orderCreteTime desc limit " + start + "," + row);
		}
		return this.getDao().getAllPreBySQL(sql.toString(), null);
	}

	/**
	 * 查询订单信息总数
	 * 
	 * @author
	 * @date 2015-3-23 下午4:43:36
	 * @param
	 * @return
	 */
	public int findOrderCount(int orderState, String userid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(distinct(o.orderId)) as count from mt_order as o,mt_orderform as f " + " where o.orderState=" + orderState + " and o.userid ='" + userid + "' and o.deleted=0");
		if (orderState == 2) {
			sql.append(" and  f.orderState=" + orderState + " ");
		} else {
			sql.append(" and o.orderState=" + orderState + " ");
		}
		List<HashMap> countList = this.getDao().getAllPreBySQL(sql.toString(), null);
		int count = Integer.parseInt(countList.get(0).get("count").toString());
		return count;
	}

	/**
	 * 删除订单数据
	 * 
	 * @param dataId
	 * @return
	 */
	public String orderOrder(String userid, String orderid) {
		String result = null;
		MtOrder of = (MtOrder) dao.getIObjectByPK(MtOrder.class, orderid);
		of.setDeleted(1);
		this.getDao().updateIObject(of);

		String hql = "from MtOrderForm  where deleted !=1 and orderId='" + of.getOrderId() + "'";
		List<MtOrderForm> list = dao.findByQuery(hql);
		if (list != null) {
			for (MtOrderForm o : list) {
				o.setDeleted(1);
				this.getDao().updateIObject(o);
			}
		}
		return result;
	}

	/**
	 * 用户评论
	 */
	@SuppressWarnings("unchecked")
	public boolean comment(User user, ProjectReview view) {
		boolean flag = false;
		if (view != null && StringUtils.isNotEmpty(view.getOrderFormId())) {
			MtOrderForm of = (MtOrderForm) this.getDao().findIObjectByPK(MtOrderForm.class, view.getOrderFormId());
			if (of != null) {
				// 保存评论
				ProjectReview review = new ProjectReview();
				if (user != null) {
					review.setUserId(user.getId());
					review.setUserName(user.getUsername());
				}
				review.setApId(of.getProjectid());
				review.setApName(of.getProjectname());
				review.setPdId(of.getProductid());
				review.setPdName(of.getProductname());
				review.setShopId(of.getShopId());
				review.setShopName(of.getShopname());
				review.setCreateTime(new Date());
				review.setDeleted(0);
				review.setOrderFormId(of.getOfId());
				review.setApIntegral(view.getApIntegral());
				review.setApContent(view.getApContent());
				this.getDao().saveIObject(review);

				// 更新订单表评论状态
				of.setCommentState(1);
				this.getDao().updateIObject(of);
				flag = true;
			}
		}
		return flag;
	}
}

package com.xdtech.platform.pay.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xdtech.platform.carwash.bean.MtCarwashcard;
import com.xdtech.platform.core.service.BaseService;
import com.xdtech.platform.core.util.SMSUtil;
import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.util.string.ConstantString;
import com.xdtech.platform.glosscoating.bean.MtGlossCoating;
import com.xdtech.platform.minormt.bean.MtAgencyProject;
import com.xdtech.platform.minormt.bean.MtMinormt;
import com.xdtech.platform.minormt.bean.Shop;
import com.xdtech.platform.order.bean.MtOrder;
import com.xdtech.platform.order.bean.MtOrderForm;
import com.xdtech.platform.pay.bean.PayNow;
import com.xdtech.platform.pay.bean.ShoppingCar;
import com.xdtech.platform.user.bean.User;
import com.xdtech.platform.waxing.bean.MtWaxing;

@Service
public class PayMentService extends BaseService{

	/**
	 * 查询小保养
	 * 
	 * @author shendelei
	 * @date 2014-9-23 上午10:20:21
	 * @param id
	 * @return
	 */
	public MtMinormt getMinormtById(int id) {
		return (MtMinormt) this.getDao().findIObjectByPK(MtMinormt.class, id);
	}
	
	/**
	 * 查询经销商
	 * 
	 * @author shendelei
	 * @date 2014-9-23 上午10:18:13
	 * @param id
	 * @return
	 */
	public Shop getShopById(int id) {
		return (Shop) this.getDao().findIObjectByPK(Shop.class, id);
	}
	
	/**
	 * 判断购物车中有无此产品
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午04:47:19
	 * @param projectid
	 *            服务id
	 * @param productid
	 *            产品id
	 * @param userid
	 *            用户id
	 * @param shopid
	 *            商户id
	 * @param styleid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ShoppingCar getShoppingCar(int projectid, int productid, String userid, int shopid, int styleid) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ShoppingCar where deleted=0");
		hql.append(" and projectid=" + projectid + " and productid=" + productid + " and userid='" + userid + "' and shopid=" + shopid + " and styleid=" + styleid + "");
		List<ShoppingCar> malist = this.getDao().findAllyHql(hql.toString(), null);
		if (malist.size() > 0) {
			return malist.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 根据shopid和projectid查图片
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午04:51:59
	 * @param shopId
	 *            商户Id
	 * @param projectId
	 *            服务id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findShopImage(int shopId, int projectId) {
		String sql = "from MtAgencyProject  where shopId = ? and projectId = ?";
		List<MtAgencyProject> mtAgencyProjectList = this.getDao().findAllyHql(sql, new Object[] { shopId, projectId });
		return mtAgencyProjectList.size() > 0 ? mtAgencyProjectList.get(0).getImg() : "";
	}
	
	/**
	 * 添加或更新购物车
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午04:55:44
	 * @param data
	 *            购物车实体
	 */
	@SuppressWarnings("unchecked")
	public void saveOrUpdateShoppingCar(ShoppingCar data) {
		this.getDao().saveOrUpdateIObject(data);
	}
	
	/**
	 * 购物车list
	 * @author shendelei
	 * @date 2015-3-25 下午2:29:48
	 * @param shoppingCarList
	 */
	public void saveOrUpdateShoppingCarList(List<ShoppingCar> shoppingCarList) {
		for(ShoppingCar shoppingCar:shoppingCarList){
			this.getDao().executeUpdate("update mt_shoppingcar set carCount = "+shoppingCar.getCarCount() +" where id = "+shoppingCar.getId());
		}
	}
	
	public void deleteShoppingCarListById(List<Integer> delIds){
		if(delIds != null){
			for(Integer id:delIds){
				this.getDao().executeUpdate("delete from mt_shoppingcar where id = "+id);
			}
		}
	}
	
	/**
	 * 购物车列表
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午02:43:08
	 * @param userId
	 *            用户Id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap> listShoppingCar(String userId, int startIndex, int pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append("select * from mt_shoppingcar where deleted=0 and userid='").append(userId).append("' limit "+startIndex+","+pageSize);
		System.out.println(hql.toString());
		return this.getDao().getAllPreBySQL(hql.toString(), null);
	}
	
	/**
	 * 购物车列表
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午02:43:08
	 * @param userId
	 *            用户Id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int listShoppingCarCount(String userId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) as count from mt_shoppingcar where deleted=0 and userid='").append(userId).append("'");
		List<HashMap> countList = this.getDao().getAllPreBySQL(hql.toString(),null);
		int count = Integer.parseInt(countList.get(0).get("count").toString());
		return count;
	}
	
	/**
	 * 通过id查询购物车
	 * 
	 * @author Xuc
	 * @date 2014-11-18下午01:34:43
	 * @param id
	 *            购物车id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ShoppingCar findShoppingCarById(int id) {
		return (ShoppingCar) this.getDao().findIObjectByPK(ShoppingCar.class, id);
	}
	
	
	/**
	 * 获得订单总共金额
	 * @author shendelei
	 * @date 2015-3-24 下午3:33:17
	 * @param id
	 * @return
	 */
	public int sumPriceShoppingCar(String userId){
		StringBuffer hql = new StringBuffer();
		hql.append("select ifnull(sum(wgprice*carCount),0) as sumPrice from mt_shoppingcar where deleted=0 and userid='").append(userId).append("'");
		List<HashMap> countList = this.getDao().getAllPreBySQL(hql.toString(),null);
		int sumPrice = Integer.parseInt(countList.get(0).get("sumPrice").toString());
		return sumPrice;
	}
	
	/**
	 * 查询用户购物车中指定产品
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午05:48:24
	 * @param userid
	 *            用户Id
	 * @param shoppingIds
	 *            购物车id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap> getShoppingCarUser(String userId, List<Integer> shoppingIds) {
		StringBuilder hql = new StringBuilder();
		hql.append("select * from mt_shoppingcar where deleted=0 and userid='").append(userId+"'");
		if (shoppingIds.size() > 0) {
			StringBuffer ids = new StringBuffer();
			for (Integer shoppingId : shoppingIds) {
				ids.append("'" + shoppingId + "',");
			}
			String shoppingId = ids.substring(0, ids.length() - 1);
			hql.append(" and id in (" + shoppingId + ")");
		}
		System.out.println(hql);
		return this.getDao().getAllPreBySQL(hql.toString(), null);
	}
	
	/**
	 * 查询用户购物车中指定产品
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午05:48:24
	 * @param userid
	 *            用户Id
	 * @param shoppingIds
	 *            购物车id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ShoppingCar> getShoppingCarList(String userid, List<Integer> shoppingIds) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ShoppingCar where deleted=0");
		hql.append(" and userid='" + userid + "'");
		if (shoppingIds.size() > 0) {
			StringBuffer ids = new StringBuffer();
			for (Integer shoppingId : shoppingIds) {
				ids.append("'" + shoppingId + "',");
			}
			String shoppingId = ids.substring(0, ids.length() - 1);
			hql.append(" and id in (" + shoppingId + ")");
		}
		return this.getDao().findAllyHql(hql.toString(), null);
	}
	
	/**
	 * 从购物车下单
	 * 
	 * @author Xuc
	 * @date 2014-11-18上午09:47:01
	 * @param mtshoppingcarList
	 *            购物车列表
	 * @param registerUser
	 *            用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MtOrder saveOrder(List<ShoppingCar> mtshoppingcarList, User registerUser) {
		int payPrice = 0;
		for (ShoppingCar mtShopPingCar : mtshoppingcarList) {
			payPrice += mtShopPingCar.getWgprice() * mtShopPingCar.getCarCount();
		}
		String orderNo = wbOrderNo();
		MtOrder wo = new MtOrder();
		wo.setOrderNo(orderNo);
		wo.setUserId(String.valueOf(registerUser.getId()));
//		wo.setUserName(registerUser.getUsername());
//		wo.setOrderRealName(registerUser.getTrueName());
//		wo.setOrderEmail(registerUser.getEmail());
//		wo.setOrderPhone(registerUser.getContactNo());
		wo.setOrderBi(0);
		wo.setOrderType(1);
		wo.setOrderState(0);
		wo.setOrderCreteTime(new Date());// 下单时间
		wo.setOrderCostPrice(new BigDecimal(payPrice));// 订单总价
		wo.setOrderVIPPrice(new BigDecimal(payPrice));// 优惠价格后期开发还用总价
		wo.setPayPrice(new BigDecimal(payPrice));
		wo.setOrderRemark("无");// 备注 后期开发
		wo.setDeleted(0);
		wo.setOrdertime(new Date());
		wo.setCutPrice(new BigDecimal(0));
		dao.saveIObject(wo);

		for (ShoppingCar mss : mtshoppingcarList) {
			for (int i = 1; i <= mss.getCarCount(); i++) {
				MtOrderForm wof = new MtOrderForm();
				wof.setOrderId(wo.getOrderId());
				wof.setOrderState(0);
				wof.setShopId(mss.getShopId());
				wof.setDeleted(0);
				wof.setSubscription(0);// 订金比率
				wof.setPmount(1);
				wof.setProductid(mss.getProductId());
				wof.setProductname(mss.getProductName());
				wof.setProjectBi(0);
				if (mss.getProjectId() == 0) {
					wof.setProjectid(11814);
				} else {
					wof.setProjectid(mss.getProjectId());
				}

				if (StringUtils.isEmpty(mss.getProjectName())) {
					wof.setProjectname("小保养");
				} else {
					wof.setProjectname(mss.getProjectName());
				}

				wof.setOfType("0");// 0全款，1定金,2免费预约
				wof.setShopId(mss.getShopId());
				wof.setShopname(mss.getShopName());
				wof.setStyleId(mss.getStyleId());
				wof.setStyleName(mss.getStyleName());
				wof.setOrdertime(new Date());

				if (mss.getPaytype() == 0) {
					wof.setPayPrice(new BigDecimal(mss.getWgprice()));// 网购价,要加上工时费用
				}
				if (mss.getPaytype() == 1) {
					wof.setPayPrice(new BigDecimal(mss.getDjprice()));// 定金价
				}
				wof.setPjDmHours(new BigDecimal(mss.getDmprice()));// 店面价
				wof.setPjWgHours(new BigDecimal(mss.getWgprice()));// 网购价
				wof.setWorkWgHours(new BigDecimal(mss.getDjprice()));// 工时价格

				wof.setOfCostPrice(new BigDecimal(mss.getWgprice()));// 不用
				wof.setOfVIPrice(new BigDecimal(mss.getWgprice()));// 不用
				wof.setWorkDmHours(new BigDecimal(mss.getWgprice()));// 不用
//				if (mss.getShopId() == ServerInfo.getTongYongShopId()) {
//					wof.setGeneraltype(1);
//					String verification = generateString(8);
//					while (verification != "") {
//						if (findMtOrderFormByverification(verification)) {
//							wof.setVerification(verification);
//							break;
//						}
//						verification = generateString(8);
//					}
//
//				} else {
					wof.setGeneraltype(0);
					wof.setVerification(String.valueOf(Math.abs(new Random().nextInt()) % 100000000));
//				}
				wof.setProductnumber(i);
				this.dao.saveIObject(wof);

			}
			deleteShopPingCar(mss.getId());// 删除
		}
		return wo;
	}
	
	/**
	 * 小保养立即购买
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午03:47:50
	 * @param payNow
	 *            下单帮助实体
	 * @param registerUser
	 *            用户
	 * @param carCount
	 *            数量
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MtOrder payNowMinSaveOrder(PayNow payNow, User registerUser, int carCount) {
		if (carCount < 1) {
			carCount = 1;
		}
		MtMinormt mtMinormt = getMinormtById(payNow.getId());
		MtOrder wo = new MtOrder();
		if (mtMinormt != null) {
			/*
			 * 下单
			 */
			String orderNo = wbOrderNo();
			wo.setOrderNo(orderNo);
			wo.setUserId(String.valueOf(registerUser.getId()));
			wo.setUserName(registerUser.getUsername());
			wo.setOrderRealName(registerUser.getTrueName());
			wo.setOrderEmail(registerUser.getEmail());
			wo.setOrderPhone(registerUser.getContactNo());
			wo.setOrderBi(0);
			wo.setOrderType(1);
			wo.setOrderState(0);
			wo.setOrderCreteTime(new Date());
			wo.setOrderCostPrice(new BigDecimal(mtMinormt.getWebprice()).multiply(new BigDecimal(carCount)));// 订单总价
			wo.setOrderVIPPrice(new BigDecimal(mtMinormt.getWebprice()).multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			wo.setPayPrice(new BigDecimal(mtMinormt.getWebprice()).multiply(new BigDecimal(carCount)));
			wo.setOrderRemark("无");// 备注 后期开发
			wo.setDeleted(0);
			wo.setOrdertime(new Date());
			wo.setCutPrice(new BigDecimal(0));
			dao.saveIObject(wo);

			Shop shop = getShopById(mtMinormt.getShopid());

			/*
			 * 订单明细
			 */
			for (int i = 1; i <= carCount; i++) {
				MtOrderForm wof = new MtOrderForm();
				wof.setOrderId(wo.getOrderId());
				wof.setOrderState(0);
				wof.setShopId(mtMinormt.getShopid());
				wof.setDeleted(0);
				wof.setSubscription(0);// 订金比率
				wof.setPmount(1);
				wof.setProductid(mtMinormt.getId());
				wof.setProductname(mtMinormt.getProductname());
				wof.setProjectBi(0);
				wof.setProjectid(11814);
				wof.setProjectname("小保养");
				if (shop.getClassification() == 1) {
					wof.setOfType("2");// 0全款，1定金，2免费预约
				} else {
					wof.setOfType("0");// 0全款，1定金，2免费预约
				}
				wof.setShopname(shop == null ? "" : shop.getShopName());
				wof.setStyleId(mtMinormt.getStyleid());
				wof.setStyleName(mtMinormt.getStylename());
				wof.setOrdertime(new Date());

				wof.setPayPrice(new BigDecimal(mtMinormt.getWebprice()));// 网购价,要加上工时费用
				wof.setPjDmHours(new BigDecimal(mtMinormt.getDmprice()));// 店面价
				wof.setPjWgHours(new BigDecimal(mtMinormt.getWebprice()));// 网购价
				wof.setWorkWgHours(new BigDecimal(mtMinormt.getHwprice()));// 定金价格

				wof.setOfCostPrice(new BigDecimal(mtMinormt.getWebprice()));// 不用
				wof.setOfVIPrice(new BigDecimal(mtMinormt.getWebprice()));// 不用
				wof.setWorkDmHours(new BigDecimal(mtMinormt.getWebprice()));// 不用
//				if (mtMinormt.getShopid() == ServerInfo.getTongYongShopId()) {
//					wof.setGeneraltype(1);
//					String verification = generateString(8);
//					while (verification != "") {
//						if (findMtOrderFormByverification(verification)) {
//							wof.setVerification(verification);
//							break;
//						}
//						verification = generateString(8);
//					}
//				} else {
					wof.setGeneraltype(0);
					wof.setVerification(String.valueOf(Math.abs(new Random().nextInt()) % 100000000));
//				}
				wof.setProductnumber(i);
				this.dao.saveIObject(wof);

				if (shop.getClassification() == 1) {
					sendData(wo);// 4S店免费预约短信
				}
			}
		}

		return wo;
	}
	
	/**
	 * 洗车立即购买
	 * 
	 * @author Xuc
	 * @date 2014-11-18上午09:50:32
	 * @param payNow
	 *            下单帮助实体
	 * @param registerUser
	 *            用户
	 * @param carLevel
	 *            车型级别
	 * @param carCount
	 *            数量
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MtOrder payNowCarWashSaveOrder(PayNow payNow, User registerUser, int carLevel, int carCount) {
		if (carCount < 1) {
			carCount = 1;
		}
		MtCarwashcard mtCarwashcard = getCarWashbyId(payNow.getId());
		MtOrder wo = new MtOrder();
		if (mtCarwashcard != null) {
			Shop shop = getShopById(mtCarwashcard.getShopId());
			/*
			 * 下单
			 */
			String orderNo = wbOrderNo();
			wo.setOrderNo(orderNo);// 订单号
			wo.setPcount(carCount);// 数量
			wo.setShopId(mtCarwashcard.getShopId());// 服务商ID
			wo.setShopName(shop.getShopName());// 服务商名称
			wo.setUserId(registerUser.getId());// 用户ID
			wo.setUserName(registerUser.getUsername());// 用户名称
			wo.setOrderBi(0);// 180币，暂时没开启
			wo.setOrderType(1);// 1为购物车2为保养套餐
			wo.setUserName(registerUser.getUsername());
			wo.setOrderRealName(registerUser.getTrueName());
			wo.setOrderEmail(registerUser.getEmail());
			wo.setOrderPhone(registerUser.getContactNo());
			BigDecimal payPrice = new BigDecimal(0);
			BigDecimal dmPrice = new BigDecimal(0);
			if (carLevel == 1) {// 小型车
				payPrice = mtCarwashcard.getXhwprice();
				dmPrice = mtCarwashcard.getXhprice();
				wo.setOrderCostPrice(mtCarwashcard.getXhwprice().multiply(new BigDecimal(carCount)));// 订单总价
				wo.setOrderVIPPrice(mtCarwashcard.getXhwprice().multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			}
			if (carLevel == 3) {// suv
				payPrice = mtCarwashcard.getShwprice();
				dmPrice = mtCarwashcard.getShprice();
				wo.setOrderCostPrice(mtCarwashcard.getShwprice().multiply(new BigDecimal(carCount)));// 订单总价
				wo.setOrderVIPPrice(mtCarwashcard.getShwprice().multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			}
			if (carLevel == 4) {// 商务/面包
				payPrice = mtCarwashcard.getMhwprice();
				dmPrice = mtCarwashcard.getMhprice();
				wo.setOrderCostPrice(mtCarwashcard.getMhwprice().multiply(new BigDecimal(carCount)));// 订单总价
				wo.setOrderVIPPrice(mtCarwashcard.getMhwprice().multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			}
			wo.setPayPrice(payPrice.multiply(new BigDecimal(carCount)));
			wo.setOrderState(0);
			wo.setOrderCreteTime(new Date());
			wo.setOrderRemark("无");// 备注 后期开发
			wo.setDeleted(0);
			wo.setOrdertime(new Date());
			wo.setCutPrice(new BigDecimal(0));
			dao.saveIObject(wo);

			/*
			 * 订单明细
			 */
			for (int i = 1; i <= carCount; i++) {
				MtOrderForm wof = new MtOrderForm();
				wof.setOrderId(wo.getOrderId());
				wof.setOrderState(0);
				wof.setShopId(mtCarwashcard.getShopId());
				wof.setDeleted(0);
				wof.setSubscription(0);// 订金比率
				wof.setPmount(1);
				wof.setProductid(mtCarwashcard.getId());
				wof.setProductname(mtCarwashcard.getTitle());
				wof.setProjectBi(0);
				wof.setProjectid(mtCarwashcard.getProjectId());
				wof.setProjectname(mtCarwashcard.getProjectName());
				wof.setOfType("0");// 0全款，1定金
				wof.setShopname(shop == null ? "" : shop.getShopName());
				wof.setOrdertime(new Date());
				wof.setVerification(String.valueOf(Math.abs(new Random().nextInt()) % 100000000));
				wof.setPayPrice(payPrice);// 网购价,要加上工时费用
				wof.setPjDmHours(dmPrice);// 店面价
				wof.setPjWgHours(payPrice);// 网购价
				wof.setWorkWgHours(new BigDecimal(0));// 定金价格

				wof.setOfCostPrice(payPrice);// 不用
				wof.setOfVIPrice(payPrice);// 不用
				wof.setWorkDmHours(payPrice);// 不用
//				if (mtCarwashcard.getShopId() == ServerInfo.getTongYongShopId()) {
//					wof.setGeneraltype(1);
//					String verification = generateString(8);
//					while (verification != "") {
//						if (findMtOrderFormByverification(verification)) {
//							wof.setVerification(verification);
//							break;
//						}
//						verification = generateString(8);
//					}
//				} else {
					wof.setGeneraltype(0);
					wof.setVerification(String.valueOf(Math.abs(new Random().nextInt()) % 100000000));
//				}
				wof.setProductnumber(i);
				this.dao.saveIObject(wof);

			}
		}
		return wo;
	}
	
	/**
	 * 封釉镀膜镀晶立即购买
	 * 
	 * @author Xuc
	 * @date 2014-11-18上午09:54:06
	 * @param payNow
	 *            下单帮助实体
	 * @param registerUser
	 *            用户
	 * @param carLevel
	 *            车型级别
	 * @param carCount
	 *            数量
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MtOrder payNowGlossSaveOrder(PayNow payNow, User registerUser, int carLevel, int carCount) {
		if (carCount < 1) {
			carCount = 1;
		}
		MtGlossCoating mt = getGlossCoating(payNow.getId());
		MtOrder wo = new MtOrder();
		if (mt != null) {
			Shop shop = getShopById(mt.getShopid());
			/*
			 * 下单
			 */
			String orderNo = wbOrderNo();
			wo.setOrderNo(orderNo);// 订单号
			wo.setPcount(carCount);// 数量
			wo.setShopId(mt.getShopid());// 服务商ID
			wo.setShopName(shop.getShopName());// 服务商名称
			wo.setUserId(registerUser.getId());// 用户ID
			wo.setUserName(registerUser.getUsername());// 用户名称
			wo.setOrderBi(0);// 180币，暂时没开启
			wo.setOrderType(1);// 1为购物车,立即支付2为保养套餐
			wo.setUserName(registerUser.getUsername());
			wo.setOrderRealName(registerUser.getTrueName());
			wo.setOrderEmail(registerUser.getEmail());
			wo.setOrderPhone(registerUser.getContactNo());
			BigDecimal payPrice = new BigDecimal(0);
			BigDecimal dmPrice = new BigDecimal(0);
			if (carLevel == 1) {// 小型车
				payPrice = mt.getWprice();
				dmPrice = mt.getDprice();
				wo.setOrderCostPrice(mt.getWprice().multiply(new BigDecimal(carCount)));// 订单总价
				wo.setOrderVIPPrice(mt.getWprice().multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			}
			if (carLevel == 3) {// suv
				payPrice = mt.getSwprice();
				dmPrice = mt.getSdprice();
				wo.setOrderCostPrice(mt.getSwprice().multiply(new BigDecimal(carCount)));// 订单总价
				wo.setOrderVIPPrice(mt.getSwprice().multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			}
			if (carLevel == 4) {// 商务/面包
				payPrice = mt.getBwprice();
				dmPrice = mt.getBdprice();
				wo.setOrderCostPrice(mt.getBwprice().multiply(new BigDecimal(carCount)));// 订单总价
				wo.setOrderVIPPrice(mt.getBwprice().multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			}
			wo.setPayPrice(payPrice.multiply(new BigDecimal(carCount)));
			wo.setOrderState(0);
			wo.setOrderCreteTime(new Date());
			wo.setOrderRemark("无");// 备注 后期开发
			wo.setDeleted(0);
			wo.setOrdertime(new Date());
			wo.setCutPrice(new BigDecimal(0));
			dao.saveIObject(wo);

			/*
			 * 订单明细
			 */
			for (int i = 1; i <= carCount; i++) {
				MtOrderForm wof = new MtOrderForm();
				wof.setOrderId(wo.getOrderId());
				wof.setOrderState(0);
				wof.setShopId(mt.getShopid());
				wof.setDeleted(0);
				wof.setSubscription(0);// 订金比率
				wof.setPmount(1);
				wof.setProductid(mt.getId());
				wof.setProductname(mt.getTitle());
				wof.setProjectBi(0);
				wof.setProjectid(mt.getProjectid());
				wof.setProjectname(mt.getProjectname());
				wof.setOfType("0");// 0全款，1定金
				wof.setShopname(shop == null ? "" : shop.getShopName());
				wof.setOrdertime(new Date());
				wof.setVerification(String.valueOf(Math.abs(new Random().nextInt()) % 100000000));
				wof.setPayPrice(payPrice);// 网购价,要加上工时费用
				wof.setPjDmHours(dmPrice);// 店面价
				wof.setPjWgHours(payPrice);// 网购价
				wof.setWorkWgHours(new BigDecimal(0));// 定金价格

				wof.setOfCostPrice(payPrice);// 不用
				wof.setOfVIPrice(payPrice);// 不用
				wof.setWorkDmHours(payPrice);// 不用
//				if (mt.getShopid() == ServerInfo.getTongYongShopId()) {
//					wof.setGeneraltype(1);
//					String verification = generateString(8);
//					while (verification != "") {
//						if (findMtOrderFormByverification(verification)) {
//							wof.setVerification(verification);
//							break;
//						}
//						verification = generateString(8);
//					}
//				} else {
					wof.setGeneraltype(0);
					wof.setVerification(String.valueOf(Math.abs(new Random().nextInt()) % 100000000));
//				}
				wof.setProductnumber(i);
				this.dao.saveIObject(wof);

			}
		}
		return wo;
	}
	
	/**
	 * 打蜡立即购买
	 * 
	 * @author Xuc
	 * @date 2014-11-18上午09:56:49
	 * @param payNow
	 * @param registerUser
	 * @param carLevel
	 * @param carCount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MtOrder payNowWaxingSaveOrder(PayNow payNow, User registerUser, int carLevel, int carCount) {
		if (carCount < 1) {
			carCount = 1;
		}
		MtWaxing waxing = getWaxingbyId(payNow.getId());
		MtOrder wo = new MtOrder();
		if (waxing != null) {
			Shop shop = getShopById(waxing.getShopId());
			/*
			 * 下单
			 */
			String orderNo = wbOrderNo();
			wo.setOrderNo(orderNo);// 订单号
			wo.setPcount(carCount);// 数量
			wo.setShopId(waxing.getShopId());// 服务商ID
			wo.setShopName(shop.getShopName());// 服务商名称
			wo.setUserId(registerUser.getId());// 用户ID
			wo.setUserName(registerUser.getUsername());// 用户名称
			wo.setOrderBi(0);// 180币，暂时没开启
			wo.setOrderType(1);// 1为购物车,立即支付2为保养套餐
			wo.setUserName(registerUser.getUsername());
			wo.setOrderRealName(registerUser.getTrueName());
			wo.setOrderEmail(registerUser.getEmail());
			wo.setOrderPhone(registerUser.getContactNo());
			BigDecimal payPrice = new BigDecimal(0);
			BigDecimal dmPrice = new BigDecimal(0);

			if (carLevel == 1) {// 小型车
				if (waxing.getWprice() != null) {
					payPrice = waxing.getWprice().add(waxing.getHwprice() == null ? new BigDecimal(0) : waxing.getHwprice());
				} else {
					payPrice = waxing.getHwprice() == null ? new BigDecimal(0) : waxing.getHwprice();
				}
				dmPrice = waxing.getDprice();
				wo.setOrderCostPrice(waxing.getWprice().multiply(new BigDecimal(carCount)));// 订单总价
				wo.setOrderVIPPrice(waxing.getWprice().multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			}
			if (carLevel == 3) {// suv
				if (waxing.getShwprice() != null) {
					payPrice = waxing.getShwprice().add(waxing.getSwprice() == null ? new BigDecimal(0) : waxing.getSwprice());
				} else {
					payPrice = waxing.getSwprice() == null ? new BigDecimal(0) : waxing.getSwprice();
				}

				dmPrice = waxing.getSdprice();
				wo.setOrderCostPrice(waxing.getSwprice().multiply(new BigDecimal(carCount)));// 订单总价
				wo.setOrderVIPPrice(waxing.getSwprice().multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			}
			if (carLevel == 4) {// 商务/面包
				if (waxing.getBhwprice() != null) {
					payPrice = waxing.getBhwprice().add(waxing.getBwprice() == null ? new BigDecimal(0) : waxing.getBwprice());
				} else {
					payPrice = waxing.getBwprice() == null ? new BigDecimal(0) : waxing.getBwprice();
				}
				dmPrice = waxing.getBdprice();
				wo.setOrderCostPrice(waxing.getBwprice().multiply(new BigDecimal(carCount)));// 订单总价
				wo.setOrderVIPPrice(waxing.getBwprice().multiply(new BigDecimal(carCount)));// 优惠价格后期开发还用总价
			}
			wo.setPayPrice(payPrice.multiply(new BigDecimal(carCount)));
			wo.setOrderState(0);
			wo.setOrderCreteTime(new Date());
			wo.setOrderRemark("无");// 备注 后期开发
			wo.setDeleted(0);
			wo.setOrdertime(new Date());
			wo.setCutPrice(new BigDecimal(0));
			dao.saveIObject(wo);

			/*
			 * 订单明细
			 */
			for (int i = 1; i <= carCount; i++) {
				MtOrderForm wof = new MtOrderForm();
				wof.setOrderId(wo.getOrderId());
				wof.setOrderState(0);
				wof.setShopId(waxing.getShopId());
				wof.setDeleted(0);
				wof.setSubscription(0);// 订金比率
				wof.setPmount(1);
				wof.setProductid(waxing.getId());
				wof.setProductname(waxing.getTitle());
				wof.setProjectBi(0);
				wof.setProjectid(waxing.getProjectId());
				wof.setProjectname(waxing.getProjectName());
				wof.setOfType("0");// 0全款，1定金
				wof.setShopname(shop == null ? "" : shop.getShopName());
				wof.setOrdertime(new Date());
				wof.setVerification(String.valueOf(Math.abs(new Random().nextInt()) % 100000000));
				wof.setPayPrice(payPrice);// 网购价,要加上工时费用
				wof.setPjDmHours(dmPrice);// 店面价
				wof.setPjWgHours(payPrice);// 网购价
				wof.setWorkWgHours(new BigDecimal(0));// 定金价格

				wof.setOfCostPrice(payPrice);// 不用
				wof.setOfVIPrice(payPrice);// 不用
				wof.setWorkDmHours(payPrice);// 不用
//				if (waxing.getShopId() == ServerInfo.getTongYongShopId()) {
//					wof.setGeneraltype(1);
//					String verification = generateString(8);
//					while (verification != "") {
//						if (findMtOrderFormByverification(verification)) {
//							wof.setVerification(verification);
//							break;
//						}
//						verification = generateString(8);
//					}
//				} else {
					wof.setGeneraltype(0);
					wof.setVerification(String.valueOf(Math.abs(new Random().nextInt()) % 100000000));
//				}
				wof.setProductnumber(i);
				this.dao.saveIObject(wof);

			}
		}
		return wo;
	}
	
	/**
	 * 维保订单编号 用4位随机数+当前时间精确到毫秒,数据库同时增加唯一索引防止订单号出现重复情况
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午03:50:13
	 * @return
	 */
	public String wbOrderNo() {
		int r1 = (int) (Math.random() * (10000));
		long now = System.currentTimeMillis();
		String orderNo = "WB" + r1 + now;
		return orderNo;
	}
	
	/**
	 * 删除购物车
	 * 
	 * @author Xuc
	 * @date 2014-11-18上午09:46:29
	 * @param id
	 *            购物车Id
	 */
	public void deleteShopPingCar(int id) {
		dao.executeUpdate("delete from mt_shoppingcar where id = " + id);
	}
	
	/**
	 * 更新用户
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午05:05:16
	 * @param user
	 *            用户实体
	 */
	@SuppressWarnings("unchecked")
	public void updateUserInfo(User user) {
		this.getDao().updateIObject(user);
	}
	
	/**
	 * 发送短信
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午04:34:16
	 * @param mtOrder
	 *            订单
	 */
	public void sendData(MtOrder mtOrder) {
		SMSUtil smsUtil = new SMSUtil();
		String sendData = "";
		String sendData2 = "";
		List<MtOrderForm> wofList = findMtOrderFormList(mtOrder.getOrderId());// 订单详细表
		for (MtOrderForm mtOrderForm : wofList) {
			Shop shop = getShopById(mtOrderForm.getShopId());
			if (shop.getClassification() == 0) {// 0为维修厂，1为4S店

				if (mtOrderForm.getGeneraltype() == 0) {
					/*
					 * 给用户发短信
					 */
					sendData = "您已成功购买了" + shop.getShopName() + "[" + shop.getAddr() + "]" + mtOrderForm.getProjectname() + "(" + mtOrderForm.getProductname() + ")。订单号" + mtOrder.getOrderNo() + ",产品号" + mtOrderForm.getProductnumber() + ",验证码" + mtOrderForm.getVerification() + ",请在服务完成后出示,客服电话4006099786!";
					smsUtil.sendSMS(new String[] { mtOrder.getOrderPhone() }, sendData);
					/*
					 * 给服务商发短信
					 */
					sendData2 = "180迈用户" + mtOrder.getOrderRealName() + ",手机号" + mtOrder.getOrderPhone() + ",已成功购买了(" + shop.getShopName() + ")," + mtOrderForm.getProjectname() + "(" + mtOrderForm.getProductname() + ")的服务,全款支付,订单号" + mtOrder.getOrderNo() + ",产品号" + mtOrderForm.getProductnumber() + ",如有问题请致电4006099786!";
					smsUtil.sendSMS(new String[] { shop.getPhone() }, sendData2);
				} else {
					/*
					 * 通用产品给用户发短信
					 */
					sendData = "您已成功购买了180迈(通用产品)" + mtOrderForm.getProductname() + "" + mtOrderForm.getProjectname() + "的服务.订单号" + mtOrder.getOrderNo() + ",产品号" + mtOrderForm.getProductnumber() + ",验证码" + mtOrderForm.getVerification() + ",(与购买手机号同时使用),请先到店完成验证后再接受服务,服务商查询www.180mi.com,客服电话4006099786!";
					smsUtil.sendSMS(new String[] { mtOrder.getOrderPhone() }, sendData);

				}
			} else {
				/*
				 * 给预约用户发短信
				 */
				sendData = "预约成功,稍后客服会与您联系,详情请咨询电话4006099786";
				smsUtil.sendSMS(new String[] { mtOrder.getOrderPhone() }, sendData);
			}
		}
	}
	
	/**
	 * 查询订单详细
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午04:35:04
	 * @param orderId
	 *            订单id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MtOrderForm> findMtOrderFormList(String orderId) {
		String a = orderId + "";
		StringBuilder hql = new StringBuilder();
		List<Object> paramList = new ArrayList<Object>(1);
		hql.append("from OrderForm wf where orderId=? and deleted=0");
		paramList.add(a);
		return (List<MtOrderForm>) dao.findAllyHql(hql.toString(), paramList.toArray());
	}
	
	/**
	 * 查询洗车
	 * 
	 * @author Xuc
	 * @date 2014-11-18上午09:52:03
	 * @param id
	 *            洗车id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MtCarwashcard getCarWashbyId(int id) {
		return (MtCarwashcard) this.getDao().findIObjectByPK(MtCarwashcard.class, id);
	}
	
	/**
	 * 查询封釉镀膜镀晶
	 * 
	 * @author Xuc
	 * @date 2014-11-18上午09:55:45
	 * @param id
	 *            产品Id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MtGlossCoating getGlossCoating(int id) {
		return (MtGlossCoating) this.getDao().getIObjectByPK(MtGlossCoating.class, id);
	}
	
	/**
	 * 查询打蜡
	 * 
	 * @author Xuc
	 * @date 2014-11-18上午09:57:28
	 * @param id
	 *            产品id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MtWaxing getWaxingbyId(int id) {
		return (MtWaxing) this.getDao().findIObjectByPK(MtWaxing.class, id);
	}
	
	/**
	 * 通过订单编号查找订单
	 * 
	 * @param orderNo 订单号
	 * @return
	 * @author
	 */
	@SuppressWarnings("unchecked")
	public MtOrder getWbOrder(String orderNo) {
		DetachedCriteria dc = DetachedCriteria.forClass(MtOrder.class);
		dc.add(Restrictions.eq("orderNo", orderNo));
		dc.add(Restrictions.eq(ConstantString.DELETED, ConstanData.DATAVALID));
		List<MtOrder> dataList = this.getDao().findAllByCriteria(dc);
		if (dataList != null && dataList.size() > 0) {
			return dataList.get(0);
		}
		return null;
	}
}

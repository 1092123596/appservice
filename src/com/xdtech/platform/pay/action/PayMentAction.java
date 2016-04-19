package com.xdtech.platform.pay.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.Action;
import com.xdtech.platform.alipay.config.AlipayConfig;
import com.xdtech.platform.alipay.sign.SignUtils;
import com.xdtech.platform.alipay.util.AlipaySubmit;
import com.xdtech.platform.carwash.bean.MtCarwashcard;
import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.util.string.ServerInfo;
import com.xdtech.platform.core.web.action.BaseAction;
import com.xdtech.platform.glosscoating.bean.MtGlossCoating;
import com.xdtech.platform.minormt.bean.MtMinormt;
import com.xdtech.platform.minormt.bean.Shop;
import com.xdtech.platform.order.bean.MtOrder;
import com.xdtech.platform.pay.bean.PayNow;
import com.xdtech.platform.pay.bean.ShoppingCar;
import com.xdtech.platform.pay.service.PayMentService;
import com.xdtech.platform.pay.util.CommonUtil;
import com.xdtech.platform.pay.util.WxPayHelper;
import com.xdtech.platform.user.bean.User;
import com.xdtech.platform.waxing.bean.MtWaxing;

public class PayMentAction extends BaseAction{
	
	/**
	 * 支付serivce
	 */
	@Resource
	private PayMentService payMentService;
	
	/**
	 * 小保养实体
	 */
	private MtMinormt mtMinormt;

	/**
	 * 购物车实体
	 */
	private ShoppingCar shoppingCar;
	
	/**
	 * 购物车list
	 */
	private List<ShoppingCar> shoppingCarList;
	
	private List<Integer> delIds;
	
	/**
	 * 添加购物车数量
	 */
	private int carCount;
	
	
	private int start;
	
	
	private int row;
	
	/**
	 * 购物车下单选中id数组
	 */
	private List<Integer> selectedIds;
	
	/**
	 * 订单类型
	 */
	private String orderType;
	
	/**
	 * 立即支付实体
	 */
	private PayNow payNow;
	
	/**
	 * 洗车
	 */
	private MtCarwashcard mc;
	
	
	private int carLevel;
	
	/**
	 * 镀膜实体
	 */
	private MtGlossCoating mtgloss;
	
	/**
	 * 打蜡
	 */
	private MtWaxing waxing;
	
	/**
	 * 订单号
	 */
	private String orderNo;
	
	/**
	 * 支付宝网页支付表单
	 */
	private String alipayHtml;

	/**
	 * 小保养添加购物车
	 * 
	 * @author Xuc
	 * @date 2014-11-17下午04:57:47
	 */
	public void addMinormtShopping() {
		int code = ConstanData.FAILURECODE;
		String message = "添加失败！请联系管理员";
		if (currentGadUser != null) {
			if (mtMinormt != null) {
				mtMinormt = payMentService.getMinormtById(mtMinormt.getId());
				if (mtMinormt != null) {
					shoppingCar = new ShoppingCar();
					Shop shop = payMentService.getShopById(mtMinormt.getShopid());
					ShoppingCar mspc = payMentService.getShoppingCar(0, mtMinormt.getId(), currentGadUser.getId(), mtMinormt.getShopid(), mtMinormt.getStyleid());
					shoppingCar.setUserid(currentGadUser.getId());
					shoppingCar.setProjectId(0);
					shoppingCar.setProjectName("小保养");
					shoppingCar.setProductId(mtMinormt.getId());
					shoppingCar.setProductName(mtMinormt.getProductname());
					shoppingCar.setShopId(mtMinormt.getShopid());
					shoppingCar.setShopName(shop != null ? shop.getShopName() : "");
					shoppingCar.setStyleId(mtMinormt.getStyleid());
					shoppingCar.setStyleName(mtMinormt.getStylename());
					shoppingCar.setServicetype(0);
					shoppingCar.setPaytype(0);
					shoppingCar.setDmprice(mtMinormt.getDmprice());
					shoppingCar.setWgprice(mtMinormt.getWebprice());
					shoppingCar.setDatetime(new Date());
					shoppingCar.setDeleted(ConstanData.DATAVALID);
					if (StringUtils.isEmpty(mtMinormt.getImg())) {
						shoppingCar.setImg(payMentService.findShopImage(mtMinormt.getShopid(), 11814));
					} else {
						shoppingCar.setImg(mtMinormt.getImg());
					}
					if (mspc == null) {
						shoppingCar.setCarCount(carCount);
						payMentService.saveOrUpdateShoppingCar(shoppingCar);
					} else {
						mspc.setCarCount(carCount + mspc.getCarCount());
						payMentService.saveOrUpdateShoppingCar(mspc);
					}
					code = ConstanData.SUCCESSCODE;
					message = "添加购物车成功";
				} else {
					code = ConstanData.FAILURECODE;
					message = "添加失败！此产品不存在或已经被删除";
				}
			}
		} else {
			message = "您没有登录，请登录后操作";
		}
		writeValue(null, code, message, 0, 0, null);
	}
	
	/**
	 * 洗车加入购物车
	 * 
	 * @author Xuc
	 * @date 2014-11-19下午04:16:49
	 */
	public void addCarWashShopping() {
		int code = ConstanData.FAILURECODE;
		String message = "添加失败！请联系管理员";
		if (currentGadUser != null) {
			if (mc != null) {
				mc = payMentService.getCarWashbyId(mc.getId());
				if (mc != null) {
					shoppingCar = new ShoppingCar();
					Shop shop = payMentService.getShopById(mc.getShopId());
					ShoppingCar mspc = payMentService.getShoppingCar(mc.getProjectId(), mc.getId(), currentGadUser.getId(), mc.getShopId(), 0);
					shoppingCar.setUserid(currentGadUser.getId());
					shoppingCar.setProjectId(mc.getProjectId());
					shoppingCar.setProjectName(mc.getProjectName());
					shoppingCar.setProductId(mc.getId());
					shoppingCar.setProductName(mc.getTitle());
					shoppingCar.setShopId(mc.getShopId());
					shoppingCar.setShopName(shop != null ? shop.getShopName() : "");
					shoppingCar.setServicetype(0);
					shoppingCar.setPaytype(0);
					shoppingCar.setParm(carLevel);
					if (StringUtils.isEmpty(mc.getImg())) {
						shoppingCar.setImg(payMentService.findShopImage(mc.getShopId(), mc.getProjectId()));
					} else {
						shoppingCar.setImg(mc.getImg());
					}
					if (carLevel == 1) {// 小型车
						shoppingCar.setDmprice(mc.getXhprice().intValue());
						shoppingCar.setWgprice(mc.getXhwprice().intValue());
					}
					if (carLevel == 3) {// suv
						shoppingCar.setDmprice(mc.getShprice().intValue());
						shoppingCar.setWgprice(mc.getShwprice().intValue());
					}
					if (carLevel == 4) {// 商务/面包
						shoppingCar.setDmprice(mc.getMhprice().intValue());
						shoppingCar.setWgprice(mc.getMhwprice().intValue());
					}
					shoppingCar.setDatetime(new Date());
					shoppingCar.setDeleted(ConstanData.DATAVALID);
					if (mspc == null) {
						shoppingCar.setCarCount(carCount);
						payMentService.saveOrUpdateShoppingCar(shoppingCar);
					} else {
						mspc.setCarCount(carCount + mspc.getCarCount());
						payMentService.saveOrUpdateShoppingCar(mspc);
					}
					code = ConstanData.SUCCESSCODE;
					message = "添加购物车成功";
				} else {
					code = ConstanData.FAILURECODE;
					message = "添加失败！此产品不存在或已经被删除";
				}
			}
		}else{
			code = ConstanData.FAILURECODE;
			message = "您没有登录，请登录后操作";
		}
		writeValue(null, code, message, 0, 0, null);
	}
	
	/**
	 * 封釉镀膜镀晶加入购物车
	 * 
	 * @author Xuc
	 * @date 2014-11-19下午04:22:35
	 */
	public void addGlossCoatShopping() {
		int code = ConstanData.FAILURECODE;
		String message = "添加失败！请联系管理员";
		if (currentGadUser != null) {
			if (mtgloss != null) {
				mtgloss = payMentService.getGlossCoating(mtgloss.getId());
				if (mtgloss != null) {
					shoppingCar = new ShoppingCar();
					Shop shop = payMentService.getShopById(mtgloss.getShopid());
					shoppingCar.setUserid(currentGadUser.getId());
					shoppingCar.setProjectId(mtgloss.getProjectid());
					shoppingCar.setProjectName(mtgloss.getProjectname());
					shoppingCar.setProductId(mtgloss.getId());
					shoppingCar.setProductName(mtgloss.getTitle());
					shoppingCar.setShopId(mtgloss.getShopid());
					shoppingCar.setShopName(shop != null ? shop.getShopName() : "");
					shoppingCar.setServicetype(0);
					shoppingCar.setPaytype(0);
					shoppingCar.setParm(carLevel);

					if (StringUtils.isEmpty(mtgloss.getImg())) {
						shoppingCar.setImg(payMentService.findShopImage(mtgloss.getShopid(), mtgloss.getProjectid()));
					} else {
						shoppingCar.setImg(mtgloss.getImg());
					}
					if (carLevel == 1) {// 小型车
						shoppingCar.setDmprice((mtgloss.getDprice() != null ? mtgloss.getDprice().intValue() : 0) + (mtgloss.getHprice() != null ? mtgloss.getHprice().intValue() : 0));
						shoppingCar.setWgprice((mtgloss.getWprice() != null ? mtgloss.getWprice().intValue() : 0) + (mtgloss.getHwprice() != null ? mtgloss.getHwprice().intValue() : 0));
					}
					if (carLevel == 3) {// suv
						shoppingCar.setDmprice((mtgloss.getShprice() != null ? mtgloss.getShprice().intValue() : 0) + (mtgloss.getSdprice() != null ? mtgloss.getSdprice().intValue() : 0));
						shoppingCar.setWgprice((mtgloss.getShwprice() != null ? mtgloss.getShwprice().intValue() : 0) + (mtgloss.getSwprice() != null ? mtgloss.getSwprice().intValue() : 0));
					}
					if (carLevel == 4) {// 商务/面包
						shoppingCar.setDmprice((mtgloss.getBdprice() != null ? mtgloss.getBdprice().intValue() : 0) + (mtgloss.getBhprice() != null ? mtgloss.getBhprice().intValue() : 0));
						shoppingCar.setWgprice((mtgloss.getBhwprice() != null ? mtgloss.getBhwprice().intValue() : 0) + (mtgloss.getBwprice() != null ? mtgloss.getBwprice().intValue() : 0));
					}
					shoppingCar.setDatetime(new Date());
					shoppingCar.setDeleted(ConstanData.DATAVALID);

					ShoppingCar mspc = payMentService.getShoppingCar(mtgloss.getProjectid(), mtgloss.getId(), currentGadUser.getId(), mtgloss.getShopid(), 0);

					if (mspc == null) {
						shoppingCar.setCarCount(carCount);
						payMentService.saveOrUpdateShoppingCar(shoppingCar);
					} else {
						mspc.setCarCount(carCount + mspc.getCarCount());
						payMentService.saveOrUpdateShoppingCar(mspc);
					}
					code = ConstanData.SUCCESSCODE;
					message = "添加购物车成功";
				} else {
					code = ConstanData.FAILURECODE;
					message = "添加失败！此产品不存在或已经被删除";
				}
			}
		} else {
			code = ConstanData.FAILURECODE;
			message = "您没有登录，请登录后操作";
		}
		writeValue(null, code, message, 0, 0, null);
	}
	
	/**
	 * 打蜡加入购物车
	 * 
	 * @author Xuc
	 * @date 2014-11-19下午04:27:48
	 */
	public void addWaxingShopping() {
		int code = ConstanData.FAILURECODE;
		String message = "添加失败！请联系管理员";
		if (currentGadUser != null) {
			if (waxing != null) {
				waxing = payMentService.getWaxingbyId(waxing.getId());
				if (waxing != null) {
					shoppingCar = new ShoppingCar();
					Shop shop = payMentService.getShopById(waxing.getShopId());
					shoppingCar.setUserid(currentGadUser.getId());
					shoppingCar.setProjectId(waxing.getProjectId());
					shoppingCar.setProjectName(waxing.getProjectName());
					shoppingCar.setProductId(waxing.getId());
					shoppingCar.setProductName(waxing.getTitle());
					shoppingCar.setShopId(waxing.getShopId());
					shoppingCar.setShopName(shop != null ? shop.getShopName() : "");
					shoppingCar.setServicetype(0);
					shoppingCar.setPaytype(0);
					shoppingCar.setParm(carLevel);
					if (StringUtils.isEmpty(waxing.getImg())) {
						shoppingCar.setImg(payMentService.findShopImage(waxing.getShopId(), waxing.getProjectId()));
					} else {
						shoppingCar.setImg(waxing.getImg());
					}
					if (carLevel == 1) {// 小型车
						shoppingCar.setDmprice((waxing.getDprice() != null ? waxing.getDprice().intValue() : 0) + (waxing.getHprice() != null ? waxing.getHprice().intValue() : 0));
						shoppingCar.setWgprice((waxing.getWprice() != null ? waxing.getWprice().intValue() : 0) + (waxing.getHwprice() != null ? waxing.getHwprice().intValue() : 0));
					}
					if (carLevel == 3) {// suv
						shoppingCar.setDmprice((waxing.getShprice() != null ? waxing.getShprice().intValue() : 0) + (waxing.getSdprice() != null ? waxing.getSdprice().intValue() : 0));
						shoppingCar.setWgprice((waxing.getShwprice() != null ? waxing.getShwprice().intValue() : 0) + (waxing.getSwprice() != null ? waxing.getSwprice().intValue() : 0));
					}
					if (carLevel == 4) {// 商务/面包
						shoppingCar.setDmprice((waxing.getBdprice() != null ? waxing.getBdprice().intValue() : 0) + (waxing.getBhprice() != null ? waxing.getBhprice().intValue() : 0));
						shoppingCar.setWgprice((waxing.getBhwprice() != null ? waxing.getBhwprice().intValue() : 0) + (waxing.getBwprice() != null ? waxing.getBwprice().intValue() : 0));
					}
					shoppingCar.setDatetime(new Date());
					shoppingCar.setDeleted(ConstanData.DATAVALID);
					ShoppingCar mspc = payMentService.getShoppingCar(waxing.getProjectId(), waxing.getId(), currentGadUser.getId(), waxing.getShopId(), 0);
					if (mspc == null) {
						shoppingCar.setCarCount(carCount);
						payMentService.saveOrUpdateShoppingCar(shoppingCar);
					} else {
						mspc.setCarCount(carCount + mspc.getCarCount());
						payMentService.saveOrUpdateShoppingCar(mspc);
					}
					code = ConstanData.SUCCESSCODE;
					message = "添加购物车成功";
				} else {
					code = ConstanData.FAILURECODE;
					message = "添加失败！此产品不存在或已经被删除";
				}
			}
		} else {
			code = ConstanData.FAILURECODE;
			message = "您没有登录，请登录后操作";
		}
		writeValue(null, code, message, 0, 0, null);
	}
	
	/**
	 * 购物车列表
	 * 
	 * @author Xuc
	 * @date 2014-11-13上午09:45:11
	 * @return
	 */
	public void shoppingCarList() {
		List<HashMap> shoppingCarList = payMentService.listShoppingCar(currentGadUser.getId(), start, row);
		int count = payMentService.listShoppingCarCount(currentGadUser.getId());
//		int sumPrice = payMentService.sumPriceShoppingCar(currentGadUser.getId());
		writeValue(shoppingCarList,  ConstanData.SUCCESSCODE, null, count, 0, "");
	}
	
	/**
	 * 加减加入购物车
	 * 
	 * @author Xuc
	 * @date 2014-11-18下午01:31:20
	 * @return
	 */
	public void saveShoppingCarList() {
		payMentService.deleteShoppingCarListById(delIds);
		if(shoppingCarList != null){
			payMentService.saveOrUpdateShoppingCarList(shoppingCarList);
		}
		int sumPrice = payMentService.sumPriceShoppingCar(currentGadUser.getId());
		writeValue(null,  ConstanData.SUCCESSCODE, null, 0, sumPrice, "");
	}
	
	/**
	 * 购物车结算
	 * 
	 * @author Xuc
	 * @date 2014-11-18下午03:25:10
	 * @return
	 */
	public void settlementShopping() {
		List<HashMap> shoppingCarList = payMentService.getShoppingCarUser(currentGadUser.getId(), selectedIds);
		int sumPrice = payMentService.sumPriceShoppingCar(currentGadUser.getId());
		writeValue(shoppingCarList,  ConstanData.SUCCESSCODE, null, 0, sumPrice, "");
	}
	
	/**
	 * 提交订单
	 * 
	 * @author Xuc
	 * @date 2014-11-18下午04:30:40
	 * @return
	 */
	public void saveOrder() {
		try {
			MtOrder mtOrder = null;
//			currentGadUser = (currentGadUser) request.getSession().getAttribute("gadUser");// 登录账户信息
//			currentGadUser.setTrueName(trueName);
//			currentGadUser.setContactNo(phoneNo);
//			payMentService.updateUserInfo(currentGadUser);// 修改用户信息
			if (selectedIds != null) {
				shoppingCarList = payMentService.getShoppingCarList(currentGadUser.getId(), selectedIds);
			}
			if ("pay".equals(orderType)) {
				if (shoppingCarList != null && shoppingCarList.size() > 0) {
					mtOrder = payMentService.saveOrder(shoppingCarList, currentGadUser);
				}
			} else if ("minPayNow".equals(orderType)) {
				mtOrder = payMentService.payNowMinSaveOrder(payNow, currentGadUser, 1);
			} else if ("carWashPayNow".equals(orderType)) {
				mtOrder = payMentService.payNowCarWashSaveOrder(payNow, currentGadUser, carLevel, 1);
			} else if ("glossPayNow".equals(orderType)) {// 封釉镀膜镀晶 添加订单
				mtOrder = payMentService.payNowGlossSaveOrder(payNow, currentGadUser, carLevel, 1);
			} else if ("waxingPayNow".equals(orderType)) {
				mtOrder = payMentService.payNowWaxingSaveOrder(payNow, currentGadUser, carLevel, 1);
			}
			List<MtOrder> orderList = new ArrayList<MtOrder>();
			orderList.add(mtOrder);
			writeValue(orderList,  ConstanData.SUCCESSCODE, null, 0, 0, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 小保养立即购买
	 * 
	 * @author Xuc
	 * @date 2014-11-19下午02:41:17
	 * @return
	 */
	public void minPayNow() {
		List<PayNow> payNowList = new ArrayList<PayNow>();
		payNow = new PayNow();
//		Shop shop = null;
		if (mtMinormt != null) {
			mtMinormt = payMentService.getMinormtById(mtMinormt.getId());
//			if (mtMinormt != null) {
//				shop = payMentService.getShopById(mtMinormt.getShopid());
//			}
			if (StringUtils.isEmpty(mtMinormt.getImg())) {
				mtMinormt.setImg(payMentService.findShopImage(mtMinormt.getShopid(), 11814));
			} else {
				mtMinormt.setImg(mtMinormt.getImg());
			}
			payNow.setId(mtMinormt.getId());
			payNow.setImg(mtMinormt.getImg());
			payNow.setCarCount(1);
			payNow.setPayPrice(mtMinormt.getWebprice());
			payNow.setShopId(mtMinormt.getShopid());
			payNow.setProductName(mtMinormt.getProductname());
			payNow.setPrice(mtMinormt.getWebprice());
			payNowList.add(payNow);
			writeValue(payNowList,  ConstanData.SUCCESSCODE, null, 0, 0, "");
		}
	}
	
	/**
	 * 洗车立即购买
	 * 
	 * @author Xuc
	 * @date 2014-11-19下午03:59:39
	 * @return
	 */
	public void carWashPayNow() {
		payNow = new PayNow();
		int code = ConstanData.SUCCESSCODE;
		if (mc != null) {
			mc = payMentService.getCarWashbyId(mc.getId());
			if (carLevel == 1||carLevel == 0) {// 小型车
				mc.setWprice(mc.getXhwprice());
			} else if (carLevel == 3) {// suv
				mc.setWprice(mc.getShwprice());
			} else if (carLevel == 4) {// 商务/面包
				mc.setWprice(mc.getMhwprice());
			} else {
				code = ConstanData.FAILURECODE;
			}

			if (StringUtils.isEmpty(mc.getImg())) {
				mc.setImg(payMentService.findShopImage(mc.getShopId(), mc.getProjectId()));
			} else {
				mc.setImg(mc.getImg());
			}
			payNow.setId(mc.getId());
			payNow.setImg(mc.getImg());
			payNow.setCarCount(1);
			payNow.setPayPrice(mc.getWprice().intValue());
			payNow.setShopId(mc.getShopId());
			payNow.setProductName(mc.getTitle());
			payNow.setPrice(mc.getWprice().intValue());
			payNow.setCarLevel(carLevel);
			List<PayNow> payNowList = new ArrayList<PayNow>();
			payNowList.add(payNow);
			writeValue(payNowList, code, null, 0, 0, "");
		}
	}
	
	/**
	 * 封釉镀膜镀晶立即购买
	 * 
	 * @author Xuc
	 * @date 2014-11-19下午03:57:01
	 * @return
	 */
	public void glossPayNow() {
		int code = ConstanData.SUCCESSCODE;
		payNow = new PayNow();
		if (mtgloss != null) {
			mtgloss = payMentService.getGlossCoating(mtgloss.getId());
			if (mtgloss != null) {
				if (carLevel == 1) {// 小型车
					mtgloss.setWprice(mtgloss.getWprice());
				} else if (carLevel == 3) {// suv
					mtgloss.setWprice(mtgloss.getSwprice());
				} else if (carLevel == 4) {// 商务/面包
					mtgloss.setWprice(mtgloss.getBwprice());
				} else {
					code = ConstanData.FAILURECODE;
				}

				if (StringUtils.isEmpty(mtgloss.getImg())) {
					mtgloss.setImg(payMentService.findShopImage(mtgloss.getShopid(), mtgloss.getProjectid()));
				} else {
					mtgloss.setImg(mtgloss.getImg());
				}
				payNow.setId(mtgloss.getId());
				payNow.setImg(mtgloss.getImg());
				payNow.setCarCount(1);
				payNow.setPayPrice(mtgloss.getWprice().intValue());
				payNow.setShopId(mtgloss.getShopid());
				payNow.setProductName(mtgloss.getTitle());
				payNow.setPrice(mtgloss.getWprice().intValue());
				payNow.setCarLevel(carLevel);
				List<PayNow> payNowList = new ArrayList<PayNow>();
				payNowList.add(payNow);
				writeValue(payNowList, code, null, 0, 0, "");
			}
		}
	}
	
	/**
	 * 打蜡立即购买
	 * 
	 * @author Xuc
	 * @date 2014-11-19下午04:00:50
	 * @return
	 */
	public void waxingPayNow() {
		int code = ConstanData.SUCCESSCODE;
		payNow = new PayNow();
		if (waxing != null) {
			waxing = payMentService.getWaxingbyId(waxing.getId());
			if (waxing != null) {
				if (carLevel == 1) {// 小型车
					if (waxing.getWprice() != null) {
						waxing.setWprice(waxing.getWprice().add(waxing.getHwprice() == null ? new BigDecimal(0) : waxing.getHwprice()));
					} else {
						waxing.setWprice(waxing.getHwprice() == null ? new BigDecimal(0) : waxing.getHwprice());
					}
				} else if (carLevel == 3) {// suv
					if (waxing.getShwprice() != null) {
						waxing.setWprice(waxing.getShwprice().add(waxing.getSwprice() == null ? new BigDecimal(0) : waxing.getSwprice()));
					} else {
						waxing.setWprice(waxing.getSwprice() == null ? new BigDecimal(0) : waxing.getSwprice());
					}
				} else if (carLevel == 4) {// 商务/面包
					if (waxing.getBhwprice() != null) {
						waxing.setWprice(waxing.getBhwprice().add(waxing.getBwprice() == null ? new BigDecimal(0) : waxing.getBwprice()));
					} else {
						waxing.setWprice(waxing.getBwprice() == null ? new BigDecimal(0) : waxing.getBwprice());
					}
				} else {
					code = ConstanData.FAILURECODE;
				}

				if (StringUtils.isEmpty(waxing.getImg())) {
					waxing.setImg(payMentService.findShopImage(waxing.getShopId(), waxing.getProjectId()));
				} else {
					waxing.setImg(waxing.getImg());
				}

				payNow.setId(waxing.getId());
				payNow.setImg(waxing.getImg());
				payNow.setCarCount(1);
				payNow.setPayPrice(waxing.getWprice().intValue());
				payNow.setShopId(waxing.getShopId());
				payNow.setProductName(waxing.getTitle());
				payNow.setPrice(waxing.getWprice().intValue());
				payNow.setCarLevel(carLevel);
				List<PayNow> payNowList = new ArrayList<PayNow>();
				payNowList.add(payNow);
				writeValue(payNowList, code, null, 0, 0, "");
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void wxPay(){
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			/** 获取token*/
			HttpClient client = new HttpClient();
			GetMethod get = new GetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxafca0d58d2070337&secret=28da4ef919641858abe37dce4b1c54e9");
			client.executeMethod(get);
			String s = get.getResponseBodyAsString();
			JSONObject json = JSONObject.fromObject(s);
			String access_token = json.getString("access_token");
			
			
			/**生成预支付订单*/
			MtOrder mtOrder = payMentService.getWbOrder(orderNo);
			
			WxPayHelper wxPayHelper = new WxPayHelper();
			
			/** 先设置基本信息*/
			wxPayHelper.SetAppId("wxafca0d58d2070337");
			wxPayHelper.SetAppKey("iMWadSxWjDL9tK7wtiZfe1JexNvpTGxp0eUlWZhO8gIXnIMr4QYh5cM40onUP2uzf3g5JYUw8DUNCOovFETarNz72gipUg4Od8B00eLq0iAHm8ZcgnP19EarJ0Cy1ctj");
			wxPayHelper.SetPartnerKey("0d0c481b1cf1f229761071415c467464");
			wxPayHelper.SetSignType("sha1");
			/** 设置请求package信息*/
			wxPayHelper.SetParameter("bank_type", "WX");
			wxPayHelper.SetParameter("body", mtOrder.getOrderNo());
			wxPayHelper.SetParameter("partner", "1220000001");
			wxPayHelper.SetParameter("out_trade_no", mtOrder.getOrderNo());
			wxPayHelper.SetParameter("total_fee", mtOrder.getPayPrice().multiply(new BigDecimal(100))+"");
			wxPayHelper.SetParameter("fee_type", "1");
			wxPayHelper.SetParameter("notify_url", "http://www.180mi.com/wx/notify.dhtml");
			wxPayHelper.SetParameter("spbill_create_ip", request.getRemoteAddr());
			wxPayHelper.SetParameter("input_charset", "UTF-8");
			
			PostMethod post = new PostMethod("https://api.weixin.qq.com/pay/genprepay?access_token=" + access_token);
			post.setRequestBody(wxPayHelper.CreateAppPackage(orderNo));
			client.executeMethod(post);
			json = JSONObject.fromObject(post.getResponseBodyAsString());
			if(json.getString("errcode").equals("0")){
				String prepayid = json.getString("prepayid");
				/**生成支付数据包*/
				HashMap<String, String> nativeObj = new HashMap<String, String>();
				nativeObj.put("appid", "wxafca0d58d2070337");
				nativeObj.put("noncestr", CommonUtil.CreateNoncestr());
				nativeObj.put("package", "Sign=WXpay");
				nativeObj.put("partnerid", "1220000001");
				nativeObj.put("timestamp", Long.toString(new Date().getTime()/1000));
				nativeObj.put("prepayid", prepayid);
				nativeObj.put("sign", wxPayHelper.GetBizSign(nativeObj));
				out.write(JSONObject.fromObject(nativeObj).toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
		
	}
	
	/**
	 * 支付宝手机支付
	 * 
	 * @author Xuc
	 * @date 2014-10-27下午04:03:46
	 * @return
	 */
	public String aliPayMobile() {
		AlipayConfig.setKey(ServerInfo.getValue("aliKey"));
		AlipayConfig.setPartner(ServerInfo.getValue("aliPartner"));
		MtOrder mtOrder = payMentService.getWbOrder(orderNo);
		// 支付宝网关地址
		String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
		AlipaySubmit.setGateWay(ALIPAY_GATEWAY_NEW);
		// //////////////////////////////////调用授权接口alipay.wap.trade.create.direct获取授权码token//////////////////////////////////////

		// 返回格式
		String format = "xml";
		// 必填，不需要修改

		// 返回格式
		String v = "2.0";
		// 必填，不需要修改

		// 请求号
		String req_id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// 必填，须保证每次请求都是唯一

		// req_data详细信息

		// 服务器异步通知页面路径
		String notify_url = ServerInfo.getValue("aliNotify_url_wap");
		// 需http://格式的完整路径，不能加?id=123这类自定义参数

		// 页面跳转同步通知页面路径
		String call_back_url = ServerInfo.getValue("aliRetrun_url_wap");
		call_back_url = "http://192.168.20.213:8080/appservice/payment/alipayPageCallBack.dhtml";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		// 操作中断返回地址
		String merchant_url = "";
		// 用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数

		// 卖家支付宝帐户
		String seller_email = "54811824@qq.com";
		// 必填

		// 商户订单号
		String out_trade_no = mtOrder.getOrderNo();
		// 商户网站订单系统中唯一订单号，必填

		// 订单名称
		String subject = mtOrder.getOrderNo();
		// 必填

		// 付款金额
		String total_fee = mtOrder.getPayPrice() + "";
		total_fee = "0.01";
		// 必填

		// 请求业务参数详细
		String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>" + seller_email + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
		// 必填

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTempToken = new HashMap<String, String>();
		sParaTempToken.put("service", "alipay.wap.trade.create.direct");
		sParaTempToken.put("partner", AlipayConfig.partner);
		sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
		sParaTempToken.put("sec_id", AlipayConfig.sign_type);
		sParaTempToken.put("format", format);
		sParaTempToken.put("v", v);
		sParaTempToken.put("req_id", req_id);
		sParaTempToken.put("req_data", req_dataToken);

		// 建立请求
		String sHtmlTextToken;
		// 获取token
		String request_token = "";
		try {
			sHtmlTextToken = AlipaySubmit.buildRequest("", "", sParaTempToken);
			// URLDECODE返回的信息
			sHtmlTextToken = URLDecoder.decode(sHtmlTextToken, AlipayConfig.input_charset);
			request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// out.println(request_token);

		// //////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////

		// 业务详细
		String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
		// 必填

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("sec_id", AlipayConfig.sign_type);
		sParaTemp.put("format", format);
		sParaTemp.put("v", v);
		sParaTemp.put("req_data", req_data);

		// 建立请求
		alipayHtml = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		return Action.SUCCESS;
	}
	
	/**
	 * 支付宝网页支付同步跳转
	 * @return
	 */
	public String alipayPageCallBack(){
		return Action.SUCCESS;
	}
	
	/**
	 * 组装支付宝钱包支付数据串
	 */
	public void getAlipayInfo(){
		AlipayConfig.setKey(ServerInfo.getValue("aliKey"));
		AlipayConfig.setPartner(ServerInfo.getValue("aliPartner"));
		MtOrder order = payMentService.getWbOrder(orderNo);
		
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + AlipayConfig.partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"54811824@qq.com\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + order.getOrderNo() + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + order.getOrderNo() + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + order.getOrderNo() + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + "0.01" + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + ServerInfo.getValue("aliNotify_url_wap")
				+ "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";
		
		// 对订单做RSA 签名
		String sign = SignUtils.sign(orderInfo);;
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(orderInfo + "&sign=\"" + sign + "\"&sign_type=\"RSA\"");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public MtMinormt getMtMinormt() {
		return mtMinormt;
	}

	public void setMtMinormt(MtMinormt mtMinormt) {
		this.mtMinormt = mtMinormt;
	}

	public ShoppingCar getShoppingCar() {
		return shoppingCar;
	}

	public void setShoppingCar(ShoppingCar shoppingCar) {
		this.shoppingCar = shoppingCar;
	}
	
	public int getCarCount() {
		return carCount;
	}

	public void setCarCount(int carCount) {
		this.carCount = carCount;
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
	
	public List<ShoppingCar> getShoppingCarList() {
		return shoppingCarList;
	}

	public void setShoppingCarList(List<ShoppingCar> shoppingCarList) {
		this.shoppingCarList = shoppingCarList;
	}
	
	public List<Integer> getDelIds() {
		return delIds;
	}

	public void setDelIds(List<Integer> delIds) {
		this.delIds = delIds;
	}
	
	public List<Integer> getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(List<Integer> selectedIds) {
		this.selectedIds = selectedIds;
	}
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public PayNow getPayNow() {
		return payNow;
	}

	public void setPayNow(PayNow payNow) {
		this.payNow = payNow;
	}
	
	public int getCarLevel() {
		return carLevel;
	}

	public void setCarLevel(int carLevel) {
		this.carLevel = carLevel;
	}
	
	public MtCarwashcard getMc() {
		return mc;
	}

	public void setMc(MtCarwashcard mc) {
		this.mc = mc;
	}

	public MtGlossCoating getMtgloss() {
		return mtgloss;
	}

	public void setMtgloss(MtGlossCoating mtgloss) {
		this.mtgloss = mtgloss;
	}
	
	public MtWaxing getWaxing() {
		return waxing;
	}

	public void setWaxing(MtWaxing waxing) {
		this.waxing = waxing;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAlipayHtml() {
		return alipayHtml;
	}

	public void setAlipayHtml(String alipayHtml) {
		this.alipayHtml = alipayHtml;
	}
}

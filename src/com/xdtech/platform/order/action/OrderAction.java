package com.xdtech.platform.order.action;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.web.action.BaseAction;
import com.xdtech.platform.order.bean.ProjectReview;
import com.xdtech.platform.order.service.OrderService;

/**
 * @Description:app服务器端订单查询action
 * @author
 * @date 2015-3-23 上午10:07:41
 */
public class OrderAction extends BaseAction {
	@Resource
	private OrderService orderService;
	/**
	 * 分页开始
	 */
	private int start;
	/**
	 * 每页数目
	 */
	private int row;
	/**
	 * 状态标识(订单：0未支付，1已支付；评论：0未评论，1已评论)
	 */
	private int state;
	private String orderid;
	/**
	 * 评论
	 */
	private ProjectReview review;

	/**
	 * 订单列表(未支付、已支付)
	 */
	public void orderList() {
		List<HashMap<String, Object>> orderList = orderService.findOrderByUserId(currentGadUser.getId(), state, start, row);
		for (HashMap<String, Object> order : orderList) {
			List<HashMap<String, Object>> orderProList = orderService.findOrderProduct(order.get("orderId").toString());
			for (HashMap<String, Object> orderPro : orderProList) {
				String imgUrl = orderService.findProdImg((Integer) orderPro.get("projectId"), (Integer) orderPro.get("productId"));
				if (StringUtils.isNotEmpty(imgUrl)) {
					orderPro.put("image", imgUrl);
				}
			}
			order.put("orderProList", orderProList);
		}
		// int count = orderService.findOrderCount(orderState,
		// currentGadUser.getId());
		writeValue(orderList, ConstanData.SUCCESSCODE, null, 0);

	}

	/**
	 * 订单(已完成/已消费)
	 */
	public void orderCompletedList() {
		List<HashMap<String, Object>> orderCompletedList = orderService.findCompletedOrder(currentGadUser.getId(), start, row);
		writeValue(orderCompletedList, ConstanData.SUCCESSCODE, null, 0);
	}

	/**
	 * 评论
	 */
	public void commentList() {
		List<HashMap<String, Object>> commentList = orderService.commentList(currentGadUser.getId(), start, row, state);
		for (HashMap<String, Object> comment : commentList) {
			String imgUrl = orderService.findProdImg((Integer) comment.get("projectId"), (Integer) comment.get("productId"));
			if (StringUtils.isNotEmpty(imgUrl)) {
				comment.put("image", imgUrl);
			}
		}
		writeValue(commentList, ConstanData.SUCCESSCODE, null, 0);
	}

	/**
	 * 删除评论操作
	 */
	public void orderDeleteDo() {
		String result = orderService.orderOrder(currentGadUser.getId(), orderid);

		if (result != null) {
			writeValue(null, ConstanData.FAILURECODE, null, 0);
		} else {
			writeValue(null, ConstanData.SUCCESSCODE, null, 0);
		}

	}

	/**
	 * 用户评论
	 */
	public void comment() {
		boolean flag = orderService.comment(currentGadUser, review);
		if (flag) {
			writeValue(null, ConstanData.SUCCESSCODE, null, 0);
		} else {
			writeValue(null, ConstanData.FAILURECODE, null, 0);
		}
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

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public ProjectReview getReview() {
		return review;
	}

	public void setReview(ProjectReview review) {
		this.review = review;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}

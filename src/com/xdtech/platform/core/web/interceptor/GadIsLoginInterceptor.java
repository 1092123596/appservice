package com.xdtech.platform.core.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xdtech.platform.core.util.string.ConstantString;
import com.xdtech.platform.core.web.action.BaseAction;
import com.xdtech.platform.user.bean.User;

public class GadIsLoginInterceptor extends AbstractInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 89548185695123456L;

	/**
	 * 拦截Action处理的拦截方法
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的actionContext实例
		if (invocation.getInvocationContext().getSession().get(ConstantString.GadUser) == null) {
			return "noLogin";
		} else {
			BaseAction action = (BaseAction) invocation.getAction();
			action.setCurrentDad((User) invocation.getInvocationContext().getSession().get(ConstantString.GadUser));
			return invocation.invoke();
		}
	}

}

package com.xdtech.platform.core.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xdtech.platform.core.util.log.LogUtil;
/**
 * 拦截异常
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class XDExceptionInteceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation ai) {
		try {
			return ai.invoke();
		} catch (Exception e) {
			LogUtil.printStackTrace(e);
			return "error";
		}
	}
}

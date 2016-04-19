package com.xdtech.platform.core.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http请求接口
 * 
 * @author Administrator
 */
public interface ServletHandler {
	/**
	 * Request
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @throws Exception
	 */
	void setServletRequest(HttpServletRequest request) throws Exception;

	/**
	 * Response
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 */
	void setServletResponse(HttpServletResponse response) throws Exception;

	/**
	 * 操作内容描述
	 * @return 操作内容描述
	 */
	String getActionMessage();


}

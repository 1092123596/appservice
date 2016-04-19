package com.xdtech.platform.core.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.xdtech.platform.core.util.ContextUtil;
import com.xdtech.platform.core.util.string.ConstantString;

/**
 * 不允许直接访问jsp页面
 * 
 * @author Administrator
 */
public class JspFilter implements Filter {
	/**
	 * 允许访问的url
	 */
	private List<String> allowURIList = new ArrayList<String>();
	
	public void destroy() {

	}

	/**
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (allowURIList.contains(((HttpServletRequest) request).getRequestURI().replace(ContextUtil.getContextPath(), "")) || ((HttpServletRequest) request).getRequestURI().replace(ContextUtil.getContextPath(), "").equals("/")) {
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		chain.doFilter(request, response);
	}

	/**
	 * 
	 */
	public void init(FilterConfig config) throws ServletException {
		String allowURI = (config.getInitParameter(ConstantString.ALLOWURI) != null ? config.getInitParameter(ConstantString.ALLOWURI) : "");
		allowURIList = Arrays.asList(allowURI.split(";"));
	}

}

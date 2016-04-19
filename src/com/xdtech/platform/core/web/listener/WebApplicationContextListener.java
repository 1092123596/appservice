package com.xdtech.platform.core.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xdtech.platform.core.util.ContextUtil;
import com.xdtech.platform.core.util.SpringUtil;

/**
 * web应用初始化加载
 * 
 * @author Administrator
 */
public class WebApplicationContextListener implements ServletContextListener {

	/**
	 * 关闭
	 * 
	 * @param servletContextEvent
	 *            servletContextEvent
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

	/**
	 * 启动
	 * 
	 * @param servletContextEvent
	 *            servletContextEvent
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		SpringUtil.setWac(WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()));
		ContextUtil.setContextPath(servletContextEvent.getServletContext().getContextPath());
	}

}

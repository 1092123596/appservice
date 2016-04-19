package com.xdtech.platform.core.service;

import javax.annotation.Resource;

import com.xdtech.platform.core.dao.IDaoManager;

/**
 * service基类
 * 
 * @author Administrator
 */
public abstract class BaseService {

//	public BaseService() {
//		if (SpringUtil.getWebApplicationContext()!=null && SpringUtil.getWebApplicationContext().getBean("dao") != null) {
//			this.dao = (IDaoManager) SpringUtil.getWebApplicationContext().getBean("dao");
//		}
//	}

	/**
	 * 数据库操作 dao
	 */
	@SuppressWarnings("unchecked")
	@Resource
	protected IDaoManager dao;

	@SuppressWarnings("unchecked")
	public void setDao(IDaoManager dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	public IDaoManager getDao() {
		return dao;
	}

}

package com.xdtech.platform.core.util.string;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import com.xdtech.platform.core.util.ContextUtil;
import com.xdtech.platform.core.util.log.LogUtil;

/**
 * 初始化服务器信息
 * 
 * @author cuixy
 */
public class ServerInfo {
	/**
	 * 平台资源
	 */
	private static Properties props = new Properties();
	
	private static String defaultPath = ServerInfo.class.getClassLoader().getResource("../../").getPath();
	static {
		try {
			ResourceBundle paltformResourceBundle = ResourceBundle.getBundle("sysconfig/platform");
			Enumeration<String> keySet = paltformResourceBundle.getKeys();
			String key;
			while (keySet.hasMoreElements()) {
				key = keySet.nextElement();
				props.setProperty(key, paltformResourceBundle.getString(key));
			}
		} catch (Exception e) {
			LogUtil.error("读取系统平台配置文件sysconfig/platform.properties出错");
		}
	}

	/**
	 * 获得静态资源服务器路径
	 * 
	 * @return return
	 */
	public static String getStaticServer() {
		return props.getProperty(ConstantString.STATICSERVER) != null ? props.getProperty(ConstantString.STATICSERVER) : ContextUtil.getContextPath();
	}
	public static String getImageServer() {
		return props.getProperty(ConstantString.IMAGESERVER) != null ? props.getProperty(ConstantString.IMAGESERVER) : ContextUtil.getContextPath();
	}
	public static String getImageStore() {
		return props.getProperty(ConstantString.IMAGESTORE) != null ? props.getProperty(ConstantString.IMAGESTORE) : ContextUtil.getContextPath();
	}
	public static String getServerURL() {
		return props.getProperty(ConstantString.SERVERURL) != null ? props.getProperty(ConstantString.SERVERURL) : ContextUtil.getContextPath();
	}
	public static String getResourceServer() {
		return props.getProperty(ConstantString.RESOUCESERVER) != null ? props.getProperty(ConstantString.RESOUCESERVER) : ContextUtil.getContextPath()+"/admin/";
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}
	/**
	 * 返回html静态生成文件夹
	 * @return 
	 */
	public static String getOutHtmlFolder(){
		return props.getProperty(ConstantString.OUTHTML) != null ? props.getProperty(ConstantString.OUTHTML) : defaultPath;
	}
	
	/**
	 * 返回服务器统一字符集
	 * @return
	 */
	public static String getServerCharSet(){
		return "UTF-8";
	}
	
	/**
	 * 返回通用产品商家id
	 * @return
	 */
	public static int getTongYongShopId(){
		return Integer.parseInt(props.getProperty(ConstantString.ISTONGYONG)!=null?props.getProperty(ConstantString.ISTONGYONG):"0");
	}
	/**
	 * 图片路径
	 * @return
	 */
	public static String getImageUrl() {
		return props.getProperty(ConstantString.IMAGEURL) != null ? props.getProperty(ConstantString.IMAGEURL) : ContextUtil.getContextPath();
	}
	
	/**
	 * 大图片路径
	 * @author shendelei
	 * @date 2014-11-27 上午9:29:09
	 * @return
	 */
	public static String getBigImageUrl(){
		return props.getProperty(ConstantString.BIGIMAGEURL) != null ? props.getProperty(ConstantString.BIGIMAGEURL) : ContextUtil.getContextPath();
	}
}

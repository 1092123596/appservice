package com.xdtech.platform.core.util.string;


/**
 * 常用数字类
 * 
 * @author cuixy
 */
public class ConstanData {
	/**
	 * 数据删除的标识
	 */
	public static final int DATADELETED = 1;
	/**
	 * 数据有效的标识
	 */
	public static final int DATAVALID = 0;
	
	/**
	 * 中间标识
	 */
	public static final int MIDDLEID = 2;

	/**
	 * 启用
	 */
	public static final int USED = 1;

	/**
	 * 禁用
	 */
	public static final int UNUSED = 0;
	
	/**
	 * 服务器地址
	 */
	public static String SERVERURL = ServerInfo.getServerURL();
	/**
	 * 操作成功标识
	 */
	public static final int SUCCESSCODE = 200;
	/**
	 * 操作失败标识
	 */
	public static final int FAILURECODE = 300;
	/**
	 * 没有登录标识
	 */
	public static final int NOLOGINCODE = 301;
	
	
	
//	public static final String WEBDIR = ConstanData.class.getClassLoader().getResource("../../").getPath();
	public static final String WEBDIR = ConstanData.class.getClassLoader().getResource("../../").getPath() + ServerInfo.getImageStore();
	
	/**
	 * 通用产品商家Id
	 */
	public static final int TONGYONGSHOPID = ServerInfo.getTongYongShopId();
	
	/**
	 * ajax图片路径
	 */
	public static final String AJAXUPLOADIMG = WEBDIR+"image/ajaxUpload/";
}

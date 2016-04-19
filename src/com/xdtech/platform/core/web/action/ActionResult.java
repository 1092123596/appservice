package com.xdtech.platform.core.web.action;

/**
 * action操作结果
 * 
 * @author cuixy
 * @date 2012-02-27
 */
public class ActionResult {
	/**
	 * 状态
	 */
	private int statusCode;
	/**
	 * 消息
	 */
	private String message = "";
	/**
	 * 返回后要操作的tab的id
	 */
	private String navTabId = "";
	/**
	 * 要转向的url
	 */
	private String forwardUrl;
	/**
	 * 对tab进行的操作
	 */
	private String callbackType;
	
	private boolean Identification;
	
	private String id;
	
	/**
	* 构造函数
	 * @param statusCode	状态码
	 */
	public ActionResult(int statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * 构造函数
	 * @param statusCode 状态码
	 * @param message 消息
	 */
	public ActionResult(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	public boolean isIdentification() {
		return Identification;
	}

	public void setIdentification(boolean identification) {
		Identification = identification;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 构造函数
	 * @param Identification 手机邮件注册找回密码标识
	 * @param id 用户Id
	
	public ActionResult(boolean Identification, String id) {
		this.Identification = Identification;
		this.id = id;
	} */

	/**
	 * 构造函数
	 * @param statusCode	状态码
	 * @param message	消息
	 * @param navTabId	tabId
	 * @param forwardUrl	加载url
	 * @param callbackType	回调类型
	 */
	public ActionResult(int statusCode, String message, String navTabId, String forwardUrl, String callbackType) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.navTabId = navTabId;
		this.forwardUrl = forwardUrl;
		this.callbackType = callbackType;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

}

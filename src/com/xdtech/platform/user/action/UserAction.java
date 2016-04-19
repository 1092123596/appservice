package com.xdtech.platform.user.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.Action;
import com.xdtech.platform.core.util.MD5Util;
import com.xdtech.platform.core.util.SMSUtil;
import com.xdtech.platform.core.util.UploadUtil;
import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.util.string.ConstantString;
import com.xdtech.platform.core.web.action.ActionResult;
import com.xdtech.platform.core.web.action.BaseAction;
import com.xdtech.platform.personalcenter.service.MtorderService;
import com.xdtech.platform.user.bean.User;
import com.xdtech.platform.user.bean.UserCarInfo;
import com.xdtech.platform.user.service.UserService;

/**
 * 手机端用户操作
 * 
 * @author Administrator
 * 
 */
public class UserAction extends BaseAction {
	@Resource
	private UserService userService;
	@Resource
	private MtorderService mtorderservice;
	/**
	 * 用户实体
	 */
	private User user;
	/**
	 * 手机验证码
	 */
	private String rendscode;
	private String rendscodephone;
	private String rendscodephone1;
	private String userid;
	private String username;
	private String brandname;
	private int brandId;
	private int deptId;
	private String brandIds;
	private String deptIds;
	private String ids;
	private String deptname;
	private String password;
	public static Hashtable<String, String> ht = new Hashtable<String, String>();
	private List<UserCarInfo> userCarInfoList;

	/**
	 * 上传文件
	 */
	private File file;

	/**
	 * 上传文件名称
	 */
	private String fileName;

	/**
	 * 跳转用户登录页面
	 * 
	 * @return
	 */
	public String toUserLogin() {
		return Action.SUCCESS;
	}

	/**
	 * 退出登录
	 */
	public void logOut() {
		request.getSession().removeAttribute(ConstantString.GadUser);
	}

	/**
	 * 登录
	 */
	public void userLogin() {
		if (user != null) {
			user.setPassword(MD5Util.MD5(user.getPassword()));
			user = userService.getUserByNamePaw(user);
			ActionResult ar = new ActionResult(ConstanData.FAILURECODE, "账号或密码错误");
			if (user != null) {
				ar = new ActionResult(ConstanData.SUCCESSCODE);
				user.setLastTime(new Date());// 设置用户最后登录时间
				userService.updateUser(user);
				// writeValue(user, ConstanData.SUCCESSCODE, null);
			} else {
				// writeValue(user,ConstanData.FAILURECODE,"账号或密码错误");
			}
		}
	}

	/**
	 * 修改用户昵称
	 */
	public void editTrueName() {
		user = userService.getRegisterUser(userid);
		try {
			user.setTrueName(URLDecoder.decode(username, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		userService.updateUser(user);
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		writeValue(userList, ConstanData.SUCCESSCODE, request.getSession().getId(), 1);
	}

	/**
	 * 查找用户车型信息
	 * 
	 * @return
	 */
	public void userCarInfo() {
		user = userService.getUserByUsername(username);
		if (user != null) {
			if (username.equals(user.getUsername())) {
				List<HashMap> userlist = userService.getUser(username);
				writeValue(userlist, ConstanData.SUCCESSCODE, "[180迈]欢迎您,您已登录180迈手机客户端.", 1);
			}
		}

	}

	/**
	 * 手机用户登录
	 * 
	 * @return
	 */
	public void userPhoneLogin() {

		user = userService.getUserByUsername(username, password);
		if (user != null) {
			request.getSession().setAttribute(ConstantString.GadUser, user);
			User user1 = userService.getUserByUsername(username, password);
			user1.setPassword(null);
			List<User> userList = new ArrayList<User>();
			userList.add(user1);
			writeValue(userList, ConstanData.SUCCESSCODE, request.getSession().getId(), 1);
		} else {
			writeValue(null, ConstanData.FAILURECODE, "用户名或密码错误!!!", 0);
		}
	}

	/**
	 * 手机用户注册
	 * 
	 * @return
	 */
	public void userPhoneZhuCe() {
		user = userService.getUserByUsername(username);
		if (user != null) {
			writeValue(null, ConstanData.FAILURECODE, "[180迈]提醒您,您已注册过此号，请直接登录.", 0);
		} else {
			if (ht.containsKey(username) && ht.containsValue(rendscodephone.trim())) {
				User user = new User();
				user.setUsername(username);
				user.setTrueName(username);
				user.setContactNo(username);
				user.setPassword(rendscodephone1.trim());
				userService.saveUser(user);
				List<HashMap> userlist = userService.getUser(username);
				writeValue(userlist, ConstanData.SUCCESSCODE, request.getSession().getId(), 1);
			} else {
				writeValue(null, ConstanData.FAILURECODE, "很遗憾,您注册失败,请重新注册", 0);
			}
		}

	}

	/**
	 * 手机验证码发送,注册
	 * 
	 * @return
	 */
	public void checkResendCode() {
		user = userService.getUserByUsername(username);
		if (user != null) {
			writeValue(null, ConstanData.SUCCESSCODE, "[180迈]提醒您,您已注册过此号，请直接登录.", 0);
		} else {
			rendscode = String.valueOf(Math.abs(new Random().nextInt()) % 1000000);
			if (!StringUtils.isEmpty(rendscode)) {
				ht.put(username, rendscode);
				SMSUtil smsUtil = new SMSUtil();
				/*
				 * 给用户发短信
				 */
				String sendData = "为您提供您的手机验证密码是：" + rendscode + "";
				boolean resendcode = false;
				String sendCount = (String) request.getSession().getAttribute("sendCount");
				int count = StringUtils.isEmpty(sendCount) ? 0 : Integer.valueOf(sendCount);

				if (count < 5) {
					resendcode = smsUtil.sendSMStelePhone(new String[] { username }, sendData);
					if (count == 4) {
						request.getSession().setAttribute("lockTime", new Date().getTime() + "");
					}
					request.getSession().setAttribute("sendCount", ++count + "");
					writeValue(null, ConstanData.SUCCESSCODE, "手机验证密码已发送", 1);
				} else {
					String lockTime = (String) request.getSession().getAttribute("lockTime");
					long lock = Long.valueOf(lockTime);
					long cut = new Date().getTime() - lock;
					if (cut > 300000) {
						resendcode = smsUtil.sendSMStelePhone(new String[] { username }, sendData);
						request.getSession().setAttribute("lockTime", null);
						request.getSession().setAttribute("sendCount", "0");
						writeValue(null, ConstanData.SUCCESSCODE, "手机验证密码已发送", 1);
					} else {
						writeValue(null, ConstanData.FAILURECODE, "手机验证密码发送失败", 0);
					}
				}

			}
		}
	}

	/**
	 * 点击页面保存车辆信息
	 * */
	public void saveUserCarInfo() {
		userService.updateUserCarInfo(currentGadUser.getId());
		if (userCarInfoList != null && !userCarInfoList.isEmpty())
			try {
				userService.updateUserCarInfoByUserId(currentGadUser.getId(), userCarInfoList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		List<HashMap<String, String>> usercarinfolist = userService.getUCI(currentGadUser.getId());
		writeValue(usercarinfolist, ConstanData.SUCCESSCODE, "", 1, 0, request.getSession().getId());
	}

	/**
	 * 刷新选择品牌页面
	 * */
	public void flashStyle() {
		User user = (User) request.getSession().getAttribute(ConstantString.GadUser);
		saveUserCarInfo();
		List<HashMap<String, String>> usercarinfolist = userService.getUCI(userid);
		writeValue(usercarinfolist, ConstanData.SUCCESSCODE, "", 1, 0, request.getSession().getId());
	}

	/**
	 * 没有登录统一处理
	 * 
	 * @author shendelei
	 * @date 2015-4-7 下午1:52:31
	 */
	public void noLogin() {
		writeValue(null, ConstanData.NOLOGINCODE, "您没有登录，请登录后操作", 0);
	}

	/**
	 * 头像上传
	 * 
	 * @author shendelei
	 * @date 2015-4-7 下午1:52:52
	 */
	public void uploadImage() {
		String imagePath = null;
		if (file != null) {
			String path = ConstanData.AJAXUPLOADIMG;
			// 上传文件
			// uncompleted
			try {
				imagePath = UploadUtil.oneUpFiles(file, path, fileName);
				User user = userService.getRegisterUser(userid);
				user.setUserimg(imagePath);
				userService.updateUser(user);
				writeValue(null, ConstanData.SUCCESSCODE, imagePath, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void delUserCarInfo() {
		userService.delUserCarInfo(brandIds, deptIds, currentGadUser.getId());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRendscode() {
		return rendscode;
	}

	public void setRendscode(String rendscode) {
		this.rendscode = rendscode;
	}

	public String getRendscodephone() {
		return rendscodephone;
	}

	public void setRendscodephone(String rendscodephone) {
		this.rendscodephone = rendscodephone;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRendscodephone1() {
		return rendscodephone1;
	}

	public void setRendscodephone1(String rendscodephone1) {
		this.rendscodephone1 = rendscodephone1;
	}

	public List<UserCarInfo> getUserCarInfoList() {
		return userCarInfoList;
	}

	public void setUserCarInfoList(List<UserCarInfo> userCarInfoList) {
		this.userCarInfoList = userCarInfoList;
	}
}

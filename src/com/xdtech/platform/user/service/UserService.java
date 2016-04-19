package com.xdtech.platform.user.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xdtech.platform.core.service.BaseService;
import com.xdtech.platform.core.util.MD5Util;
import com.xdtech.platform.core.util.string.ConstanData;
import com.xdtech.platform.core.util.string.ConstantString;
import com.xdtech.platform.core.util.string.ValidateObject;
import com.xdtech.platform.user.bean.User;
import com.xdtech.platform.user.bean.UserCarInfo;

@Service
public class UserService extends BaseService {
	/**
	 *检查用户名唯一 返回结果提示
	 */
	private static final String USERNAME = "此账号已被注册";

	/**
	 * 判断用户名密码是否存在
	 * 
	 * @param user
	 * @return
	 */
	public User getUserByNamePaw(User registerUser) {
		User user = null;
		if (registerUser != null) {
			DetachedCriteria dc = DetachedCriteria.forClass(User.class);
			dc.add(Restrictions.eq(ConstantString.DELETED, ConstanData.DATAVALID));
			dc.add(Restrictions.eq("username", registerUser.getUsername()));
			dc.add(Restrictions.eq("password", registerUser.getPassword()));
			List<User> support = this.getDao().findAllByCriteria(dc);
			if (ValidateObject.hasValueInCollection(support)) {
				user = support.get(0);
			}
		}
		return user;
	}

	/**
	 * 判断用户名密码是否存在
	 * 
	 * @param RegisterUser
	 *            RegisterUser
	 * @return
	 */
	public User getUserByUsername(String username, String password) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if (ValidateObject.hasValue(username)) {
			dc.add(Restrictions.eq("username", username));
		}
		if (ValidateObject.hasValue(username)) {
			dc.add(Restrictions.eq("password", password));
		}
		List<User> orderList = this.getDao().findAllByCriteria(dc);
		User user = null;
		if (orderList != null && orderList.size() > 0) {
			user = orderList.get(0);
		}
		return user;
	}

	/**
	 * 判断用户名是否存在
	 * 
	 * @param RegisterUser
	 *            RegisterUser
	 * @return
	 */
	public User getUserByUsername(String username) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if (ValidateObject.hasValue(username)) {
			dc.add(Restrictions.eq("username", username));
		}
		List<User> orderList = this.getDao().findAllByCriteria(dc);
		User user = null;
		if (orderList != null && orderList.size() > 0) {
			user = orderList.get(0);
		}
		return user;
	}

	/**
	 * 判断用户名密码是否存在
	 * 
	 * @param RegisterUser
	 *            RegisterUser
	 * @return
	 */
	public List<HashMap> getUser(String username) {
		StringBuffer sql = new StringBuffer();
		// sql.append("select username,password from gc_user where username='"+username+"'");
		sql.append("select username as username,password as password,deptname as deptName,userid as id,brandname as brandName from gc_user g where username='" + username + "'");
		List<HashMap> RegisterUserList = this.getDao().getAllPreBySQL(sql.toString(), null);
		return RegisterUserList;
	}

	/**
	 * 保存用户车辆信息
	 * 
	 * @param user
	 */
	public void saveUserCarInfo(UserCarInfo uci) {
		this.getDao().saveIObject(uci);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 */
	public void updateUserCarInfo(UserCarInfo uci) {
		this.getDao().updateIObject(uci);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 */
	public void updateUser(User user) {
		this.getDao().updateIObject(user);
	}

	/**
	 * 保存用户信息
	 * 
	 * @param registerUser
	 * @return
	 */
	public String saveUser(User user) {
		String result = null;
		boolean unique = checkUnique(user.getUsername());
		if (unique) {
			dao.saveIObject(user);
		} else {
			result = USERNAME;
		}
		return result;
	}

	/**
	 * 根据id获取注册用户对象
	 * 
	 * @param id
	 *            参数id
	 * @return 注册用户对象
	 */
	@SuppressWarnings("unchecked")
	public User getRegisterUser(String id) {
		User rs = (User) dao.findIObjectByPK(User.class, id);
		return rs;
	}

	/**
	 * 验证用户名唯一
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkUnique(String name) {
		boolean flag = true;
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if (ValidateObject.hasValue(name)) {
			dc.add(Restrictions.eq("username", name));
		} else {
			return false;
		}
		dc.add(Restrictions.eq(ConstantString.DELETED, ConstanData.DATAVALID));
		synchronized (this) {
			// this 是当前对象。也就是我们在同步里提到的对象锁。其实synchronized（Object
			// o)这是块的同步，只要得到对象o,就可以开始这个代码块，而synchronized(this)是最泛得同步，只要有个对象就可以同步
			List<User> support = this.getDao().findAllByCriteria(dc);
			if (ValidateObject.hasValueInCollection(support)) {
				flag = false;// 用户名存在
			} else {
				flag = true;
			}
			support.clear();
			support = null;
		}
		dao.flushSession();
		return flag;
	}

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param data
	 * @return
	 */
	public User findUserByName(String userName) {
		User user = null;
		if (userName != null) {
			DetachedCriteria dc = DetachedCriteria.forClass(User.class);
			dc.add(Restrictions.eq(ConstantString.DELETED, ConstanData.DATAVALID));
			dc.add(Restrictions.eq("username", userName));
			List<User> support = this.getDao().findAllByCriteria(dc);
			if (ValidateObject.hasValueInCollection(support)) {
				user = support.get(0);
			}
		}
		return user;
	}

	/**
	 * 找回密码 手机
	 * 
	 * @param user
	 * @return
	 */
	public boolean resetPassword(User user) {
		boolean flag = false;
		if (user != null && ValidateObject.hasValue(user.getUsername()) && ValidateObject.hasValue(user.getPassword())) {
			StringBuilder hql = new StringBuilder();
			hql.append("update User set password = '").append(MD5Util.MD5(user.getPassword())).append("' where username = '").append(user.getUsername()).append("' and deleted=0");
			this.getDao().execByHQL(hql.toString());
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询车系
	 * 
	 * @author yuanqq
	 * @date 2015-3-27上午09:55:17
	 * @param brandid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserCarInfo> getUserCarInfo(String ids, String userid) {

		StringBuilder hql = new StringBuilder();
		List paramList = new ArrayList();
		hql.append("select * from gc_usercarinfo where userId is not null and ");
		if (null != userid) {
			hql.append(" userid='" + userid + "' and");
		}
		String[] checkValue = ids.split(",");
		for (int i = 0; i < checkValue.length; i++) {

			hql.append(" '" + checkValue[i] + "' or ");
			// sql.append(" and brandId='" + checkValue[i]+"' or ");
		}
		hql.append(" 1=2 ) ");
		return dao.findAllyHql(hql.toString(), paramList.toArray());

	}

	/**
	 * 查询车系
	 * 
	 * @author yuanqq
	 * @date 2015-3-27上午09:55:17
	 * @param brandid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getUCI(String userid) {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.deptName as deptName,a.brandName as brandName,b.*,(select logo from gc_vehiclebrand WHERE a.brandid = brandid) as logo from gc_usercarinfo b left join gc_vehicledept a on a.brandId = b.brandId AND a.deptId = b.deptId ");
		if (null != userid) {
			sql.append(" where b.userId='" + userid + "'");
		}
		List<HashMap<String, String>> UserCarInfoList = this.getDao().getAllPreBySQL(sql.toString(), null);
		return UserCarInfoList;

	}

	public List<HashMap> getUCI1(String brandids, String deptids, String userid) {

		StringBuilder sql = new StringBuilder();
		sql.append("select *,(select logo from gc_vehiclebrand WHERE a.brandid = brandid) as logo1 from gc_vehicledept as a where brandId!=0 ");
		String[] brandid = brandids.split(",");
		String[] deptid = deptids.split(",");
		for (int i = 0; i < brandid.length; i++) {

			sql.append(" and ( (brandId='" + brandid[i] + "' and deptId='" + deptid[i] + "' ) or ");
		}
		sql.append(" 1=2 ) ");
		List<HashMap> UserCarInfoList = this.getDao().getAllPreBySQL(sql.toString(), null);
		return UserCarInfoList;

	}

	public void updateUserCarInfo(String userId) {
		String sql = "update gc_usercarinfo set isSelected = 0 where userId = '" + userId + "'";
		this.getDao().executeUpdate(sql);
	}

	@SuppressWarnings("unchecked")
	public void updateUserCarInfoByUserId(String userId, List<UserCarInfo> userCarInfoList) {
		StringBuilder hql = new StringBuilder();
		hql.append("from UserCarInfo where userid='" + userId + "'");
		List<UserCarInfo> list = dao.findAllyHql(hql.toString(), null);
		this.getDao().execByHQL("update UserCarInfo set isSelected=0 where userid='" + userId + "'");
		for (UserCarInfo userCarInfo : userCarInfoList) {
			userCarInfo.setUserId(userId);
			boolean flag = true;
			for (UserCarInfo userCarInfo_1 : list) {
				if (userCarInfo.getDeptId().equals(userCarInfo_1.getDeptId())) {
					if (userCarInfo.getIsSelected() == 1) {
						userCarInfo_1.setIsSelected(1);
						this.dao.updateIObject(userCarInfo_1);
					}
					flag = false;
					break;
				}
			}
			if (flag)
				this.dao.saveOrUpdateIObject(userCarInfo);
		}
	}

	/**
	 * 删除我的车辆
	 * 
	 * @param id
	 */
	public void delUserCarInfo(String brandId, String deptId, String userid) {
		this.getDao().executeUpdate("delete from gc_usercarinfo where brandid='" + brandId + "' and deptid = '" + deptId + "' and userid='" + userid + "'");
	}
}

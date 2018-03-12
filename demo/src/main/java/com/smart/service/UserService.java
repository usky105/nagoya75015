package com.smart.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.smart.dao.hibernate.LoginLogHibernateDao;
import com.smart.dao.hibernate.UserHibernateDao;
import com.smart.domain.hibernate.LoginLog;
import com.smart.domain.hibernate.User;
import com.smart.exception.UserExistException;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	private UserHibernateDao userDao;
	private LoginLogHibernateDao loginLogDao;


	public boolean hasMatchUser(String userName, String password) {
		long matchCount =userDao.getMatchCount(userName, password);
		System.out.println(matchCount);
		return matchCount > 0;
	}
	
	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	@Transactional
    public void loginSuccess(User user) {
		user.setCredit( 5 + user.getCredit());
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
        userDao.updateUser(user);
        loginLogDao.addLoginLog(loginLog);
	}

	@Autowired
	public void setUserDao(UserHibernateDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setLoginLogDao(LoginLogHibernateDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}
	
	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * @param user 
	 */
	public void register(User user) throws UserExistException{
		User u = this.getUserByUserName(user.getUserName());
		if(u != null){
		    throw new UserExistException("用户名已经存在");
		}else{
		    user.setCredit(100);
            user.setUserType(1);
    			user.setLastVisit(new Date());
            userDao.save(user);
		}
	}
	
	/**
	  * 根据用户名/密码查询 User对象
	  * @param userName 用户名
	  * @return User
	  */
	 public User getUserByUserName(String userName){
	     return userDao.getUserByUserName(userName);
	 }
	 
	/**
	 * 获取所有用户
	 * @return 所有用户
	 */
	public List<User> getAllUsers(){
		return userDao.loadAll();
	}
	
	/**
     * 更新用户
     * @param user 
     */
    public void update(User user){
        userDao.update(user);
    }
}

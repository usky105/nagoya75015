package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.smart.dao.hibernate.LoginLogHibernateDao;
import com.smart.dao.hibernate.UserHibernateDao;
import com.smart.domain.hibernate.LoginLog;
import com.smart.domain.hibernate.User;
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
}

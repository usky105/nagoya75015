package com.smart.dao.hibernate;

import com.smart.domain.hibernate.User;
import com.smart.domain.hibernate.LoginLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.test.annotation.Rollback;

import static org.testng.Assert.*;

import java.util.Date;
import java.util.List;

@ContextConfiguration(locations = { "classpath:smart-dao-hbt.xml" })
@Transactional
public class TestAnnoHibernateDao extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private UserHibernateDao userDao;
	
	@Autowired
	private LoginLogHibernateDao loginLogDao;
	
	
	@Test
	@Rollback
	public void testAddUser() throws Throwable{
        User user = new User();
        user.setCredit(5);
        user.setUserName("usky");
        user.setPassword("123456");
        userDao.addUser(user);
	}	
	
	@Test
	@Rollback
	public void testUpdateUser() throws Throwable{
		User user = userDao.getUser(3);
        user.setCredit(7);
        user.setUserName("usky");
        user.setPassword("222222");
        user.setLastVisit(new Date());
        user.setLastIp("192.168.1.1");
        userDao.updateUser(user);
	}
	
	@Test
	public void testFindUserByName() {
       List<User> users = userDao.findUserByName("tom");
       Assert.assertTrue(users.size() > 0);
	}	
	
	@Test
	public void testgetUser() {
       User user = userDao.getUser(1);
       assertEquals(user.getUserName(), "tom");
	}
	
	@Test
	public void testgetUserNum() {
		Long num = userDao.getUserNum();
		System.out.println("testgetUserNum" + num);
		Assert.assertTrue(num > 0);
	}
	
	@Test
	public void testgetUserNum2() {
		Long num = userDao.getUserNum2();
		System.out.println(num);
		Assert.assertTrue(num > 0);
	}
	
	@Test
	@Rollback
	public void testAddLoginLog() throws Throwable{
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(1);
        loginLog.setIp("127.0.0.2");
        loginLog.setLoginDate(new Date());
        loginLogDao.addLoginLog(loginLog);  
	}	
	
	@Test
	public void testGetMatchCount() {
		long num = userDao.getMatchCount("tom", "1234");
		System.out.println(num);
		Assert.assertTrue(num > 0);
	}
}

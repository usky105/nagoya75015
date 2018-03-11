package com.smart.dao.hibernate;

import com.smart.domain.hibernate.User;
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

@ContextConfiguration(locations = { "classpath:applicationContext-hbt-anno.xml" })
@Transactional
public class TestAnnoHibernateDao extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private UserHibernateDao userDao;
	
	
	@Test
	@Rollback(value=false)
	public void testAddUser() throws Throwable{
        User user = new User();
        user.setCredits(5);
        user.setUserName("usky");
        user.setPassword("123456");
        userDao.addUser(user);
	}	
	
	@Test
	@Rollback(value=false)
	public void testUpdateUser() throws Throwable{
		User user = userDao.getUser(3);
        user.setCredits(7);
        user.setUserName("usky");
        user.setPassword("222222");
        user.setLastVisit(new Date());
        user.setLastIp("192.168.1.1");
        userDao.updateUser(user);
	}
	
	@Test
	public void testFindUserByName() {
       List<User> users = userDao.findUserByName("admin");
       Assert.assertTrue(users.size() > 0);
	}	
	
	@Test
	public void testgetUser() {
       User user = userDao.getUser(1);
       assertEquals(user.getUserName(), "admin");
	}
	
	@Test
	public void testgetUserNum() {
		Long num = userDao.getUserNum();
		System.out.println(num);
		Assert.assertTrue(num > 0);
	}
	
	@Test
	public void testgetUserNum2() {
		Long num = userDao.getUserNum2();
		System.out.println(num);
		Assert.assertTrue(num > 0);
	}
	
	
	
	
		
}

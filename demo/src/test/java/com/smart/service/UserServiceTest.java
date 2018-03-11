package com.smart.service;

import java.util.Date;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.smart.domain.hibernate.User;
import static org.testng.Assert.*;

@ContextConfiguration("classpath*:/applicationContext.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
    private UserService userService;

	@Test
	public void testHasMatchUser() {
		boolean b1 = userService.hasMatchUser("tom", "1234");
		boolean b2 = userService.hasMatchUser("admin", "1111");
		assertTrue(b1);
		assertTrue(!b2);
	}

	@Test
     public void testFindUserByUserName()throws Exception{
        for(int i =0; i< 100;i++) {
            User user = userService.findUserByUserName("tom");
            assertEquals(user.getUserName(), "tom");
        }

    }


	@Test
	public void testAddLoginLog() {
		User user = userService.findUserByUserName("tom");
		user.setUserId(1);
		user.setUserName("tom");
		user.setLastIp("192.168.12.7");
		user.setLastVisit(new Date());
		userService.loginSuccess(user);
	}
}


package com.smart.web;

import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smart.domain.hibernate.User;


import java.util.List;

/**
 *   论坛管理，这部分功能由论坛管理员操作，包括：创建论坛版块、指定论坛版块管理员、
 * 用户锁定/解锁。
 */
@Controller
public class ForumManageController extends BaseController {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 列出所有的论坛模块
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView listAllBoards() {
		ModelAndView view =new ModelAndView();

		view.setViewName("/listAllBoards");
		return view;
	}
	
	/**
	 * 用户锁定及解锁管理页面
	 * @return
	 */
	@RequestMapping(value = "/forum/userLockManagePage", method = RequestMethod.GET)
	public ModelAndView userLockManagePage() {
		ModelAndView view =new ModelAndView();
		List<User> users = userService.getAllUsers();
		view.setViewName("/userLockManage");
		view.addObject("users", users);
		return view;
	}
	
	/**
	 * 用户锁定及解锁设定
	 * @return
	 */
	@RequestMapping(value = "/forum/userLockManage", method = RequestMethod.POST)
	public ModelAndView userLockManage(@RequestParam("userName") String userName
			,@RequestParam("locked") String locked) {
		ModelAndView view =new ModelAndView();
        User user = userService.getUserByUserName(userName);
		if (user == null) {
			view.addObject("errorMsg", "用户名(" + userName
					+ ")不存在");
			view.setViewName("/fail");
		} else {
			user.setLocked(Integer.parseInt(locked));
			userService.update(user);
			view.setViewName("/success");
		}
		return view;
	}

}

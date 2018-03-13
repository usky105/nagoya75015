/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smart.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smart.cons.CommonConstant;
import com.smart.domain.hibernate.Board;
import com.smart.domain.hibernate.User;
import com.smart.service.ForumService;

/**
 *   这个Action负责处理论坛普通操作功能的请求，包括：显示论坛版块列表、显示论坛版块主题列表、
 * 表主题帖、回复帖子、删除帖子、设置精华帖子等操作。
 */
@Controller
public class BoardManageController extends BaseController {

	private ForumService forumService;

	@Autowired
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	/**
	 * 删除版块
	 */
	@RequestMapping(value = "/board/removeBoard", method = RequestMethod.GET)
	public String removeBoard(@RequestParam("boardIds") String boardIds) {
		String[] arrIds = boardIds.split(",");
		for (int i = 0; i < arrIds.length; i++) {
			forumService.removeBoard(new Integer(arrIds[i]));
		}
		String targetUrl = "/index.html";
		return "redirect:"+targetUrl;
	}
	

}

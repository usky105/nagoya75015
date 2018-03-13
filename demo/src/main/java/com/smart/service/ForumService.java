package com.smart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.hibernate.*;
import com.smart.domain.hibernate.*;

/**
 * 论坛管理服务类，对论坛版块、话题、帖子进行管理
 */
@Service
public class ForumService {

	private BoardDao boardDao;

	@Autowired
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	/**
	 * 获取所有的论坛版块
	 * 
	 * @return
	 */
	public List<Board> getAllBoards() {
		return boardDao.loadAll();
	}
	
	/**
	 * 根据boardId获取Board对象
	 * 
	 * @param boardId
	 */
	public Board getBoardById(int boardId) {
		return boardDao.get(boardId);
	}
	
	/**
	 * 创建一个新的论坛版块
	 * 
	 * @param board
	 */
	public void addBoard(Board board) {
		boardDao.save(board);
	}
	
	/**
	 * 删除一个版块
	 * @param boardId
	 */
	public void removeBoard(int boardId){
		Board board = boardDao.get(boardId);
		boardDao.remove(board);
	}
	
	

}

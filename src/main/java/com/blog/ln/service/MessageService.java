package com.blog.ln.service;

import java.util.List;

import com.blog.ln.bean.MessageInfo;

/**
 * 留言管理
 * @author Administrator
 *
 */
public interface MessageService {

	/**
	 * 查询留言信息
	 * @param info
	 * @return
	 */
	public List<MessageInfo> getList(MessageInfo info);
	
	/**
	 * 修改在线留言信息
	 * @param info
	 * @return
	 */
	public boolean update(MessageInfo info);
	
	
	/**
	 * 根据编号删除留言信息
	 * @param messageId
	 * @return
	 */
	public boolean delete(Integer messageId);
	
	/**
	 * 查询需要显示的留言信息
	 * @return
	 */
	 public List<MessageInfo> getMessagesList();
	 
	 /**
	  * 添加留言信息
	  * @param info
	  * @return
	  */
	 public boolean add(MessageInfo info);
	
}

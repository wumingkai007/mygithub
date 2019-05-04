package com.blog.ln.service;

import com.blog.ln.bean.UserInfo;
import com.blog.ln.utils.PageBean;

/**
 * 用户管理的业务逻辑接口
 * @author Administrator
 *
 */
public interface UserInfoService {
	
	/**
	 * 根据分页条件查询用户信息
	 * @param user 查询条件
	 * @param page 页数
	 * @return
	 */
	public PageBean<UserInfo> getList(UserInfo user,Integer page);
	
	/**
	 * 添加用户信息
	 * @param user 用户信息
	 * @return
	 */
	public boolean add(UserInfo user);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public boolean update(UserInfo user);
	
	/**
	 * 删除用户信息
	 * @param user
	 * @return
	 */
	public boolean delete(UserInfo user);
	
	/**
	 * 根据条件查询用户信息
	 * @param user
	 * @return
	 */
	public UserInfo getUser(UserInfo user);
	
	/**
	 * 根据账号和密码查询用户信息
	 * @param user
	 * @return
	 */
	public UserInfo isLogin(UserInfo user);
	
	/**
	 * 获取用户的总数
	 * @return
	 */
	public int getCount();
	
}

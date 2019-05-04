package com.blog.ln.service;

import java.util.List;

import com.blog.ln.bean.CategoryInfo;

/**
 * 栏目管理的业务逻辑接口
 * @author Administrator
 *
 */
public interface CategoryInfoService {

	/**
	 * 查询所有的栏目信息
	 * @return
	 */
	
	public List<CategoryInfo> list(CategoryInfo info);
	
	/**
	 * 添加栏目信息
	 * @param info
	 * @return
	 */
	public boolean add(CategoryInfo info);
	
	/**
	 * 根据编号查询栏目信息
	 * @param categoryId
	 * @return
	 */
	public CategoryInfo getCategoryInfo(Integer categoryId);
	
	/**
	 * 修改栏目信息
	 * @param info
	 * @return
	 */
	public boolean update(CategoryInfo info);
	
	/**
	 * 根据栏目的编号删除栏目以及这个栏目下所有的文章
	 * @param categoryId
	 */
	public void delete(Integer categoryId)throws Exception;
	

}

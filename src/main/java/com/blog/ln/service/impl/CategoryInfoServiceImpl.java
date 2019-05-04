package com.blog.ln.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ln.bean.CategoryInfo;
import com.blog.ln.dao.ArticleInfoMapper;
import com.blog.ln.dao.CategoryInfoMapper;
import com.blog.ln.service.CategoryInfoService;

@Service
public class CategoryInfoServiceImpl implements CategoryInfoService{

	/**
	 * 栏目管理的mapper
	 */
	@Autowired
	private CategoryInfoMapper catemapper;
	
	/**
	 * 文章管理的Mapper
	 */
	@Autowired
	private ArticleInfoMapper artmapper;
	

	//@Cacheable(cacheNames="liu",key="#p0")
	public List<CategoryInfo> list(CategoryInfo info) {
		
		return catemapper.getCateList();
	}

	@Override
	//@CacheEvict(cacheNames="liu",allEntries=true)
	public boolean add(CategoryInfo info) {
		try {
			int count = catemapper.insertSelective(info);
			if(count>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override

	public CategoryInfo getCategoryInfo(Integer categoryId) {
		
		try {
			
			return catemapper.selectByPrimaryKey(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	//@CacheEvict(cacheNames="liu",allEntries=true)
	public boolean update(CategoryInfo info) {
		try {
			int count = catemapper.updateByPrimaryKeySelective(info);
			if(count>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional //事物处理
	//@CacheEvict(cacheNames="liu",allEntries=true)
	public void delete(Integer categoryId)throws Exception {
		
		//先删除文章信息
		artmapper.deleteCategoryId(categoryId);
		//在删除栏目信息
		catemapper.deleteByPrimaryKey(categoryId);
		
	}

}

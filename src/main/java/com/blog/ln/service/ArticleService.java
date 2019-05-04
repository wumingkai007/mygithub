package com.blog.ln.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.utils.PageBean;

/**
 * 文章管理的业务逻辑接口
 * @author Administrator
 *
 */
public interface ArticleService {

	/**
	 * 添加文章信息
	 * @param article
	 * @return
	 */
	public boolean add(ArticleInfo article);
	
	/**
	 * 文章修改
	 * @param article
	 * @return
	 */
	public boolean update(ArticleInfo article);
	
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	public String doPutFile(MultipartFile file) ;
	
	/**
	 * 根据条件查询文章信息
	 * @param artinfo
	 * @param page
	 * @return
	 */
	public PageBean<ArticleInfo> getList(ArticleInfo artinfo,Integer page);
	
	/**
	 * 根据编号查询文章信息
	 * @param artinfo
	 * @return
	 */
	public ArticleInfo getArticleInfo(ArticleInfo artinfo);
	
	/**
	 * 根据文章编号删除文章信息
	 * @param articleId
	 * @return
	 */
	public boolean delete(Integer articleId);
	
	/**
	 * 查询最新15条记录信息
	 * @return
	 */
	public List<ArticleInfo> getNewList();
	
	/**
	 * 查询站长推荐的10条记录信息
	 * @return
	 */
	public List<ArticleInfo> getArtList();
	
	
}

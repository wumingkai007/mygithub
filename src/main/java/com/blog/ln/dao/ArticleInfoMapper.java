package com.blog.ln.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.ln.bean.ArticleInfo;

/**
 * 文章管理
 * @author Administrator
 *
 */
@Mapper
public interface ArticleInfoMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    ArticleInfo selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);
    
    /**
     * 根据栏目编号删除文章信息
     * @param categoryId
     * @return
     */
    public int deleteCategoryId(Integer categoryId);
    
    /**
     * 根据条件查询文章信息
     * @param info
     * @return
     */
    public List<ArticleInfo> getArticleList(ArticleInfo info);
    
    /**
     * 文章的总数
     * @param info
     * @return
     */
    public Long getArticleCount(ArticleInfo info);
}
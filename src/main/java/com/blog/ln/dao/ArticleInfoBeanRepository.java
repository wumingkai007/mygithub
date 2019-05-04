package com.blog.ln.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.blog.ln.bean.ArticleInfoBean;

@Component
public interface ArticleInfoBeanRepository  extends ElasticsearchRepository<ArticleInfoBean,Integer>{

	Page<ArticleInfoBean> findDistinctArticleInfoBeanByArticleTitleContainingOrArticleContentContaining(String articleTitle,String articleContent,Pageable page);
}

package com.blog.ln.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.bean.ArticleInfoBean;
import com.blog.ln.dao.ArticleInfoBeanRepository;
import com.blog.ln.dao.ArticleInfoMapper;
import com.blog.ln.service.ArticleService;
import com.blog.ln.utils.Const;
import com.blog.ln.utils.PageBean;
import com.blog.ln.utils.PageUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleInfoMapper artmapper;

	@Autowired
	private ArticleInfoBeanRepository er;
	
	@Override
	public boolean add(ArticleInfo article) {

		try {

			int count = artmapper.insertSelective(article);
			System.out.println(article.getArticleId());
			//保存到es中
			ArticleInfoBean aib = new ArticleInfoBean();
			aib.setArticleId(article.getArticleId());
			aib.setArticleTitle(article.getArticleTitle());
			aib.setArticleContent(article.getArticleContent());
			aib.setArticleImg(article.getArticleImg());
			aib.setArticleTime(article.getArticleTime());
			er.save(aib);  
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public String doPutFile(MultipartFile file) {

		try {
			// 图片名称
			String filename = file.getOriginalFilename();

			// 当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String format = sdf.format(new Date());

			String url = Const.FILE_URL + format + filename;
			System.out.println(url);
			// jersey 客户端
			Client client = new Client();
			WebResource resource = client.resource(url);

			// 将文件转为byte
			byte[] buf = file.getBytes();
			resource.put(String.class, buf);

			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PageBean<ArticleInfo> getList(ArticleInfo artinfo, Integer page) {

		// 总记录数
		int allRow = artmapper.getArticleCount(artinfo).intValue();
		// 总页数
		int totalPage = PageUtil.countTotalPage(allRow, Const.PAGE_SIZE);
		// 当前第几页
		int currentPage = PageUtil.countCurrentPage(page);
		// 起始记录数
		int start = PageUtil.countStart(Const.PAGE_SIZE, currentPage);

		if (page >= 0) {
			artinfo.setStart(start);
			artinfo.setLength(Const.PAGE_SIZE);
		} else {
			artinfo.setStart(-1);
			artinfo.setLength(-1);
		}

		List<ArticleInfo> arts = artmapper.getArticleList(artinfo);

		PageBean<ArticleInfo> pageBean = new PageBean<>();
		pageBean.setAllRow(allRow);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(arts);

		return pageBean;
	}

	@Override
	public ArticleInfo getArticleInfo(ArticleInfo artinfo) {

		try {
			return artmapper.selectByPrimaryKey(artinfo.getArticleId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public boolean update(ArticleInfo article) {
		try {

			int count = artmapper.updateByPrimaryKeySelective(article);
			
			//修改
			ArticleInfoBean aib = new ArticleInfoBean();
			aib.setArticleId(article.getArticleId());
			aib.setArticleTitle(article.getArticleTitle());
			aib.setArticleContent(article.getArticleContent());
			aib.setArticleImg(article.getArticleImg());
			aib.setArticleTime(article.getArticleTime());
			er.save(aib);  
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer articleId) {
		try {
			int count = artmapper.deleteByPrimaryKey(articleId);
			//删除
			ArticleInfoBean aib = new ArticleInfoBean();
			aib.setArticleId(articleId);
			er.delete(aib);  
			if(count>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ArticleInfo> getNewList() {
		
		ArticleInfo info = new ArticleInfo();
		info.setArticleMark(Const.MARK_YES);
		info.setStart(0);
		info.setLength(15);
	
		List<ArticleInfo> list = artmapper.getArticleList(info);
		
		return list;
	}

	@Override
	public List<ArticleInfo> getArtList() {
		
		ArticleInfo info = new ArticleInfo();
		info.setArticleRecom(Const.MARK_YES);
		info.setArticleMark(Const.MARK_YES);
		info.setStart(0);
		info.setLength(10);
	
		List<ArticleInfo> list = artmapper.getArticleList(info);
		
		return list;
	}

}

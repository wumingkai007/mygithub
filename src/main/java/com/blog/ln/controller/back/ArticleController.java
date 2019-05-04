package com.blog.ln.controller.back;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.bean.CategoryInfo;
import com.blog.ln.bean.UserInfo;
import com.blog.ln.service.ArticleService;
import com.blog.ln.service.CategoryInfoService;
import com.blog.ln.utils.PageBean;

/**
 * 文章管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/article/")
public class ArticleController {

	/**
	 * 栏目管理的业务逻辑接口
	 */
	@Autowired
	private CategoryInfoService cateservice;
	
	/**
	 * 文章管理的业务逻辑接口
	 */
	@Autowired
	private ArticleService artService;
	
	/**
	 * 查询文章信息
	 * @param info
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("list")
	public String list(ArticleInfo info,Model model,Integer page) {
		PageBean<ArticleInfo> pageBean = artService.getList(info, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("artinfo", info);
		
		CategoryInfo cateinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(cateinfo);
		model.addAttribute("catelist", catelist);
		
		return "back/article/article_list";
	}
	
	/**
	 * 加载文章添加页面
	 * @return
	 */
	@RequestMapping("loadadd")
	public String loadadd(Model model) {
		CategoryInfo cateinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(cateinfo);
		model.addAttribute("catelist", catelist);
		
		
		return "back/article/article_add";
	}
	
	/**
	 * 添加文章信息
	 * @param article
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	public String add(ArticleInfo article,Model model,HttpSession session) {
		
		UserInfo user = (UserInfo)session.getAttribute("userinfo");
		if(user!=null) {
			article.setUserId(user.getUserId());
			article.setArticleTime(new Date());
		}
		
		boolean mark = artService.add(article);
		if(mark) {
			model.addAttribute("info", "添加文章成功");
		}else {
			model.addAttribute("info", "添加文章失败");
		}
		
		CategoryInfo cateinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(cateinfo);
		model.addAttribute("catelist", catelist);
		
		return "back/article/article_add";
	}
	
	/**
	 * 上传图片
	 * @param upload
	 * @return
	 */
	@RequestMapping("upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile upload) {
		String url = artService.doPutFile(upload);
		
		return url;
	}
	
	/**
	 * 在线文本编辑器上传图片（富文本编辑）
	 * @param upload
	 * @param request
	 * @param response
	 */
	@RequestMapping("uploadfile")
	public void uploadfile(@RequestParam MultipartFile upload,HttpServletRequest request,HttpServletResponse response) {
		
		try {
			String url = artService.doPutFile(upload);
			PrintWriter out = response.getWriter();
			String callBack = request.getParameter("CKEditorFuncNum");
			out.println("<script>window.parent.CKEDITOR.tools.callFunction(" + callBack + ",'" + url + "')</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 加载修改页面
	 * @param article
	 * @param model
	 * @return
	 */
	@RequestMapping("loadupdate")
	public String loadupdate(ArticleInfo article,Model model) {
		
		ArticleInfo artinfo = artService.getArticleInfo(article);
		model.addAttribute("artinfo", artinfo);
		
		CategoryInfo cateinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(cateinfo);
		model.addAttribute("catelist", catelist);
		
		return "back/article/article_update";
	}
	
	/**
	 * 添加文章信息
	 * @param article
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(ArticleInfo article,Model model,HttpSession session) {
		
		UserInfo user = (UserInfo)session.getAttribute("userinfo");
		if(user!=null) {
			article.setUserId(user.getUserId());
			article.setArticleTime(new Date());
		}
		
		boolean mark = artService.update(article);
		if(mark) {
			model.addAttribute("info", "修改文章成功");
		}else {
			model.addAttribute("info", "修改文章失败");
		}
		
		CategoryInfo cateinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(cateinfo);
		model.addAttribute("catelist", catelist);
		
		model.addAttribute("artinfo", article);
		
		return "back/article/article_update";
	}
	
	/**
	 * 根据编号删除文章信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id,Model model) {
		boolean mark = artService.delete(id);
		if(mark) {
			model.addAttribute("info", "删除文章成功");
		}else {
			model.addAttribute("info", "删除文章失败");
		}
		return "back/article/article_info";
	}
}

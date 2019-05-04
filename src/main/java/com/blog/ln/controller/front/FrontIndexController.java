package com.blog.ln.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.bean.ArticleInfoBean;
import com.blog.ln.bean.CategoryInfo;
import com.blog.ln.bean.MessageInfo;
import com.blog.ln.dao.ArticleInfoBeanRepository;
import com.blog.ln.service.ArticleService;
import com.blog.ln.service.CategoryInfoService;
import com.blog.ln.service.MessageService;
import com.blog.ln.utils.PageBean;

/**
 * 前台展示的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/")
public class FrontIndexController {

	/**
	 * 栏目管理的业务逻辑接口
	 */
	@Autowired
	private CategoryInfoService cateService;
	
	/**
	 * 文章管理的业务逻辑接口
	 */
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 留言管理的业务逻辑接口
	 */
	@Autowired
	private MessageService messageService;
	
	
	@Autowired
	private ArticleInfoBeanRepository er;
	
	/**
	 * 全文搜索
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping("es")
	public String getlist(String name,Model model){
		Pageable page = new PageRequest(0, 50);
		Page<ArticleInfoBean> pages = er.findDistinctArticleInfoBeanByArticleTitleContainingOrArticleContentContaining(name, name, page);
		List<ArticleInfoBean> list = pages.getContent();
		model.addAttribute("eslist", list);
		init(model);
		
		//查询站长推荐10条记录
		List<ArticleInfo> rlist = articleService.getArtList();
		model.addAttribute("rlist", rlist);
		
		return "es";
	}
	
	/**
	 * 初始化
	 * @param model
	 */
	public void init(Model model) {
		CategoryInfo info  = new CategoryInfo();
		List<CategoryInfo> catelist = cateService.list(info);
		model.addAttribute("catelist", catelist);
		
		//查询站长推荐10条记录
		List<ArticleInfo> rlist = articleService.getArtList();
		model.addAttribute("rlist", rlist);
	}
	
	/**
	 * 首页展示信息
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		init(model);
		
		//查询最新的15条文章信息
		List<ArticleInfo> artlist = articleService.getNewList();
		model.addAttribute("artlist", artlist);
		

		
		
		return "index";
	}
	
	/**
	 * 查询详情的文章
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("listview/{id}")
	public String listView(@PathVariable("id") Integer id,Model model) {
		init(model);
		ArticleInfo artinfo = new ArticleInfo();
		artinfo.setArticleId(id);
		artinfo = articleService.getArticleInfo(artinfo);
		
		model.addAttribute("artinfo", artinfo);
		
		
		return "listview";
	}
	
	/**
	 * 根据栏目的类别查询文章信息
	 * @param info
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("list")
	public String list(ArticleInfo info,Model model,Integer page) {
		
		init(model);
		
		PageBean<ArticleInfo> pageBean = articleService.getList(info, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("artinfo", info);
		
		return "list";
	}
	
	/**
	 * 查询留言信息
	 * @param model
	 * @return
	 */
	@RequestMapping("message")
	public String message(Model model) {
		init(model);
		List<MessageInfo> melist= messageService.getMessagesList();
		model.addAttribute("melist", melist);
		
		return "message";
	}
	
	/**
	 * 添加留言信息
	 * @param info
	 * @param model
	 * @return
	 */
	@RequestMapping("addmessage")
	public String addMessage(MessageInfo info,Model model) {
		init(model);
		
		boolean mark = messageService.add(info);
		if(mark) {
			
			model.addAttribute("meinfo", "留言成功");
		}else {
			model.addAttribute("meinfo", "留言失败");
		}
		
		List<MessageInfo> melist= messageService.getMessagesList();
		model.addAttribute("melist", melist);
		
		return "/message_info";
	}
	
}

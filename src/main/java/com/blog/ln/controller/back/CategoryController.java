package com.blog.ln.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.ln.bean.CategoryInfo;
import com.blog.ln.service.CategoryInfoService;

/**
 * 栏目管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/category/")
public class CategoryController {
	
	@Autowired
	private CategoryInfoService cateService;
	
	/**
	 * 查询所有的栏目信息
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model,CategoryInfo info) {
		
		List<CategoryInfo> catelist = cateService.list(info);
		model.addAttribute("catelist", catelist);
		
		return "back/category/category";
	}
	
	/**
	 * 添加栏目信息
	 * @param info
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	public String add(CategoryInfo info,Model model) {
		
		boolean mark = cateService.add(info);
		if(mark) {
			model.addAttribute("info", "添加栏目信息成功");
		}else {
			model.addAttribute("info", "添加栏目信息失败");
		}
		
		return "back/category/category_info";
	}
	
	/**
	 * 根据编号查询栏目信息
	 * @param model
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("get/{id}")
	public String loadupdate(Model model,@PathVariable("id") Integer categoryId) {
		
		CategoryInfo cateinfo = cateService.getCategoryInfo(categoryId);
		model.addAttribute("cateinfo", cateinfo);
		
		return "back/category/category_update";
	}
	
	/**
	 * 修改栏目信息
	 * @param info
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(CategoryInfo info,Model model) {
		
		boolean mark = cateService.update(info);
		if(mark) {
			model.addAttribute("info", "修改栏目信息成功");
		}else {
			model.addAttribute("info", "修改栏目信息失败");
		}
		
		model.addAttribute("cateinfo", info);
		
		return "back/category/category_update";
	}
	
	@RequestMapping("/del/{id}")
	public String delete(@PathVariable("id") Integer categoryId,Model model) {
		
		try {
			cateService.delete(categoryId);
			model.addAttribute("info", "删除栏目信息成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("info", "删除栏目信息失败");
		}
		
		return "back/category/category_info";
	}

}

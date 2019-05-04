package com.blog.ln.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.ln.bean.UserInfo;
import com.blog.ln.service.UserInfoService;
import com.blog.ln.utils.PageBean;

/**
 * 用户管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/user/")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userservcie;

	/**
	 * 根据分页查询用户信息
	 * @param user
	 * @param model
	 * @param page 
	 * @return
	 */
	@RequestMapping("list")
	public String list(UserInfo user,Model model,Integer page) {
		
		PageBean<UserInfo> pageBean = userservcie.getList(user, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("userinfo", user);
		
		
		return "back/userinfo/userinfo_list";
	}
	
	/**
	 * 加载添加页面
	 * @return
	 */
	@RequestMapping("loadAdd")
	public String loadAdd() {
		
		return "back/userinfo/userinfo_add";
	}
	
	/**
	 * 添加用户信息
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	public String add(UserInfo user,Model model) {
		
		boolean mark = userservcie.add(user);
		if(mark) {
			model.addAttribute("info", "添加用户信息成功");
		}else {
			model.addAttribute("info", "添加用户信息失败");
		}
		
		
		return "back/userinfo/userinfo_add";
	}
	
	
	/**
	 * 加载修改页面
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("loadUpdate")
	public String loadUpdate(UserInfo user,Model model) {
		
		UserInfo userinfo = userservcie.getUser(user);
		model.addAttribute("user", userinfo);
		
		return "back/userinfo/userinfo_update";
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(UserInfo user,Model model) {
		
		boolean mark = userservcie.update(user);
		if(mark) {
			model.addAttribute("info", "修改用户信息成功");
		}else {
			model.addAttribute("info", "修改用户信息失败");
		}
		
		model.addAttribute("user", user);
		return "back/userinfo/userinfo_update";
	}
	
	
	/**
	 * 删除用户
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(UserInfo user) {
		userservcie.delete(user);

		return "redirect:/back/user/list?page=1";
	}
}

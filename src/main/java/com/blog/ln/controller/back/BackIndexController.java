package com.blog.ln.controller.back;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.ln.bean.UserInfo;
import com.blog.ln.service.UserInfoService;

@Controller
@RequestMapping("/back/")
public class BackIndexController {

	/**
	 * 用户管理的业务逻辑接口
	 */
	@Autowired
	private UserInfoService userservice;
	
	@RequestMapping("login")
	public String login(HttpSession session) {
		
		//清除Session
		session.invalidate();
		 
		return "back/login";
	}
	
	@RequestMapping("index")
	public String index() {
		return "back/index";
	}
	
	@RequestMapping("main")
	public String main(Model model,HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		model.addAttribute("ip", ip);
		
	
		
		model.addAttribute("usercount", userservice.getCount());
		
		return "back/main";
	}
	
	/**
	 * 用户登录
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("userlogin")
	public String userlogin(UserInfo user,Model model,HttpSession session) {
		
		UserInfo userInfo = userservice.isLogin(user);
		if(userInfo!=null) {
			//需要把当前登录用户存到session中
			session.setAttribute("userinfo", userInfo);
			
			//获取登录时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			session.setAttribute("logintime", sdf.format(date));
			return "back/index";
		}else {
			model.addAttribute("info", "账号或密码错误!");
			return "back/login";
		}
		
	}
	
}

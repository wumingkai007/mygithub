package com.blog.ln.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.blog.ln.bean.UserInfo;

@WebFilter("/back/*")
public class LoginFilter implements Filter {

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//获取HttpServletRequest
		HttpServletRequest hreq = (HttpServletRequest)request;
		String uri = hreq.getRequestURI();
		
		//从HttpSession 中获取用户信息userinfo
		HttpSession session = hreq.getSession();
		UserInfo user = (UserInfo)session.getAttribute("userinfo");
		
		if(uri.contains("/userlogin")) {
			chain.doFilter(request, response);
		}else if(user!=null) {
			chain.doFilter(request, response);
		}else {
			hreq.getRequestDispatcher("/back/login").forward(hreq, response);
		}
		
		System.out.println("uri:"+uri);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

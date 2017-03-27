package com.bit2017.security;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.web.context.support.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.*;

import com.bit2017.mysite.service.*;
import com.bit2017.mysite.vo.*;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	
	//이렇게 바로 가져올 수도 있음
	/*@Autowired
	private UserService userService;*/
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());		
		
		UserService userService = ac.getBean(UserService.class);
		UserVo vo = userService.getUser(email, password);
		if(vo==null) {
			response.sendRedirect(request.getContextPath() +"/user/loginform?result=fail");
			return false;
		}
		
		//인증처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", vo);
		response.sendRedirect(request.getContextPath() + "/main");
		
		return false;
	}

}

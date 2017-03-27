package com.bit2017.security;

import javax.servlet.http.*;

import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.*;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter {



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		HttpSession session = request.getSession(false);
		if(session!= null) {
			session.removeAttribute("authUser");
			session.invalidate();
			
		}
		response.sendRedirect(request.getContextPath() + "/main");
		return false;
	}
	
}

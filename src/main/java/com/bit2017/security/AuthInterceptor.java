package com.bit2017.security;

import javax.servlet.http.*;

import org.springframework.web.method.*;
import org.springframework.web.servlet.handler.*;

import com.bit2017.mysite.vo.*;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		
		if(handler instanceof HandlerMethod==false) {
			return true;
		}
		
		
		//1. @Auth 를 달고있는 핸들러인가를 확인!
		Auth auth = ((HandlerMethod)handler).getMethodAnnotation(Auth.class);
		if(auth==null) {
			return true;
		}
		
		//2. 달려있는 경우에는 Session 에 authUser가 있는지 확인
		
		HttpSession session = request.getSession(false);
		if(session.getAttribute("authUser")==null) {
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser==null) {
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
		
		return true;
	}

}

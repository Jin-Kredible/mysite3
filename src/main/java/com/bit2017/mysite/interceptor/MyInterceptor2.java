package com.bit2017.mysite.interceptor;

import javax.servlet.http.*;

import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.*;

public class MyInterceptor2 extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		
		
		return true;
	}

}
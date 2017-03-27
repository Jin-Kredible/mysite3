package com.bit2017.security;

import javax.servlet.http.*;

import org.springframework.core.*;
import org.springframework.web.bind.support.*;
import org.springframework.web.context.request.*;
import org.springframework.web.method.support.*;

import com.bit2017.mysite.vo.*;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, 
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		if(supportsParameter(parameter)== false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		//@AuthUser 가 붙어있고
		//Type 이 UserVo이면 밑에 실행
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		
		HttpSession session = request.getSession(false);
		
		if(session==null) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		//@AuthUser 가 달려있지 않음
		if(authUser == null) {
			return false;
		}
		
		//파라미터 타입이 UserVo인지 
		if(parameter.getParameterType().equals(UserVo.class) ==false){
			return false;
		}
		
		return true;
	}

}

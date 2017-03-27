package com.bit2017.mysite.exception;

import java.io.*;

import org.apache.commons.logging.*;
import org.springframework.web.bind.annotation.*;

import kr.co.saramin.logexample3.controller.*;

@ControllerAdvice
public class GlobalExceptionHandler{
	private static final Log LOG = LogFactory.getLog( GlobalExceptionHandler.class );
	@ExceptionHandler(Exception.class)
	public String ExceptionHandler( Exception e) {
		//1. log 처리
		StringWriter erros = new StringWriter();
		e.printStackTrace(new PrintWriter(erros));
		LOG.error(erros.toString());
		//2. 화면처리 (사용자에게 알림)
		return "/error/exception";
	}
}

package com.bit2017.mysite.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	
	@RequestMapping("/main")
	public String main() {
		return "/main/index";
	}
	
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "<h1>안녕하세요</h1>";
	}
	
	
	
}

package com.bit2017.mysite.controller;

import java.util.*;

import javax.servlet.http.*;
import javax.validation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.bit2017.mysite.dto.*;
import com.bit2017.mysite.repository.*;
import com.bit2017.mysite.service.*;
import com.bit2017.mysite.vo.*;
import com.bit2017.security.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail(
		@RequestParam (value="email", required=true, defaultValue="") String email) {
		boolean isExists = userService.exists(email);
		
		return JSONResult.success(isExists? "Exist" : "Not exists");
		
	}
	
	
	@RequestMapping("/joinform")
	public String joinform( @ModelAttribute UserVo userVo) {
		return "/user/joinform";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		if(result.hasErrors()==true) {
		/*	List<ObjectError> list = result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println(error);
			}*/
			System.out.println(result);
			System.out.println(userVo);
			model.addAllAttributes(result.getModel());
			return "/user/joinform";
		}
		//System.out.println(uservo);
		/*if(uservo.getName()==""||uservo.getEmail()==""||uservo.getPassword()=="") {
			return "/user/joinform";
		}*/
		
		//userService.join(uservo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "/user/joinsuccess";
	}

	@RequestMapping("/loginform")
	public String loginform() {
		return "/user/loginform";
	}
	
	@Auth
	@RequestMapping("/modifyform")
	public String modifyform(@AuthUser UserVo authUser, Model model) {
		
	
		UserVo userVo = userService.getUser(authUser.getEmail(), authUser.getPassword());
		model.addAttribute("userVo", userVo);
		//System.out.println(userVo);
		return "/user/modifyform";
	}
	
	@Auth
	@RequestMapping("/modify") 
	public String modify(@ModelAttribute UserVo userVo,
			@AuthUser UserVo authUser){
		

		userService.updateUser(userVo, authUser.getNo());
		
		authUser.setName(userVo.getName());
		
		
		return "redirect:/user/modifyform";
	}


}

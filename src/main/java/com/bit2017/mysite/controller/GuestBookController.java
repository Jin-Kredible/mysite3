package com.bit2017.mysite.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.bit2017.mysite.service.*;
import com.bit2017.mysite.vo.*;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	GuestBookService gService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Guestvo> list = gService.getGuestBookList();
		model.addAttribute("list", list);
		return "/guestbook/list";
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute Guestvo vo) {
		if(vo.getNo()==null||vo.getPubDate()==null) {
			return "redirect:/guestbook/list";
		}
		
		gService.insertGuestBook(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping("/deleteform")
	public String deleteform(@RequestParam (value="no", required=true, defaultValue="") Long no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute Guestvo vo, @RequestParam (value="no", required=true, defaultValue="") Long no) {
		vo.setNo(no);
		boolean trueOrFalse = gService.deleteGuestBook(vo);
		
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping("/list-ajax") 
	public String list() {
		return "guestbook/list-ajax";
	}
	
}
	


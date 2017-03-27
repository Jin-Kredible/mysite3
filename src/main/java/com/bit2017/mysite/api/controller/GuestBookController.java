package com.bit2017.mysite.api.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.bit2017.mysite.dto.*;
import com.bit2017.mysite.service.*;
import com.bit2017.mysite.vo.*;

@Controller("guestbookAPIController")
@RequestMapping("/api/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestbookService;
	
	
	@ResponseBody
	@RequestMapping("/list/{page}")
	public JSONResult list(@PathVariable("page") Integer page ) {
		
		List<Guestvo> list = guestbookService.getGuestBookList(page);
		
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public JSONResult list(@ModelAttribute Guestvo vo) {
		//System.out.println(vo);
		guestbookService.insertGuestBook(vo);	
		Long i = guestbookService.getMaxNoForPrepend();
		vo.setNo(i);
		String pubDate = guestbookService.getMessage(i);
		vo.setPubDate(pubDate);
		
		System.out.println(vo);
		return JSONResult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(@ModelAttribute Guestvo vo) {
		
		boolean result = guestbookService.deleteGuestBook(vo);
		return JSONResult.success(result? vo.getNo() : -1);
	}
	
	
	
}

package com.bit2017.mysite.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.bit2017.mysite.service.*;
import com.bit2017.mysite.vo.*;

@Controller
@RequestMapping ("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	
	@RequestMapping("/upload")
	public String upload(@RequestParam ("file") MultipartFile file, 
			@RequestParam("comments") String comments,
			Model model) {
		
		//System.out.println("filllllllllllllle" + file);
		
		galleryService.restore(file, comments);
		
		return "redirect:/gallery/list";
	}
	
	@RequestMapping(value={"","/list"})
	public String list(Model model) {
		List<GalleryVo> list = galleryService.getGalleryList();
		model.addAttribute("list", list);
		
		return "gallery/list";
	}
	
}

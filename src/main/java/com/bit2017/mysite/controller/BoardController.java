package com.bit2017.mysite.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.bit2017.mysite.repository.*;
import com.bit2017.mysite.service.*;
import com.bit2017.mysite.vo.*;
import com.bit2017.security.*;
import com.bit2017.web.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService bService;
	
	@Auth
	@RequestMapping("/replyform")
	public String replyform(@RequestParam (value="depth", required=true, defaultValue="") int depth,
			@RequestParam (value="gNo", required=true, defaultValue="") int gNo,
			@RequestParam (value="oNo", required=true, defaultValue="") int oNo
			, Model model) {

		
		model.addAttribute("depth", depth);
		model.addAttribute("gNo", gNo);
		model.addAttribute("oNo", oNo);
		
		
		return "/board/reply";
		
	}
	
	@Auth
	@RequestMapping("/reply")
	public String reply(
			@RequestParam (value="depth", required=true, defaultValue="") int depth,
			@RequestParam (value="oNo", required=true, defaultValue="") int oNo,
			@RequestParam (value="gNo", required=true, defaultValue="") int gNo,
			@RequestParam (value="userNo", required=true, defaultValue="") Long userNo,
			@ModelAttribute BoardVo vo) {
		
		vo.setDepth(depth);
		vo.setoNo(oNo);
		vo.setgNo(gNo);
		
		System.out.println(vo);
		
		bService.inputReply(vo, userNo);
		
		return "redirect:/board/list";
	}
	
	
	
	@Auth
	@RequestMapping("/delete")
	public String delete(@ModelAttribute BoardVo vo) {
		
		bService.deleteBoard(vo);
		return "redirect:/board/list";
		
	}
	
	@RequestMapping("/modifyform")
	public String modifyform(@RequestParam (value="no", required=true, defaultValue="") Long no,
			//@RequestParam (value="contentNo", required=true, defaultValue="") String title,
			@RequestParam (value="title", required=true, defaultValue="") String title,
			@RequestParam (value="content", required=true, defaultValue="") String content,
			@ModelAttribute BoardVo vo,
			Model model) {
		model.addAttribute("title", vo.getTitle());
		model.addAttribute("content", vo.getContent());
		model.addAttribute("vo", vo);
		model.addAttribute("no", no);
		//System.out.println("VO " + vo);
		//System.out.println("NO " + no);
		return "/board/modify";
	}
	
	@Auth
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo vo, @RequestParam (value="no", required=true, defaultValue="") Long no) {
		System.out.println("BBBBBBBBBBBBBBB" + no);
		System.out.println("VO " + vo);
		bService.updateBoardContent(vo, no);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/view")
	public String viewer(@RequestParam (value="no", required=true, defaultValue="") Long no, Model model) {
		BoardVo vo = bService.getBoardContent(no);
		model.addAttribute("contentNo", vo);
		model.addAttribute("no", no);
		//System.out.println("aaaaaaaaaaaaa" + no);
		return "/board/view"; 
	}
		
	@Auth
	@RequestMapping("/writeform")
	public String writeForm(@RequestParam (value="userNo", required=true, defaultValue="") Long userNo, Model model) {
		model.addAttribute("userNo", userNo);
		return "/board/write";
	}
	
	
	@Auth
	@RequestMapping("/add")
	public String addToBoard(@ModelAttribute BoardVo boardVo, @RequestParam (value="userNo", required=true, defaultValue="") Long userNo) {
	
		
		boolean result = bService.Input(boardVo, userNo);
		//System.out.println(result);
		if(result==false) {
			return "/board/add";
		}
		return "redirect:/board/list";
		
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam (value="pageNum", required=true, defaultValue="") String pageNum1,
			@RequestParam (value="keyword", required=true, defaultValue="") String keyword,
			Model model) {
		
		
		Map numbers = bService.getListNum(pageNum1, keyword);
		List list = (List)numbers.get("list");
		//System.out.println(list);
		model.addAttribute("list", list);
		
		numbers.get("pageSize");
		model.addAttribute( "pageSize", numbers.get("pageSize") );
		model.addAttribute( "blockSize", numbers.get("blockSize") );
		model.addAttribute( "currentBlock", numbers.get("currentBlock") );
		model.addAttribute( "beginPage", numbers.get("beginPage") );
		model.addAttribute( "previous", numbers.get("previous") );
		model.addAttribute( "next", numbers.get("next") );
		model.addAttribute( "endPage", numbers.get("endPage") );
		model.addAttribute( "currentPage", numbers.get("currentPage") );
		model.addAttribute("keyword", numbers.get("keyword"));

	
		
		
		return "/board/list";
		
	}
	
	
}

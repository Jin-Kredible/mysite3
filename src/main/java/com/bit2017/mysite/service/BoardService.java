package com.bit2017.mysite.service;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;


import com.bit2017.mysite.repository.*;
import com.bit2017.mysite.vo.*;
import com.bit2017.web.util.*;

@Service
public class BoardService {
	
	@Autowired
	BoardDao dao;

	

	
	
	public boolean deleteBoard(BoardVo vo) {
		return dao.boardDelete(vo);
	}

	public boolean updateBoardContent(BoardVo vo, Long bNo) {
		return dao.boardContentUpdate(vo, bNo);
	}
	
	public BoardVo getBoardContent(Long bNo) {
		return dao.getBoardContent(bNo);
	}
	
	public boolean Input(BoardVo boardVo, Long userNo) {
		boolean result = dao.boardInput(boardVo.getTitle(), boardVo.getContent(), userNo);
		//System.out.println(result);
		if(result==false) {
			return false;
		}
		
		return true;
	}
	
	public boolean inputReply(BoardVo vo, Long userNo ) {
		
		boolean i =  dao.replyBoard(vo, userNo);
		boolean z = dao.updateReply(vo, userNo);
		return i;
	}
	
	public Map<String, Object> getListNum(String pageNum, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BoardVo> list = null;
		
		int page = WebUtil.checkIntParam(pageNum, 1);
		String keyword = WebUtil.checkNullParam(keywords, "");
		
		if(keyword!="" && keyword!=null) {
			list = dao.findList(keyword, page);
		} else {
			list = dao.getList(page);
		}
		
		
		final int listCount = 5;
		final int blockCount = 5;
	
		int totalListSize = dao.getListSize(keyword);
		//System.out.println(totalListSize);

		Integer pageSize = (int)Math.ceil((double)totalListSize / listCount);
		int blockSize = (int)Math.ceil((double)pageSize/ blockCount);
		int currentBlock = (int)Math.ceil((double)page/blockCount);
		//System.out.println("pageSize = " + pageSize);
		
		
		if(page < 0) {
			page =1;
			currentBlock =1;
		} else if (page > totalListSize) {
			page = totalListSize;
		}
		
		int beginPage = (currentBlock ==0 ? 1: (currentBlock-1)*blockCount+1);
		int prevPage = (currentBlock <= 1 ? 0 : (currentBlock-1)*listCount  );
		int nextPage =(currentBlock >= blockSize ? 0 : (currentBlock*blockCount)+1) ;
		int endPage =(nextPage < 0 ? listCount : currentBlock*blockCount);

		map.put("pageSize", pageSize);
		map.put("blockSize", blockSize);
		map.put("currentBlock", currentBlock);
		map.put("beginPage", beginPage);
		map.put("previous", prevPage);
		map.put("next", nextPage);
		map.put("endPage", endPage);
		map.put("currentPage", page);
		map.put("list", list);
		map.put("keyword", keyword);
		
		//System.out.println(list);
		return map;
		
	}
	
}

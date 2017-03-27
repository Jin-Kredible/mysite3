package com.bit2017.mysite.repository;

import java.sql.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bit2017.mysite.vo.*;
import com.bit2017.web.util.*;

import oracle.jdbc.pool.*;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	

	@Autowired
	private OracleDataSource dataSource;
	
	public List<BoardVo> findList(String keyword, int page) {
		Map map = new HashMap();
		
		map.put("keyword", keyword);
		map.put("page", page);
		List<BoardVo> list = sqlSession.selectList("board.look", map);
		

		
		return list;
	}
	
	public boolean boardContentUpdate(BoardVo vo, Long bNo) {
		Map map = new HashMap();
		map.put("title", vo.getTitle());
		map.put("content", vo.getContent());
		map.put("bNo", bNo);
		int update = sqlSession.update("board.updatecontent", map);
		
		return update==1;
	}

	public boolean boardDelete(BoardVo vo) {

		int i = sqlSession.delete("board.delete", vo);

		return i == 1;
	}

	public boolean boardInput(String title, String content, Long no) {
		
		
		
		Map map = new HashMap();
		map.put("title", title);
		map.put("content", content);
		map.put("no", no);

		int count = sqlSession.insert("board.input", map);
		return count == 1;

	}

	public int getListSize(String keyword) {
		/*Map map = new HashMap();
		map.put("keyword", keyword);*/
		int listSize = sqlSession.selectOne("board.getListSize", keyword);
		return listSize;
	}

	public List<BoardVo> getList(int pageNum) {
		int page = pageNum;
		System.out.println("PAGE :::: " + page);
		System.out.println("?????????");
		return sqlSession.selectList("board.getList", page);

	}
	
	public BoardVo getBoardContent(Long bNo) {
		BoardVo vo = sqlSession.selectOne("board.getboard", bNo);
		int i = sqlSession.update("board.updateHit", bNo);
		return vo;
	}
	
	public boolean replyBoard(BoardVo vo, Long userNo) {
		Map map = new HashMap();
		map.put("title", vo.getTitle());
		map.put("content", vo.getContent());
		map.put("oNo", vo.getoNo());
		map.put("gNo", vo.getgNo());
		map.put("depth", vo.getDepth());
		map.put("userNo", userNo);
		
		System.out.println(map);

		sqlSession.insert("board.inputreply", map);
		
		
		return true;
		
	}
	
	public boolean updateReply(BoardVo vo, Long userNo) {
		Map map = new HashMap();
		map.put("title", vo.getTitle());
		map.put("content", vo.getContent());
		map.put("oNo", vo.getoNo());
		map.put("gNo", vo.getgNo());
		map.put("depth", vo.getDepth());
		map.put("userNo", userNo);
		sqlSession.update("board.updateono", map);
		
		return true;
	}

}

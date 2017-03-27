package com.bit2017.mysite.repository;

import java.sql.*;
import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;


import com.bit2017.mysite.vo.*;


@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(Guestvo vo) {
		
		int insert = sqlSession.insert("guestbook.insert", vo);
		return insert==1;
		
	}


	public boolean modify(Guestvo vo) {
		/*System.out.println("heeeeeeeeey?");*/
		int delete = sqlSession.delete("guestbook.delete", vo);
		return delete ==1;
	
	}
	
public String get(Long no) {
		
		return sqlSession.selectOne("guestbook.getpubdate", no);
	}

	public List<Guestvo> getList() {
		
		List<Guestvo> vo = sqlSession.selectList("guestbook.getlist");
		
		return vo;
	}
	
public List<Guestvo> getList(Integer page) {
		
		List<Guestvo> vo = sqlSession.selectList("guestbook.getlistByPage", page);
		
		return vo;
	}

public Long getMaxNo() {
	Long i = sqlSession.selectOne("guestbook.getMaxNo");
	return i;
}
}

package com.bit2017.mysite.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bit2017.mysite.repository.*;
import com.bit2017.mysite.vo.*;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestDao dao;
	
	public void insertGuestBook(Guestvo vo) {
		dao.insert(vo);
		
	}
	
	public List<Guestvo> getGuestBookList() {
		return dao.getList();
	}
	
	public List<Guestvo> getGuestBookList(Integer page) {
		return dao.getList(page);
	}
	
	public Long getMaxNoForPrepend() {
		return dao.getMaxNo();
	}
	
	public String getMessage( Long no) {
		return dao.get(no);
	}
	
	public boolean deleteGuestBook(Guestvo vo){
			boolean delete = dao.modify(vo);
			return delete;
	}
}

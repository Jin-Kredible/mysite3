package com.bit2017.mysite.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bit2017.mysite.repository.*;
import com.bit2017.mysite.vo.*;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	public boolean exists(String email) {
		
		UserVo userVo = dao.get(email);
		return (userVo != null);
		
	}
	
	public boolean join(UserVo userVo ) {
		return dao.insert(userVo);
	}
	
	public UserVo getUser(String email, String password) {
		return dao.get(email, password);
	}
	
	public boolean updateUser(UserVo vo, Long no) {
		dao.update(vo, no);
		
		return true;
	}
}

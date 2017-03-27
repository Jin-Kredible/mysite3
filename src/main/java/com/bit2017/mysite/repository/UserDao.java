package com.bit2017.mysite.repository;

import java.sql.*;
import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bit2017.mysite.exception.*;
import com.bit2017.mysite.vo.*;
import com.bit2017.web.util.*;

import oracle.jdbc.pool.*;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private OracleDataSource dataSource;
	
	public UserVo get(String email) {
		UserVo userVo = sqlSession.selectOne("user.getByEmail", email);
		return userVo;
	}

	public boolean update(UserVo userVo, Long no) {
		Map map = new HashMap();
		map.put("name", userVo.getName());
		map.put("password", userVo.getPassword());
		map.put("gender", userVo.getGender());
		map.put("no", no);

		int i = sqlSession.update("user.update", map);
		return i == 1;
	}

	public boolean insert(UserVo userVo) {
		String vo = WebUtil.checkNullParam(sqlSession.selectOne("user.getByEmail", userVo.getEmail()), "");
		if(vo==""||vo==null) {
		int i = sqlSession.insert("user.insert", userVo);
		return i == 1;
		}
		return false;
	}

	public UserVo get(String email, String password) throws UserDAOException {

		Map map = new HashMap();
		map.put("email", email);
		map.put("password", password);

		UserVo vo = sqlSession.selectOne("user.getByEmailandPassword", map);

		return vo;
	}

}

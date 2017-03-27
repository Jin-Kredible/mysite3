package com.bit2017.mysite.repository;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bit2017.mysite.vo.*;

@Repository
public class GalleryDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public boolean insertGallery(GalleryVo vo) {
		System.out.println(vo);
		int i = sqlSession.insert("gallery.input", vo);
		return i==1;
	}
	
	public List<GalleryVo> selectGallery() {
		List<GalleryVo> list = sqlSession.selectList("gallery.getList");
		return list;
	}
}

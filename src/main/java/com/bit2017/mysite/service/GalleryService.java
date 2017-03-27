package com.bit2017.mysite.service;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.bit2017.mysite.repository.*;
import com.bit2017.mysite.vo.*;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao dao;

	private static final String SAVE_PATH = "/upload";
	
	public List<GalleryVo> getGalleryList() {
		List<GalleryVo> list= dao.selectGallery();
		return list;
	}
	
	public void restore(MultipartFile file, String comments) {
		
		if(file.isEmpty()) {
			return;
		}
		
		String originalFileName = file.getOriginalFilename();
		String extName = originalFileName.substring(originalFileName.lastIndexOf(".")+1, originalFileName.length());
		String saveFileName = generateSaveFile(extName);
		
		try {
			writeFile(file, saveFileName);
		} catch (IOException e) {
			new RuntimeException(e);
		}
		System.out.println(originalFileName);
		System.out.println(extName);
		System.out.println(saveFileName);
		
		GalleryVo vo = new GalleryVo();
		vo.setOriginalFileName(originalFileName);
		vo.setSaveFileName(saveFileName);
		vo.setComments(comments);
		dao.insertGallery(vo);
		
	}
	
	public void writeFile(MultipartFile file, String saveFileName) throws IOException {
		byte[] data = file.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH+"/" + saveFileName);
		fos.write(data);
		fos.close();
	}
	
	public String generateSaveFile(String extName) {
		
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("."+extName);
		
		return fileName;

	}
	
}

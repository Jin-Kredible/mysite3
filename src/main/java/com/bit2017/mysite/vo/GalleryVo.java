package com.bit2017.mysite.vo;

import org.springframework.stereotype.*;

public class GalleryVo {

	private String originalFileName;
	private String saveFileName;
	private String comments;
	private Long no;
	
	
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "GalleryVo [originalFileName=" + originalFileName + ", saveFileName=" + saveFileName + ", comments="
				+ comments + ", no=" + no + "]";
	}
	
	
}

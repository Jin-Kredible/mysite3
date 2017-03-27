package com.bit2017.mysite.vo;

import java.util.*;

public class BoardAttributeVo {
	
	private List<BoardVo> list;
	private int totalListSize;
	private int listCount;
	private int pageNum;
	private int beginPage;
	private int endPage;
	private int prevPage;
	private int nextPage;
	private int pageSize;
	
	public BoardAttributeVo(List<BoardVo> list, int totalListSize, int listCount, int pageNum, int beginPage,
			int endPage, int prevPage, int nextPage, int pageSize) {
		super();
		this.list = list;
		this.totalListSize = totalListSize;
		this.listCount = listCount;
		this.pageNum = pageNum;
		this.beginPage = beginPage;
		this.endPage = endPage;
		this.prevPage = prevPage;
		this.nextPage = nextPage;
		this.pageSize = pageSize;
	}
	
	public List<BoardVo> getList() {
		return list;
	}
	public void setList(List<BoardVo> list) {
		this.list = list;
	}
	public int getTotalListSize() {
		return totalListSize;
	}
	public void setTotalListSize(int totalListSize) {
		this.totalListSize = totalListSize;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	

}
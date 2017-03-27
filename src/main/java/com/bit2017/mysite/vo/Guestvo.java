package com.bit2017.mysite.vo;

public class Guestvo {
	private Long no;
	private String name;
	private String password;
	private String content;
	private String pubDate;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String pasword) {
		this.password = pasword;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	@Override
	public String toString() {
		return "Guestvo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content + ", pubDate="
				+ pubDate + "]";
	}
	
	
}

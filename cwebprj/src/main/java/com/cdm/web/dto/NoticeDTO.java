package com.cdm.web.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class NoticeDTO { //공지사항 글

	private int notice_num;// 공지사항 번호
	private String title;// 제목

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;// 작성날짜
	private String content;// 내용
	private int viewcount;// 조회수

	public int getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
}

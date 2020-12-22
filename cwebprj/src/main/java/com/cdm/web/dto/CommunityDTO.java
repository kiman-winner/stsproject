package com.cdm.web.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CommunityDTO {	//게시물 
	private int community_num; // 게시글 번호
	private String title; // 게시글 제목
	private String writer_id;// 작성자

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;// 등록날짜
	private String content;// 내용
	private int viewcount;// 조회수
	private int replycount;// 댓글수

	public int getReplycount() {
		return replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public int getCommunity_num() {
		return community_num;
	}

	public void setCommunity_num(int community_num) {
		this.community_num = community_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
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

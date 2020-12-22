package com.cdm.web.dto;

import java.util.Date;

public class ReplyDTO { //댓글 
	private int community_num;// 게시글 번호
	private int reply_num;// 댓글 번호
	private String content;// 내용
	private String writer_id;// 작성자 아이디
	private Date regdate;// 작성날짜

	public int getCommunity_num() {
		return community_num;
	}

	public void setCommunity_num(int community_num) {
		this.community_num = community_num;
	}

	public int getReply_num() {
		return reply_num;
	}

	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

}

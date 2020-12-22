package com.cdm.web.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CommunityDTO {	//�Խù� 
	private int community_num; // �Խñ� ��ȣ
	private String title; // �Խñ� ����
	private String writer_id;// �ۼ���

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;// ��ϳ�¥
	private String content;// ����
	private int viewcount;// ��ȸ��
	private int replycount;// ��ۼ�

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

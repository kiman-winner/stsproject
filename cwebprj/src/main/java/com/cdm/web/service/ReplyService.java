package com.cdm.web.service;

import java.util.List;

import com.cdm.web.dto.ReplyDTO;

public interface ReplyService {
	public List<ReplyDTO> readReply(int community_num) throws Exception;
	
	public void writeReply(ReplyDTO replyDTO) throws Exception;	//¥Ò±€ ¿€º∫ 

	public void deleteReply(int reply_num)throws Exception;	//¥Ò±€ ªË¡¶ 

	public void modifyReply(ReplyDTO replyDTO) throws Exception;	//¥Ò±€ ºˆ¡§

}

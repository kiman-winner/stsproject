package com.cdm.web.service;

import java.util.List;

import com.cdm.web.dto.ReplyDTO;

public interface ReplyService {
	public List<ReplyDTO> readReply(int community_num) throws Exception;	//´ñ±Û ºÒ·¯¿À±â 
	
	public void writeReply(ReplyDTO replyDTO) throws Exception;	//´ñ±Û ÀÛ¼º 

	public void deleteReply(int reply_num, int community_num)throws Exception;	//´ñ±Û »èÁ¦ 

	public void modifyReply(ReplyDTO replyDTO) throws Exception;	//´ñ±Û ¼öÁ¤

}

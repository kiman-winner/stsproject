package com.cdm.web.dao;

import java.util.List;

import com.cdm.web.dto.ReplyDTO;

public interface ReplyDAO {
	public List<ReplyDTO> readReply (int community_num) throws Exception;	//´ñ±Û ÀÐ±â 
	
	public void writeReply(ReplyDTO replyDTO) throws Exception;	//´ñ±Û ÀÛ¼º 

	public void deleteAll(int community_num)throws Exception; //ÇØ´ç ´ñ±Û ¸ðµÎ »èÁ¦ 

	public void deleteReply(int reply_num) throws Exception;//´ñ±Û»èÁ¦ 

	public void modifyReply(ReplyDTO replyDTO)throws Exception;//´ñ±Û¼öÁ¤
}

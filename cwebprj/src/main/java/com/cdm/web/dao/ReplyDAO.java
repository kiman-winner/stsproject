package com.cdm.web.dao;

import java.util.List;

import com.cdm.web.dto.ReplyDTO;

public interface ReplyDAO {
	public List<ReplyDTO> readReply (int community_num) throws Exception;	//¥Ò±€ ¿–±‚ 
	
	public void writeReply(ReplyDTO replyDTO) throws Exception;	//¥Ò±€ ¿€º∫ 
}

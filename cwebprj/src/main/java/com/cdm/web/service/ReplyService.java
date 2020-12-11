package com.cdm.web.service;

import java.util.List;

import com.cdm.web.dto.ReplyDTO;

public interface ReplyService {
	public List<ReplyDTO> readReply(int community_num) throws Exception;
	
	public void writeReply(ReplyDTO replyDTO) throws Exception;	//댓글 작성 

	public void deleteAll(int community_num) throws Exception;	//게시글에 댓글 모두 삭제 

	public void deleteReply(int reply_num)throws Exception;	//댓글 삭제 

	public void modifyReply(ReplyDTO replyDTO) throws Exception;	//댓글 수정

}

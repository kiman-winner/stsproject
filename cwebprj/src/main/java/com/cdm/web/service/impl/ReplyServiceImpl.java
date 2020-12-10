package com.cdm.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdm.web.dao.ReplyDAO;
import com.cdm.web.dto.ReplyDTO;
import com.cdm.web.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	
	@Override
	public List<ReplyDTO> readReply(int community_num) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.readReply(community_num);
	}


	@Override
	public void writeReply(ReplyDTO replyDTO) throws Exception {
		replyDAO.writeReply(replyDTO);		
	}


	@Override
	public void deleteAll(int community_num) throws Exception {
		replyDAO.deleteAll(community_num);
	}


	@Override
	public void deleteReply(int reply_num) throws Exception {
		replyDAO.deleteReply(reply_num);
		
	}



}

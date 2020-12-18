package com.cdm.web.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dao.ReplyDAO;
import com.cdm.web.dto.ReplyDTO;
import com.cdm.web.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	@Autowired
	private CommunityDAO communityDAO;
	
	
	@Override
	public List<ReplyDTO> readReply(int community_num) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.readReply(community_num);
	}


	@Transactional
	@Override
	public void writeReply(ReplyDTO replyDTO) throws Exception {	//��� �ۼ� 
		
		HashMap<String, Integer> updateHash = new HashMap<String, Integer>(); 
		updateHash.put("updown", 1);
		updateHash.put("community_num", replyDTO.getCommunity_num());
		
		communityDAO.updateReplyCount(updateHash);	//��� �� ���� 
		
		replyDAO.writeReply(replyDTO);		
	}

	@Transactional
	@Override
	public void deleteReply(int reply_num,int community_num) throws Exception {		//��� ���� 
		HashMap<String, Integer> updateHash = new HashMap<String, Integer>(); 
		updateHash.put("updown", -1);
		updateHash.put("community_num", community_num);
		
		communityDAO.updateReplyCount(updateHash);	//��� �� ���� 
		
		replyDAO.deleteReply(reply_num);
		
	}


	@Override
	public void modifyReply(ReplyDTO replyDTO) throws Exception {
		replyDAO.modifyReply(replyDTO);
		
	}

}

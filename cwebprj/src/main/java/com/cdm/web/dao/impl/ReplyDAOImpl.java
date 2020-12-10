package com.cdm.web.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdm.web.dao.ReplyDAO;
import com.cdm.web.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Autowired SqlSession session;
	
	@Override
	public List<ReplyDTO> readReply(int community_num) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList("replyNS.readReply",community_num);
	}

	@Override
	public void writeReply(ReplyDTO replyDTO) throws Exception {
		session.insert("replyNS.writeReply",replyDTO);
	}

}

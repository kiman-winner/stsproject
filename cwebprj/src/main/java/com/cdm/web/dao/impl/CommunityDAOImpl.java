package com.cdm.web.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.Criteria;

@Repository
public class CommunityDAOImpl implements CommunityDAO{
	
	@Autowired
	private SqlSession session;
	@Override
	public void write(CommunityDTO vo) throws Exception {	//�Խù� ��� 
		
		session.insert("communityNS.write", vo);	
	}
	
	public List<CommunityDTO> read(Criteria criteria) throws Exception {	//�Խù� ��� 
		
		return session.selectList("communityNS.read",criteria);	
	}

	@Override
	public CommunityDTO detail(int community_num) {
		
		return session.selectOne("communityNS.detail",community_num);	
	}

	@Override
	public void delete(int community_num) {	//�Խñ� ���� 
		session.delete("communityNS.delete", community_num);	//�Խñ� ���� 
	}

	@Override
	public void modify(CommunityDTO vo) throws Exception {
		session.update("communityNS.modify", vo);
	}

	@Override
	public void updateViewCount(int community_num) throws Exception {
		session.update("communityNS.updateViewCount", community_num);
		
	}

	@Override
	public int listCount() throws Exception {	//�Խù� �� ���� ���ϱ� 
		// TODO Auto-generated method stub
		return session.selectOne("communityNS.listCount");	
	}

	@Override
	public void upReplyCount(int community_num) throws Exception {
		session.update("communityNS.upReplyCount", community_num);
	}

	@Override
	public void downReplyCount(int community_num) throws Exception {
		session.update("communityNS.downReplyCount", community_num);
		
	}
}

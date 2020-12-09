package com.cdm.web.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.MemberDTO;

@Repository
public class CommunityDAOImpl implements CommunityDAO{
	
	@Autowired
	private SqlSession session;
	@Override
	public void write(CommunityDTO vo) throws Exception {	//게시물 등록 
		
		session.insert("communityNS.write", vo);	
	}
	
	public List<CommunityDTO> read() throws Exception {	//게시물 등록 
		
		return session.selectList("communityNS.read");	
	}

	@Override
	public CommunityDTO detail(int community_num) {
		
		return session.selectOne("communityNS.detail",community_num);	
	}

	@Override
	public void delete(int community_num) {
		session.delete("communityNS.delete", community_num);	
		
	}

	@Override
	public void modify(CommunityDTO vo) throws Exception {
		session.update("communityNS.modify", vo);
	}
}

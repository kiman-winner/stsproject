package com.cdm.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.Criteria;
import com.cdm.web.service.CommunityService;
@Service
public class CommunityServiceImpl implements CommunityService{
	
	@Autowired
	private CommunityDAO communityDAO;
	@Override
	public void write(CommunityDTO vo) throws Exception {	//게시글 작성
		communityDAO.write(vo);
	}
	@Override
	public List<CommunityDTO> read(Criteria criteria) throws Exception {	//불러오기
		// TODO Auto-generated method stub
		return communityDAO.read(criteria);
	}
	@Override
	public int listCount() throws Exception {			//게시물 총 개수 
		return communityDAO.listCount();
	}
	
	@Override
	public CommunityDTO detail(int community_num) throws Exception {	//상세보기
		// TODO Auto-generated method stub
		return communityDAO.detail(community_num);
	}
	@Override
	public void delete(int community_num) throws Exception {	//게시글 삭제
		communityDAO.delete(community_num);
		
	}
	@Override
	public void modify(CommunityDTO vo) throws Exception {	//게시글 수정 
		communityDAO.modify(vo);
		
	}
	@Override
	public void updateViewCount(int community_num) throws Exception {	//조회수 증가
		// TODO Auto-generated method stub
		communityDAO.updateViewCount(community_num);
	}
	@Override
	public void upReplyCount(int community_num) throws Exception {	//댓글 수 증가
		communityDAO.upReplyCount(community_num);
		
	}
	@Override
	public void downReplyCount(int community_num) throws Exception {
		communityDAO.downReplyCount(community_num);
		
	}


}

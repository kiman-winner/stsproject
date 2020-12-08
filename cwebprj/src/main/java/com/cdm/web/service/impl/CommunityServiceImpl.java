package com.cdm.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.NoticeDTO;
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
	public List<CommunityDTO> read() throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.read();
	}
	@Override
	public CommunityDTO detail(int community_num) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.detail(community_num);
	}
	
}

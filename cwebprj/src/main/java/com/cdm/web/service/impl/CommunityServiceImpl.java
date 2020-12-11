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
	public void write(CommunityDTO vo) throws Exception {	//�Խñ� �ۼ�
		communityDAO.write(vo);
	}
	@Override
	public List<CommunityDTO> read(Criteria criteria) throws Exception {	//�ҷ�����
		// TODO Auto-generated method stub
		return communityDAO.read(criteria);
	}
	@Override
	public int listCount() throws Exception {			//�Խù� �� ���� 
		return communityDAO.listCount();
	}
	
	@Override
	public CommunityDTO detail(int community_num) throws Exception {	//�󼼺���
		// TODO Auto-generated method stub
		return communityDAO.detail(community_num);
	}
	@Override
	public void delete(int community_num) throws Exception {	//�Խñ� ����
		communityDAO.delete(community_num);
		
	}
	@Override
	public void modify(CommunityDTO vo) throws Exception {	//�Խñ� ���� 
		communityDAO.modify(vo);
		
	}
	@Override
	public void updateViewCount(int community_num) throws Exception {	//��ȸ�� ����
		// TODO Auto-generated method stub
		communityDAO.updateViewCount(community_num);
	}
	@Override
	public void upReplyCount(int community_num) throws Exception {	//��� �� ����
		communityDAO.upReplyCount(community_num);
		
	}
	@Override
	public void downReplyCount(int community_num) throws Exception {
		communityDAO.downReplyCount(community_num);
		
	}


}

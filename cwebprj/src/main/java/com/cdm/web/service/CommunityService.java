package com.cdm.web.service;

import java.util.List;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.Criteria;

public interface CommunityService {

	public void write(CommunityDTO vo) throws Exception;

	CommunityDTO detail(int community_num) throws Exception;

	public void delete(int community_num) throws Exception;

	void modify(CommunityDTO vo) throws Exception;

	public void updateViewCount(int community_num)throws Exception;
	
	public int listCount() throws Exception;	//�Խù� �� ���� 

	List<CommunityDTO> read(Criteria criteria) throws Exception; //�Խù� ��� ��ȸ 

	public void upReplyCount(int community_num) throws Exception;//�Խù� ��� �� ���� 

	public void downReplyCount(int community_num)throws Exception;//�Խù� ��� �� ���� 
}

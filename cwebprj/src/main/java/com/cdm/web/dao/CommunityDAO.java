package com.cdm.web.dao;

import java.util.HashMap;
import java.util.List;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.Criteria;
import com.cdm.web.dto.SearchCriteria;

public interface CommunityDAO {
	
	public void write(CommunityDTO vo) throws Exception ;
	public CommunityDTO detail(int community_num)throws Exception ;
	public void delete(int community_num)throws Exception ;
	void modify(CommunityDTO vo) throws Exception;
	public void updateViewCount(int community_num)throws Exception;
	public void updateReplyCount(HashMap<String, Integer> updateHash)throws Exception; //�Խù� ��� �� ������Ʈ
	
	List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception;	//�Խù� ���, �˻� 
	
	int countSearched(SearchCriteria searchCriteria) throws Exception;	//�Խù� �� , �˻� ��  
}

package com.cdm.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.page.Criteria;
import com.cdm.web.page.SearchCriteria;

public interface CommunityDAO {
	
	public void write(CommunityDTO vo) throws Exception ;
	public CommunityDTO detail(int community_num)throws Exception ;
	public void delete(int community_num)throws Exception ;
	void modify(CommunityDTO vo) throws Exception;
	public void updateViewCount(int community_num)throws Exception;
	public void updateReplyCount(HashMap<String, Integer> updateHash)throws Exception; //�Խù� ��� �� ������Ʈ
	
	List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception;	//�Խù� ���, �˻� 
	public List<CommunityDTO> listSearch() throws Exception;	//���� Ȩ������ �Խù� ���
	
	int countSearched(SearchCriteria searchCriteria) throws Exception;	//�Խù� �� , �˻� ��  
	public void insertFile(Map<String, Object> map)throws Exception;	//���� ���� 
	
	public List<Map<String, Object>> selectFileList(int community_num) throws Exception;	//÷�� ���� ��ȸ
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;	//÷�� ���� �ٿ�
	
	public void deleteFileAll(int community_num)throws Exception;	//�ش� �Խñ� ÷������ ���� 

	public void deleteFile(Map<String, Object> tempMap) throws Exception;	//÷������ ���� 
	public String searchDeleteFile(Map<String, Object> tempMap) throws Exception;	//÷������ ����� ��ȸ
	
	public List<String> searchDeleteFileAll(int community_num) throws Exception;	//�Խñ� ������ ��� ÷������ ��ȸ
	
}

package com.cdm.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.page.SearchCriteria;

public interface CommunityService {


	CommunityDTO detail(int community_num) throws Exception;

	public void delete(int community_num) throws Exception;

	void modify(CommunityDTO vo,String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) 
			throws Exception;	//�Խù� ����

	List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception; // �Խù� ���, �˻�

	int countSearched(SearchCriteria searchCriteria) throws Exception; // �Խù� �� , �˻� ��

	public void updateReplyCount(HashMap<String, Integer> updateHash) throws Exception; // �Խù� ��� �� ������Ʈ

	public void write(CommunityDTO vo, MultipartHttpServletRequest mpRequest)throws Exception;	//�Խù� �ۼ�
	
	public List<Map<String, Object>> selectFileList(int community_num) throws Exception;	//���� ��ȸ
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;//���� �ٿ�ε�

	List<CommunityDTO> listSearch()throws Exception; // ���� Ȩ������ �Խù� 

}

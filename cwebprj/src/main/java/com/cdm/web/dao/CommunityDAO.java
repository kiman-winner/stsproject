package com.cdm.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.page.SearchCriteria;

public interface CommunityDAO {

	public void register(CommunityDTO communityDTO) throws Exception; // �Խñ� ���

	public CommunityDTO detail(int community_num) throws Exception;// �󼼺���

	public void delete(int community_num) throws Exception;// �Խñ� ����

	void modify(CommunityDTO communityDTO) throws Exception;// �Խñ� ����

	public void updateViewCount(int community_num) throws Exception;// ��ȸ�� ����

	public void updateReplyCount(HashMap<String, Integer> updateHash) throws Exception; // �Խù� ��� �� ������Ʈ

	List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception; // �Խù� ���, �˻�

	public List<CommunityDTO> listSearch() throws Exception; // ���� Ȩ������ �Խù� ���

	int countSearched(SearchCriteria searchCriteria) throws Exception; // �Խù� �� , �˻� ��

	public void insertFile(Map<String, Object> map) throws Exception; // ���� ����

	public List<Map<String, Object>> selectFileList(int community_num) throws Exception; // ÷�� ���� ��ȸ

	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception; // ÷�� ���� �ٿ�

	public void deleteFile(String FILE_NO) throws Exception; // ÷������ ����

	public String searchDeleteFile(String FILE_NO) throws Exception; // ÷������ ����� ��ȸ

	public List<String> searchDeleteFileAll(int community_num) throws Exception; // �Խñ� ������ ��� ÷������ ��ȸ

}

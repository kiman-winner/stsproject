package com.cdm.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.page.SearchCriteria;

public interface NoticeService {
	
	NoticeDTO detail(int notice_num) throws Exception;	//�������� �ڼ��� ���� 

	public void delete(int notice_num) throws Exception;	//�������� ���� 

	void modify(NoticeDTO noticeDTO,String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) 
			throws Exception;	//�������� ����

	List<NoticeDTO> listSearch(SearchCriteria searchCriteria) throws Exception; // �������� ���, �˻�

	int countSearched(SearchCriteria searchCriteria) throws Exception; // �������� �� , �˻� ��

	public void register(NoticeDTO noticeDTO, MultipartHttpServletRequest mpRequest)throws Exception;	//�������� �ۼ�
	
	public List<Map<String, Object>> selectFileList(int notice_num) throws Exception;	//���� ��ȸ
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;//���� �ٿ�ε�

	List<NoticeDTO> listSearch()throws Exception; // ���� Ȩ������ �������� 

}

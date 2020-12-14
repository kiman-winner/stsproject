package com.cdm.web.dao;

import java.util.List;
import java.util.Map;

import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.page.SearchCriteria;

public interface NoticeDAO {

	void updateViewCount(int notice_num)throws Exception;	//��ȸ�� ����

	NoticeDTO detail(int notice_num)throws Exception ;	//�� ���� 

	List<String> searchDeleteFileAll(int notice_num)throws Exception ;	//���� ���� �˻�

	void delete(int notice_num)throws Exception ;	//�������� ���� 

	List<NoticeDTO> listSearch(SearchCriteria searchCriteria)throws Exception ;	//�������� ����Ʈ

	int countSearched(SearchCriteria searchCriteria)throws Exception ;	//�������� �˻� ����

	void write(NoticeDTO noticeDTO)throws Exception ;//�������� �ۼ�

	void insertFile(Map<String, Object> map)throws Exception ; //�������� ���� ÷��

	List<Map<String, Object>> selectFileList(int notice_num)throws Exception ; //�������� ���� ����Ʈ

	Map<String, Object> selectFileInfo(Map<String, Object> map)throws Exception ; //�������� ���� �ٿ�

	List<NoticeDTO> listSearch()throws Exception ;	//Ȩ������ �������� 

	String searchDeleteFile(Map<String, Object> tempMap)throws Exception; //�������� ���� �˻�

	void deleteFile(Map<String, Object> tempMap)throws Exception;	//�������� ���� ���� 

	void modify(NoticeDTO noticeDTO)throws Exception;  //�������� ���� ���� 
}

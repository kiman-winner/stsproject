package com.cdm.web.dao;

import java.util.List;
import java.util.Map;

import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.page.SearchCriteria;

public interface NoticeDAO {

	void updateViewCount(int notice_num)throws Exception;	//조회수 증가

	NoticeDTO detail(int notice_num)throws Exception ;	//상세 보기 

	List<String> searchDeleteFileAll(int notice_num)throws Exception ;	//삭제 파일 검색

	void delete(int notice_num)throws Exception ;	//공지사항 삭제 

	List<NoticeDTO> listSearch(SearchCriteria searchCriteria)throws Exception ;	//공지사항 리스트

	int countSearched(SearchCriteria searchCriteria)throws Exception ;	//공지사항 검색 갯수

	void write(NoticeDTO noticeDTO)throws Exception ;//공지사항 작성

	void insertFile(Map<String, Object> map)throws Exception ; //공지사항 파일 첨부

	List<Map<String, Object>> selectFileList(int notice_num)throws Exception ; //공지사항 파일 리스트

	Map<String, Object> selectFileInfo(Map<String, Object> map)throws Exception ; //공지사항 파일 다운

	List<NoticeDTO> listSearch()throws Exception ;	//홈페이지 공지사항 

	String searchDeleteFile(Map<String, Object> tempMap)throws Exception; //공지사항 파일 검색

	void deleteFile(Map<String, Object> tempMap)throws Exception;	//공지사항 파일 삭제 

	void modify(NoticeDTO noticeDTO)throws Exception;  //공지사항 파일 수정 
}

package com.cdm.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.page.SearchCriteria;

public interface NoticeService {
	
	NoticeDTO detail(int notice_num) throws Exception;	//공지사항 자세히 보기 

	public void delete(int notice_num) throws Exception;	//공지사항 삭제 

	void modify(NoticeDTO noticeDTO,String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) 
			throws Exception;	//공지사항 수정

	List<NoticeDTO> listSearch(SearchCriteria searchCriteria) throws Exception; // 공지사항 목록, 검색

	int countSearched(SearchCriteria searchCriteria) throws Exception; // 공지사항 수 , 검색 수

	public void register(NoticeDTO noticeDTO, MultipartHttpServletRequest mpRequest)throws Exception;	//공지사항 작성
	
	public List<Map<String, Object>> selectFileList(int notice_num) throws Exception;	//파일 조회
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;//파일 다운로드

	List<NoticeDTO> listSearch()throws Exception; // 메인 홈페이지 공지사항 

}

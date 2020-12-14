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
	public void updateReplyCount(HashMap<String, Integer> updateHash)throws Exception; //게시물 댓글 수 업데이트
	
	List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception;	//게시물 목록, 검색 
	public List<CommunityDTO> listSearch() throws Exception;	//메인 홈페이지 게시물 목록
	
	int countSearched(SearchCriteria searchCriteria) throws Exception;	//게시물 수 , 검색 수  
	public void insertFile(Map<String, Object> map)throws Exception;	//파일 삽입 
	
	public List<Map<String, Object>> selectFileList(int community_num) throws Exception;	//첨부 파일 조회
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;	//첨부 파일 다운
	
	public void deleteFileAll(int community_num)throws Exception;	//해당 게시글 첨부파일 삭제 

	public void deleteFile(Map<String, Object> tempMap) throws Exception;	//첨부파일 삭제 
	public String searchDeleteFile(Map<String, Object> tempMap) throws Exception;	//첨부파일 저장명 조회
	
	public List<String> searchDeleteFileAll(int community_num) throws Exception;	//게시글 삭제시 모든 첨부파일 조회
	
}

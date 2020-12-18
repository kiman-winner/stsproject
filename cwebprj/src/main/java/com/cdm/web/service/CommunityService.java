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

	void modify(CommunityDTO communityDTO, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest)
			throws Exception; // 게시물 수정

	List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception; // 게시물 목록, 검색

	int countSearched(SearchCriteria searchCriteria) throws Exception; // 게시물 수 ,검색 수

	public void register(CommunityDTO communityDTO, MultipartHttpServletRequest mpRequest) throws Exception; // 게시물 작성

	public List<Map<String, Object>> selectFileList(int community_num) throws Exception; // 파일 조회

	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;// 파일 다운로드

	List<CommunityDTO> listSearch() throws Exception; // 메인 홈페이지 게시물


}

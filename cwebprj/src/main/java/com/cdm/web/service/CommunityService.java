package com.cdm.web.service;

import java.util.HashMap;
import java.util.List;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.Criteria;
import com.cdm.web.dto.SearchCriteria;

public interface CommunityService {

	public void write(CommunityDTO vo) throws Exception;

	CommunityDTO detail(int community_num) throws Exception;

	public void delete(int community_num) throws Exception;

	void modify(CommunityDTO vo) throws Exception;

	public void updateViewCount(int community_num) throws Exception;

	List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception; // 게시물 목록, 검색

	int countSearched(SearchCriteria searchCriteria) throws Exception; // 게시물 수 , 검색 수

	public void updateReplyCount(HashMap<String, Integer> updateHash) throws Exception; // 게시물 댓글 수 업데이트
}

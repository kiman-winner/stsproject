package com.cdm.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.page.SearchCriteria;

@Repository
public class CommunityDAOImpl implements CommunityDAO { //community관련 table 접근

	@Autowired
	private SqlSession session;

	@Override
	public void register(CommunityDTO communityDTO) throws Exception { // 게시물 등록
		session.insert("communityNS.register", communityDTO);
	}

	@Override
	public CommunityDTO detail(int community_num) { // 상세보기
		return session.selectOne("communityNS.detail", community_num);
	}

	@Override
	public void delete(int community_num) { // 게시글 삭제
		session.delete("communityNS.delete", community_num);
	}

	@Override
	public void modify(CommunityDTO communityDTO) throws Exception { // 게시글 수정
		session.update("communityNS.modify", communityDTO);
	}

	@Override
	public void updateViewCount(int community_num) throws Exception { // 조회수 수정
		session.update("communityNS.updateViewCount", community_num);
	}

	@Override
	public void updateReplyCount(HashMap<String, Integer> updateHash) throws Exception { // 댓글수 수정
		session.update("communityNS.updateReplyCount", updateHash);
	}

	@Override
	public List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception { // 게시물 검색
		return session.selectList("communityNS.listSearch", searchCriteria);
	}

	@Override
	public List<CommunityDTO> listSearch() throws Exception { // 메인 홈페이지 게시물
		return session.selectList("communityNS.list");
	}

	@Override
	public int countSearched(SearchCriteria searchCriteria) throws Exception { // 게시물 수
		return session.selectOne("communityNS.countSearched", searchCriteria);
	}

	@Override
	public void insertFile(Map<String, Object> map) throws Exception { // 파일 등록
		session.insert("communityNS.insertFile", map);
	}

	@Override
	public List<Map<String, Object>> selectFileList(int community_num) throws Exception { // 파일 리스트
		return session.selectList("communityNS.selectFileList", community_num);
	}

	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception { // 파일 정보
		return session.selectOne("communityNS.selectFileInfo", map);
	}

	@Override
	public void deleteFile(String FILE_NO) throws Exception { // 파일 삭제
		session.delete("communityNS.deleteFile", FILE_NO);
	}

	@Override
	public String searchDeleteFile(String FILE_NO) throws Exception {// 삭제할 첨부파일 저장명 조회
		return session.selectOne("communityNS.searchDeleteFile", FILE_NO);
	}

	@Override
	public List<String> searchDeleteFileAll(int community_num) throws Exception {// 삭제할 모든 파일 검색
		return session.selectList("communityNS.searchDeleteFileAll", community_num);
	}

}

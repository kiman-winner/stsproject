package com.cdm.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.page.Criteria;
import com.cdm.web.page.SearchCriteria;

@Repository
public class CommunityDAOImpl implements CommunityDAO{
	
	@Autowired
	private SqlSession session;
	@Override
	public void write(CommunityDTO vo) throws Exception {	//게시물 등록 
		
		session.insert("communityNS.write", vo);	
	}

	@Override
	public CommunityDTO detail(int community_num) {
		
		return session.selectOne("communityNS.detail",community_num);	
	}

	@Override
	public void delete(int community_num) {	//게시글 삭제 
		session.delete("communityNS.delete", community_num);	//게시글 삭제 
	}

	@Override
	public void modify(CommunityDTO vo) throws Exception {
		session.update("communityNS.modify", vo);
	}

	@Override
	public void updateViewCount(int community_num) throws Exception {
		session.update("communityNS.updateViewCount", community_num);
		
	}

	@Override
	public void updateReplyCount(HashMap<String, Integer> updateHash) throws Exception {
		session.update("communityNS.updateReplyCount",updateHash);
		
	}

	@Override
	public List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception {	//게시물 검색
		  return session.selectList("communityNS.listSearch", searchCriteria);
	}
	@Override
	public List<CommunityDTO> listSearch() throws Exception {	//메인 홈페이지 게시물 
		 return session.selectList("communityNS.list");
	}

	@Override
	public int countSearched(SearchCriteria searchCriteria) throws Exception {	//게시물 수 
		 return session.selectOne("communityNS.countSearched", searchCriteria);
	}

	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		session.insert("communityNS.insertFile", map);
	}

	@Override
	public List<Map<String, Object>> selectFileList(int community_num) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList("communityNS.selectFileList", community_num);
	}

	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return session.selectOne("communityNS.selectFileInfo", map);
		}


	@Override
	public void deleteFileAll(int community_num) throws Exception {
		session.delete("communityNS.deleteFileAll", community_num);	
		
	}

	@Override
	public void deleteFile(Map<String, Object> tempMap) throws Exception {
		session.delete("communityNS.deleteFile", tempMap);		
	}

	@Override
	public String searchDeleteFile(Map<String, Object> tempMap) throws Exception {//삭제할 첨부파일 저장명 조회
		return session.selectOne("communityNS.searchDeleteFile",tempMap);
	}

	@Override
	public List<String> searchDeleteFileAll(int community_num) throws Exception {
		return session.selectList("communityNS.searchDeleteFileAll", community_num);
	}



}

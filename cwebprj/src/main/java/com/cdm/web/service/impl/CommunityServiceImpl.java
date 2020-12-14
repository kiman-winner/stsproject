package com.cdm.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dao.ReplyDAO;
import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.page.SearchCriteria;
import com.cdm.web.service.CommunityService;
import com.cdm.web.util.FileUtils;

import oracle.jdbc.logging.annotations.Log;
@Service
public class CommunityServiceImpl implements CommunityService{
	
	@Autowired
	private CommunityDAO communityDAO;
	@Autowired
	private ReplyDAO replyDAO;
	
	@Autowired
	private FileUtils fileUtils;

	@Override
	public CommunityDTO detail(int community_num) throws Exception {	//상세보기
	
		communityDAO.updateViewCount(community_num);	//조회수 증가 
		
		return communityDAO.detail(community_num);
	}
	@Override
	public void delete(int community_num) throws Exception {	//게시글 삭제
		
		replyDAO.deleteAll(community_num);	// 댓글 모두 삭제 
		
		List<String> list = communityDAO.searchDeleteFileAll(community_num); //게시판 전체 첨부파일 검색
		
		for(int i=0;i<list.size();i++)
		fileUtils.deleteFile(list.get(i));//서버에서 첨부파일 삭제
		
		communityDAO.deleteFileAll(community_num);	//첨부파일 모두 삭제 
		
		communityDAO.delete(community_num);	
		
	}

	@Override
	public void updateReplyCount(HashMap<String, Integer> updateHash) throws Exception {
		communityDAO.updateReplyCount(updateHash);
		
	}

	@Override
	public List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception {	//게시물 목록, 검색
		 return communityDAO.listSearch(searchCriteria);
	}
	@Override
	public List<CommunityDTO> listSearch() throws Exception {	//메인 게시물 리스트
		 return communityDAO.listSearch();
	}

	@Override
	public int countSearched(SearchCriteria searchCriteria) throws Exception {//게시물 수, 검색 수 
		return communityDAO.countSearched(searchCriteria);
	}

	@Override
	public void write(CommunityDTO vo, MultipartHttpServletRequest mpRequest) throws Exception {	//게시물 작성
		communityDAO.write(vo);

		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest); 
		int size = list.size();
		
		for(int i=0; i<size; i++){ 
			communityDAO.insertFile(list.get(i)); 
		}
	}
	@Override
	public List<Map<String, Object>> selectFileList(int community_num) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectFileList(community_num);
	}
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return communityDAO.selectFileInfo(map);
	}
	@Override
	public void modify(CommunityDTO vo, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest)//게시물 수정
			throws Exception {
		
		communityDAO.modify(vo);	//게시물 업데이트 
		
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(vo, files, fileNames, mpRequest); //업데이트 할 것
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {	//새것 이면 삽입
				communityDAO.insertFile(tempMap);	
			}else {
				fileUtils.deleteFile(communityDAO.searchDeleteFile(tempMap));//삭제 파일명 검색 및 서버에서 삭제
				communityDAO.deleteFile(tempMap);	//삭제 
			}
		}
	}


}

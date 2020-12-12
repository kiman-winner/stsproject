package com.cdm.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.SearchCriteria;
import com.cdm.web.service.CommunityService;
import com.cdm.web.util.FileUtils;
@Service
public class CommunityServiceImpl implements CommunityService{
	
	@Autowired
	private CommunityDAO communityDAO;
	
	@Autowired
	private FileUtils fileUtils;

	@Override
	public CommunityDTO detail(int community_num) throws Exception {	//상세보기
		// TODO Auto-generated method stub
		return communityDAO.detail(community_num);
	}
	@Override
	public void delete(int community_num) throws Exception {	//게시글 삭제
		communityDAO.delete(community_num);
		
	}
	@Override
	public void modify(CommunityDTO vo) throws Exception {	//게시글 수정 
		communityDAO.modify(vo);
		
	}
	@Override
	public void updateViewCount(int community_num) throws Exception {	//조회수 증가
		// TODO Auto-generated method stub
		communityDAO.updateViewCount(community_num);
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
	public int countSearched(SearchCriteria searchCriteria) throws Exception {//게시물 수, 검색 수 
		return communityDAO.countSearched(searchCriteria);
	}

	@Override
	public void write(CommunityDTO vo, MultipartHttpServletRequest mpRequest) throws Exception {	//게시물 작성
		communityDAO.write(vo);

		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			System.out.println("insertfile 전 list안에 값"+ list.get(i).get("community_num"));
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



}

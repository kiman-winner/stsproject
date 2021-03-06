package com.cdm.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cdm.web.commons.util.FileUtils;
import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.page.SearchCriteria;
import com.cdm.web.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService {//커뮤니티 서비스

	@Autowired
	private CommunityDAO communityDAO;

	@Autowired
	private FileUtils fileUtils;

	@Transactional(isolation = Isolation.READ_COMMITTED) // 트랜잭션 커밋 되어 확정된 데이터만 읽도록 허용
	@Override
	public CommunityDTO detail(int community_num) throws Exception { // 상세보기

		communityDAO.updateViewCount(community_num); // 조회수 증가

		return communityDAO.detail(community_num);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void delete(int community_num) throws Exception { // 게시글 삭제
		List<String> list = communityDAO.searchDeleteFileAll(community_num); // 게시판 전체 첨부파일 검색
		
		communityDAO.delete(community_num);

		for (int i = 0; i < list.size(); i++)
			fileUtils.deleteFile(list.get(i));// 서버에서 첨부파일 삭제
	}

	@Override
	public List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception { // 게시물 목록, 검색
		return communityDAO.listSearch(searchCriteria);
	}

	@Override
	public List<CommunityDTO> listSearch() throws Exception { // 메인 게시물 리스트
		return communityDAO.listSearch();
	}

	@Override
	public int countSearched(SearchCriteria searchCriteria) throws Exception {// 게시물 수, 검색 수
		return communityDAO.countSearched(searchCriteria);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void register(CommunityDTO communityDTO, MultipartHttpServletRequest mpRequest) throws Exception { // 게시물 작성
		communityDAO.register(communityDTO);

		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(communityDTO, mpRequest);
		int size = list.size();

		for (int i = 0; i < size; i++) {
			communityDAO.insertFile(list.get(i));
		}
	}

	@Override
	public List<Map<String, Object>> selectFileList(int community_num) throws Exception {//파일 리스트
		return communityDAO.selectFileList(community_num);
	}

	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {//파일 정보 불러오기
		return communityDAO.selectFileInfo(map);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void modify(CommunityDTO communityDTO, String[] files, String[] fileNames,
			MultipartHttpServletRequest mpRequest)// 게시물 수정
			throws Exception {

		communityDAO.modify(communityDTO); // 게시물 업데이트

		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(communityDTO, files, fileNames, mpRequest); // 업데이트
																													// 할
																													// 것
		Map<String, Object> tempMap = null;
		String storedName=null;
		
		int size = list.size();
		for (int i = 0; i < size; i++) {
			tempMap = list.get(i);
			if (tempMap.get("IS_NEW").equals("Y")) { // 새로 등록 된 것 이면 삽입
				communityDAO.insertFile(tempMap);
			} else {
				storedName = communityDAO.searchDeleteFile((String) tempMap.get("FILE_NO"));	//삭제 파일 저장명 검색
				communityDAO.deleteFile((String) tempMap.get("FILE_NO"));
				fileUtils.deleteFile(storedName);// 서버에서 삭제
			}
		}
	}
}

package com.cdm.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cdm.web.commons.util.FileUtils;
import com.cdm.web.dao.NoticeDAO;
import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.page.SearchCriteria;
import com.cdm.web.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {// 고객센터 서비스

	@Autowired
	private NoticeDAO noticeDAO;

	@Autowired
	private FileUtils fileUtils;

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public NoticeDTO detail(int notice_num) throws Exception { // 상세보기
		noticeDAO.updateViewCount(notice_num); // 조회수 증가
		return noticeDAO.detail(notice_num);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void delete(int notice_num) throws Exception { // 삭제
		List<String> list = noticeDAO.searchDeleteFileAll(notice_num); // 공지사항 전체 첨부파일 검색

		noticeDAO.delete(notice_num);

		for (int i = 0; i < list.size(); i++)
			fileUtils.deleteFile(list.get(i));// 서버에서 첨부파일 삭제
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void modify(NoticeDTO noticeDTO, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest)// 공지사항
																														// 수정
			throws Exception {
		noticeDAO.modify(noticeDTO);

		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(noticeDTO, files, fileNames, mpRequest); // 업데이트
		String storedName = null; // 할
									// 것
		Map<String, Object> tempMap = null;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			tempMap = list.get(i);
			if (tempMap.get("IS_NEW").equals("Y")) { // 새것 이면 삽입
				noticeDAO.insertFile(tempMap);
			} else {
				storedName = noticeDAO.searchDeleteFile((String) tempMap.get("FILE_NO"));
				noticeDAO.deleteFile((String) tempMap.get("FILE_NO")); // 삭제
				fileUtils.deleteFile(storedName);// 삭제 파일명 검색 및 서버에서 삭제

			}
		}

	}

	@Override
	public List<NoticeDTO> listSearch(SearchCriteria searchCriteria) throws Exception { // 공지사항 검색
		return noticeDAO.listSearch(searchCriteria);
	}

	@Override
	public int countSearched(SearchCriteria searchCriteria) throws Exception { // 공지사항 갯수 구하기
		return noticeDAO.countSearched(searchCriteria);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public void register(NoticeDTO noticeDTO, MultipartHttpServletRequest mpRequest) throws Exception { // 공지사항 작성

		noticeDAO.register(noticeDTO);

		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(noticeDTO, mpRequest);

		int size = list.size();

		for (int i = 0; i < size; i++) {
			noticeDAO.insertFile(list.get(i));
		}
	}

	@Override
	public List<Map<String, Object>> selectFileList(int notice_num) throws Exception { // 첨부파일 리스트 검색
		return noticeDAO.selectFileList(notice_num);
	}

	@Override
	public Map<String, Object> selectFileInfo(String fileNo) throws Exception { // 파일 검색
		return noticeDAO.selectFileInfo(fileNo);
	}

	@Override
	public List<NoticeDTO> listSearch() throws Exception { // 홈페이지 공지사항 출력
		return noticeDAO.listSearch();
	}

}

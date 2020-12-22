package com.cdm.web.commons.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.NoticeDTO;

@Component("fileUtils")
public class FileUtils { //파일관리
	private static final String filePath = "D:\\Tools\\cwebprj\\file\\"; // 파일이 저장될 위치

	public List<Map<String, Object>> parseInsertFileInfo(CommunityDTO communityDTO,
			MultipartHttpServletRequest mpRequest) throws Exception { // 파일 저장

		Iterator<String> iterator = mpRequest.getFileNames(); // 이름 목록 저장

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); // 파일 정보를 담을 List
		Map<String, Object> listMap = null;

		int community_num = communityDTO.getCommunity_num();

		File file = new File(filePath);
		if (file.exists() == false) { // 폴더 생성
			file.mkdirs();
		}

		while (iterator.hasNext()) { // 데이터가 있다면 반복

			multipartFile = mpRequest.getFile(iterator.next());

			if (multipartFile.isEmpty() == false) { // 파일이 존재하면
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;

				file = new File(filePath + storedFileName); // 해당 경로 파일 생성
				multipartFile.transferTo(file); // 파일 저장

				listMap = new HashMap<String, Object>();
				listMap.put("community_num", community_num);
				listMap.put("ORG_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}

	// 파일 업데이트
	public List<Map<String, Object>> parseUpdateFileInfo(CommunityDTO communityDTO, String[] files, String[] fileNames,
			MultipartHttpServletRequest mpRequest) throws Exception {

		Iterator<String> iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		int community_num = communityDTO.getCommunity_num();

		while (iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());

			if (multipartFile.isEmpty() == false) { // 새로운 첨부 파일 등록 시
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				multipartFile.transferTo(new File(filePath + storedFileName));

				listMap = new HashMap<String, Object>();
				listMap.put("IS_NEW", "Y"); // 새로 등록되는 파일
				listMap.put("community_num", community_num);
				listMap.put("ORG_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		}

		if (files != null && fileNames != null) { // 기존 파일 리스트에서 변동된 파일
			for (int i = 0; i < fileNames.length; i++) {
				listMap = new HashMap<String, Object>();
				listMap.put("IS_NEW", "N");
				listMap.put("FILE_NO", files[i]);
				list.add(listMap);
			}
		}
		return list;
	}

	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public void deleteFile(String searchDeleteFile) { // 파일 삭제
		File file = new File(filePath + searchDeleteFile);
		if (file.exists() == true) {
			file.delete();
		}
	}

	public List<Map<String, Object>> parseInsertFileInfo(NoticeDTO noticeDTO, MultipartHttpServletRequest mpRequest)
			throws Exception, IOException {// 공지사항 삽입
		Iterator<String> iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		int community_num = noticeDTO.getNotice_num();

		File file = new File(filePath);
		if (file.exists() == false) { // 폴더 생성
			file.mkdirs();
		}

		while (iterator.hasNext()) { // 데이터가 있다면 반복

			multipartFile = mpRequest.getFile(iterator.next());

			if (multipartFile.isEmpty() == false) { // 파일이 존재하면
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;

				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file); // 파일 저장

				listMap = new HashMap<String, Object>();
				listMap.put("notice_num", community_num);
				listMap.put("ORG_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}

	public List<Map<String, Object>> parseUpdateFileInfo(NoticeDTO noticeDTO, String[] files, String[] fileNames,
			MultipartHttpServletRequest mpRequest) throws Exception, IOException {// 공지사항 업데이트

		Iterator<String> iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		int community_num = noticeDTO.getNotice_num();

		while (iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());

			if (multipartFile.isEmpty() == false) { // 새로운 첨부 파일 등록 시
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				multipartFile.transferTo(new File(filePath + storedFileName));
				listMap = new HashMap<String, Object>();
				listMap.put("IS_NEW", "Y");
				listMap.put("notice_num", community_num);
				listMap.put("ORG_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		}

		if (files != null && fileNames != null) {
			for (int i = 0; i < fileNames.length; i++) {
				listMap = new HashMap<String, Object>();
				listMap.put("IS_NEW", "N");
				listMap.put("FILE_NO", files[i]);
				list.add(listMap); // 삭제할 파일
			}
		}
		return list;
	}
}

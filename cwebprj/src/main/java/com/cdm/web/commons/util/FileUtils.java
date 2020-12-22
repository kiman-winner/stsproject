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
public class FileUtils { //���ϰ���
	private static final String filePath = "D:\\Tools\\cwebprj\\file\\"; // ������ ����� ��ġ

	public List<Map<String, Object>> parseInsertFileInfo(CommunityDTO communityDTO,
			MultipartHttpServletRequest mpRequest) throws Exception { // ���� ����

		Iterator<String> iterator = mpRequest.getFileNames(); // �̸� ��� ����

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); // ���� ������ ���� List
		Map<String, Object> listMap = null;

		int community_num = communityDTO.getCommunity_num();

		File file = new File(filePath);
		if (file.exists() == false) { // ���� ����
			file.mkdirs();
		}

		while (iterator.hasNext()) { // �����Ͱ� �ִٸ� �ݺ�

			multipartFile = mpRequest.getFile(iterator.next());

			if (multipartFile.isEmpty() == false) { // ������ �����ϸ�
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;

				file = new File(filePath + storedFileName); // �ش� ��� ���� ����
				multipartFile.transferTo(file); // ���� ����

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

	// ���� ������Ʈ
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

			if (multipartFile.isEmpty() == false) { // ���ο� ÷�� ���� ��� ��
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				multipartFile.transferTo(new File(filePath + storedFileName));

				listMap = new HashMap<String, Object>();
				listMap.put("IS_NEW", "Y"); // ���� ��ϵǴ� ����
				listMap.put("community_num", community_num);
				listMap.put("ORG_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		}

		if (files != null && fileNames != null) { // ���� ���� ����Ʈ���� ������ ����
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

	public void deleteFile(String searchDeleteFile) { // ���� ����
		File file = new File(filePath + searchDeleteFile);
		if (file.exists() == true) {
			file.delete();
		}
	}

	public List<Map<String, Object>> parseInsertFileInfo(NoticeDTO noticeDTO, MultipartHttpServletRequest mpRequest)
			throws Exception, IOException {// �������� ����
		Iterator<String> iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		int community_num = noticeDTO.getNotice_num();

		File file = new File(filePath);
		if (file.exists() == false) { // ���� ����
			file.mkdirs();
		}

		while (iterator.hasNext()) { // �����Ͱ� �ִٸ� �ݺ�

			multipartFile = mpRequest.getFile(iterator.next());

			if (multipartFile.isEmpty() == false) { // ������ �����ϸ�
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;

				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file); // ���� ����

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
			MultipartHttpServletRequest mpRequest) throws Exception, IOException {// �������� ������Ʈ

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

			if (multipartFile.isEmpty() == false) { // ���ο� ÷�� ���� ��� ��
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
				list.add(listMap); // ������ ����
			}
		}
		return list;
	}
}

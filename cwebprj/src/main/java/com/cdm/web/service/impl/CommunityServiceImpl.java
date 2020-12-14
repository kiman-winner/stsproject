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
	public CommunityDTO detail(int community_num) throws Exception {	//�󼼺���
	
		communityDAO.updateViewCount(community_num);	//��ȸ�� ���� 
		
		return communityDAO.detail(community_num);
	}
	@Override
	public void delete(int community_num) throws Exception {	//�Խñ� ����
		
		replyDAO.deleteAll(community_num);	// ��� ��� ���� 
		
		List<String> list = communityDAO.searchDeleteFileAll(community_num); //�Խ��� ��ü ÷������ �˻�
		
		for(int i=0;i<list.size();i++)
		fileUtils.deleteFile(list.get(i));//�������� ÷������ ����
		
		communityDAO.deleteFileAll(community_num);	//÷������ ��� ���� 
		
		communityDAO.delete(community_num);	
		
	}

	@Override
	public void updateReplyCount(HashMap<String, Integer> updateHash) throws Exception {
		communityDAO.updateReplyCount(updateHash);
		
	}

	@Override
	public List<CommunityDTO> listSearch(SearchCriteria searchCriteria) throws Exception {	//�Խù� ���, �˻�
		 return communityDAO.listSearch(searchCriteria);
	}
	@Override
	public List<CommunityDTO> listSearch() throws Exception {	//���� �Խù� ����Ʈ
		 return communityDAO.listSearch();
	}

	@Override
	public int countSearched(SearchCriteria searchCriteria) throws Exception {//�Խù� ��, �˻� �� 
		return communityDAO.countSearched(searchCriteria);
	}

	@Override
	public void write(CommunityDTO vo, MultipartHttpServletRequest mpRequest) throws Exception {	//�Խù� �ۼ�
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
	public void modify(CommunityDTO vo, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest)//�Խù� ����
			throws Exception {
		
		communityDAO.modify(vo);	//�Խù� ������Ʈ 
		
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(vo, files, fileNames, mpRequest); //������Ʈ �� ��
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {	//���� �̸� ����
				communityDAO.insertFile(tempMap);	
			}else {
				fileUtils.deleteFile(communityDAO.searchDeleteFile(tempMap));//���� ���ϸ� �˻� �� �������� ����
				communityDAO.deleteFile(tempMap);	//���� 
			}
		}
	}


}

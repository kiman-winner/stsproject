package com.cdm.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdm.web.dao.NoticeDAO;
import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.page.SearchCriteria;

@Repository
public class NoticeDAOImpl implements NoticeDAO{
	@Autowired
	private SqlSession session;
	
	@Override
	public void updateViewCount(int notice_num) throws Exception {	//��ȸ�� ����
		session.update("noticeNS.updateViewCount", notice_num);
	}

	@Override
	public NoticeDTO detail(int notice_num) throws Exception {	//�������� �󼼺��� 
		return session.selectOne("noticeNS.detail",notice_num);	
	}

	@Override
	public List<String> searchDeleteFileAll(int notice_num) throws Exception {	//������ ���ϵ� �˻� 
		return session.selectList("noticeNS.searchDeleteFileAll", notice_num);
	}

	@Override
	public void delete(int notice_num) throws Exception {//�������� ���� 
		session.delete("noticeNS.delete", notice_num);	
	}

	@Override
	public List<NoticeDTO> listSearch(SearchCriteria searchCriteria) throws Exception {//�������� ����Ʈ 
		 return session.selectList("noticeNS.listSearch", searchCriteria);
	}

	@Override
	public int countSearched(SearchCriteria searchCriteria) throws Exception {//�Խù� ��
		 return session.selectOne("noticeNS.countSearched", searchCriteria);
	}

	@Override
	public void register(NoticeDTO noticeDTO) throws Exception {//�������� ��� 
		session.insert("noticeNS.register", noticeDTO);	
	}

	@Override
	public void insertFile(Map<String, Object> map) throws Exception {//���� ÷��
		session.insert("noticeNS.insertFile", map);
	}

	@Override
	public List<Map<String, Object>> selectFileList(int notice_num) throws Exception {//���� ����Ʈ
		return session.selectList("noticeNS.selectFileList", notice_num);
	}

	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {//���� �ٿ� 
		return session.selectOne("noticeNS.selectFileInfo", map);
	}

	@Override
	public List<NoticeDTO> listSearch() throws Exception {	//Ȩ������ �������� ��� 
		 return session.selectList("noticeNS.list");
	}

	@Override
	public String searchDeleteFile(Map<String, Object> tempMap) throws Exception {//������ ���� �˻�
		return session.selectOne("noticeNS.searchDeleteFile",tempMap);
	}

	@Override
	public void deleteFile(Map<String, Object> tempMap) throws Exception {//���� ����
		session.delete("noticeNS.deleteFile", tempMap);	
	}

	@Override
	public void modify(NoticeDTO noticeDTO) throws Exception {
		session.update("noticeNS.modify", noticeDTO);
	}
}

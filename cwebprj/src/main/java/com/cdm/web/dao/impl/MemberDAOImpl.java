package com.cdm.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdm.web.dao.MemberDAO;
import com.cdm.web.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {//member���� table ����

	@Autowired
	private SqlSession session;

	@Override
	public void join(MemberDTO memberDTO) throws Exception { // ȸ������ ó��
		session.insert("memberNS.join", memberDTO);
	}

	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception { // �α��� ó��
		MemberDTO member = session.selectOne("memberNS.login", memberDTO);
		return member;
	}

	@Override
	public int idCheck(String member_id) { // ���̵� �ߺ�Ȯ��
		int count;
		count = session.selectOne("memberNS.idcheck", member_id);
		return count;
	}

	@Override
	public List<String> findId(MemberDTO memberDTO) throws Exception {// ���̵� ã��
		return session.selectList("memberNS.findId", memberDTO);
	}

	@Override
	public void updateMember(MemberDTO memberDTO) throws Exception {// �������� ����
		session.update("memberNS.updateMember", memberDTO);
	}

	@Override
	public void updatepwd(MemberDTO memberDTO) throws Exception { // ��й�ȣ ����
		session.update("memberNS.updatepwd", memberDTO);
	}

	@Override
	public void deleteMember(String member_id) throws Exception { // ȸ�� Ż��
		session.delete("memberNS.deleteMember", member_id);
	}

	@Override
	public String pwdcheck(MemberDTO memberDTO) throws Exception { // ��й�ȣ Ȯ��
		return session.selectOne("memberNS.pwdcheck", memberDTO);
	}

	@Override
	public MemberDTO checkUserWithSessionKey(String value) throws Exception { // ���� Id ����� ��ȸ
		return session.selectOne("memberNS.checkUserWithSessionKey", value);
	}

	@Override
	public void keepLogin(Map<String, Object> paramMap) throws Exception { // �ڵ��α��� ����
		session.update("memberNS.keepLogin", paramMap);
	}
}

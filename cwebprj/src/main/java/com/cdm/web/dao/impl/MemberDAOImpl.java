package com.cdm.web.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdm.web.dao.MemberDAO;
import com.cdm.web.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private SqlSession session;
	
	@Override
	public void join(MemberDTO vo) throws Exception {	//ȸ������ ó��
		// TODO Auto-generated method stub
		session.insert("memberNS.join", vo);	
	}

	@Override
	public MemberDTO login(MemberDTO vo) throws Exception {		//�α��� ó�� 
		// TODO Auto-generated method stub
	
		
		MemberDTO member = session.selectOne("memberNS.login",vo);
		return member;
	}

	@Override
	public int idCheck(String member_id) {
		// TODO Auto-generated method stub
		int count;
		
		count = session.selectOne("memberNS.idcheck",member_id);
		
		return count;
	}

	@Override
	public String findId(MemberDTO vo) throws Exception {//���̵� ã�� 
		return session.selectOne("memberNS.findId",vo);
	}

	@Override
	public String findPwd(MemberDTO vo) throws Exception {	//��й�ȣ ã�� 
		return session.selectOne("memberNS.findPwd",vo);
	}
}

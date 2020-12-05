package com.cdm.web.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdm.web.dao.MemberDAO;
import com.cdm.web.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private SqlSession session;
	
	@Override
	public void join(MemberVO vo) throws Exception {	//회원가입 처리
		// TODO Auto-generated method stub
		session.insert("memberNS.join", vo);	
	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception {		//로그인 처리 
		// TODO Auto-generated method stub
	
		
		MemberVO member = session.selectOne("memberNS.login",vo);
		return member;
	}

	@Override
	public int idCheck(String member_id) {
		// TODO Auto-generated method stub
		int count;
		
		count = session.selectOne("memberNS.idcheck",member_id);
		
		return count;
	}
}

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
	public void register(MemberVO vo) throws Exception {	//회원가입 처리
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception {		//로그인 처리 
		// TODO Auto-generated method stub
	
		
		MemberVO member = session.selectOne("memberNS.login",vo);
		return member;
	}
}

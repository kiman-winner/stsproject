package com.cdm.web.dao.impl;

import java.util.List;
import java.util.Map;

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
	public void join(MemberDTO memberDTO) throws Exception {	//회원가입 처리
		// TODO Auto-generated method stub
		session.insert("memberNS.join", memberDTO);	
	}

	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception {		//로그인 처리 
		// TODO Auto-generated method stub
	
		
		MemberDTO member = session.selectOne("memberNS.login",memberDTO);
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
	public List<String> findId(MemberDTO memberDTO) throws Exception {//아이디 찾기 
		return session.selectList("memberNS.findId",memberDTO);
	}

	@Override
	public String findPwd(MemberDTO memberDTO) throws Exception {	//비밀번호 찾기 
		return session.selectOne("memberNS.findPwd",memberDTO);
	}

	@Override
	public void updateMember(MemberDTO memberDTO) throws Exception {//개인정보 수정
		session.update("memberNS.updateMember", memberDTO);	
		
	}

	@Override
	public void updatepwd(MemberDTO memberDTO) throws Exception {	//비밀번호 변경
		session.update("memberNS.updatepwd", memberDTO);			
	}

	@Override
	public void deleteMember(String member_id) throws Exception {
		session.delete("memberNS.deleteMember",member_id);
	}
}

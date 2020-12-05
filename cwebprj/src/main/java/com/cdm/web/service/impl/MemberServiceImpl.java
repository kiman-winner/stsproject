package com.cdm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdm.web.dao.MemberDAO;
import com.cdm.web.service.MemberService;
import com.cdm.web.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void join(MemberVO vo) throws Exception {	//회원가입 DAO를 통한 데이터 접근
		// TODO Auto-generated method stub
		memberDAO.join(vo);	//DAO의 메서드를 통해 sqlsession 실행
	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception { //로그인 
		
		return memberDAO.login(vo);
	}

	@Override
	public int idCheck(String member_id) throws Exception {	//아이디 중복 체크 
		// TODO Auto-generated method stub
		
		return memberDAO.idCheck(member_id);
	}

}

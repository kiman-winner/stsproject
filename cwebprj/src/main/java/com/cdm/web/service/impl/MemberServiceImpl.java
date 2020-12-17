package com.cdm.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dao.MemberDAO;
import com.cdm.web.dao.ReplyDAO;
import com.cdm.web.dto.MemberDTO;
import com.cdm.web.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void join(MemberDTO memberDTO) throws Exception {	//회원가입 DAO를 통한 데이터 접근
		// TODO Auto-generated method stub
		memberDAO.join(memberDTO);	//DAO의 메서드를 통해 sqlsession 실행
	}

	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception { //로그인 
		
		return memberDAO.login(memberDTO);
	}

	@Override
	public int idCheck(String member_id) throws Exception {	//아이디 중복 체크 
		// TODO Auto-generated method stub
		
		return memberDAO.idCheck(member_id);
	}

	@Override
	public List<String> findId(MemberDTO memberDTO) throws Exception {	//아이디 찾기 
		return memberDAO.findId(memberDTO);
	}

	@Override
	public String findpwd(MemberDTO memberDTO) throws Exception {	//비밀번호 찾기
		return memberDAO.findPwd(memberDTO);
	}

	@Override
	public void updateMember(MemberDTO memberDTO) throws Exception {	//개인정보 수정
		memberDAO.updateMember(memberDTO);
	}

	@Override
	public void updatepwd(MemberDTO memberDTO) throws Exception {
		memberDAO.updatepwd(memberDTO);
	}

	@Override
	public void deleteMember(String member_id) throws Exception {//회원 탈퇴 
		memberDAO.deleteMember(member_id);
	}

	

}

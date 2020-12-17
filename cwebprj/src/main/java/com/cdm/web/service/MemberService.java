package com.cdm.web.service;

import java.util.List;
import java.util.Map;

import com.cdm.web.dto.MemberDTO;

public interface MemberService {

	public void join(MemberDTO memberDTO) throws Exception;	//회원가입
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception;	//로그인
	
	public int idCheck(String member_id) throws Exception;	//아이디 중복 확인

	public List<String> findId(MemberDTO memberDTO) throws Exception;//아이디 찾기 

	public String findpwd(MemberDTO memberDTO)throws Exception;//비밀번호 찾기 

	public void updateMember(MemberDTO memberDTO)throws Exception;// 개인정보 변경

	public void updatepwd(MemberDTO memberDTO)throws Exception;// 비밀번호 변경

	public void deleteMember(String member_id)throws Exception;// 회원 탈퇴
	
}

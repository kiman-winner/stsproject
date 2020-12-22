package com.cdm.web.service;

import java.util.List;

import com.cdm.web.dto.MemberDTO;

public interface MemberService {

	public void join(MemberDTO memberDTO) throws Exception;	//회원가입
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception;	//로그인
	
	public int idCheck(String member_id) throws Exception;	//아이디 중복 확인

	public List<String> findId(MemberDTO memberDTO) throws Exception;//아이디 찾기 

	public void updateMember(MemberDTO memberDTO)throws Exception;// 개인정보 변경

	public void deleteMember(String member_id)throws Exception;// 회원 탈퇴

	public void updatepwd(MemberDTO memberDTO, String newpassword) throws Exception;// 비밀번호 변경

	public void resetPwd(MemberDTO memberDTO)throws Exception ;// 비밀번호 리셋

	String pwdcheck(MemberDTO memberDTO) throws Exception;// 비밀번호 체크
	
	void keepLogin(String member_id, String sessionId) throws Exception;	//세션 리밋 설정

	MemberDTO checkLoginBefore(String value) throws Exception;	//쿠키 로그인 
}

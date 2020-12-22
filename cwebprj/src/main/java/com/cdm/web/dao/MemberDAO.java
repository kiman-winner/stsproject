package com.cdm.web.dao;

import java.util.List;
import java.util.Map;

import com.cdm.web.dto.MemberDTO;

public interface MemberDAO {

	public void join(MemberDTO memberDTO) throws Exception; // 회원 가입

	public MemberDTO login(MemberDTO memberDTO) throws Exception;// 로그인

	public int idCheck(String member_id) throws Exception;// 아이디 중복확인

	public List<String> findId(MemberDTO memberDTO) throws Exception;// 아이디 찾기

	public void updateMember(MemberDTO memberDTO) throws Exception;// 개인정보 수정

	public void updatepwd(MemberDTO memberDTO) throws Exception;// 비밀번호 변경

	public void deleteMember(String member_id) throws Exception;// 회원 탈퇴

	public String pwdcheck(MemberDTO memberDTO) throws Exception;// 비밀번호 체크

	void keepLogin(Map<String, Object> paramMap) throws Exception;// 로그인 유지 처리

	MemberDTO checkUserWithSessionKey(String value) throws Exception; // 세션키 검증

}

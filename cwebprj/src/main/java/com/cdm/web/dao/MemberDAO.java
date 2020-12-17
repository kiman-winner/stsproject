package com.cdm.web.dao;

import java.util.List;
import java.util.Map;

import com.cdm.web.dto.MemberDTO;

public interface MemberDAO {
	
	//회원가입
	public void join(MemberDTO memberDTO) throws Exception ;
	//로그인
	public MemberDTO login(MemberDTO memberDTO) throws Exception ;
	
	public int idCheck(String member_id) throws Exception;

	public List<String> findId(MemberDTO memberDTO) throws Exception;//아이디 찾기 

	public String findPwd(MemberDTO memberDTO)throws Exception;//비밀번호 찾기

	public void updateMember(MemberDTO memberDTO)throws Exception;//개인정보 수정

	public void updatepwd(MemberDTO memberDTO)throws Exception;//비밀번호 변경

	public void deleteMember(String member_id)throws Exception;//회원 탈퇴
	
}

package com.cdm.web.dao;

import com.cdm.web.dto.MemberDTO;

public interface MemberDAO {
	
	//회원가입
	public void join(MemberDTO vo) throws Exception ;
	
	//로그인
	public MemberDTO login(MemberDTO vo) throws Exception ;

	
	public int idCheck(String member_id) throws Exception;

	public String findId(MemberDTO vo) throws Exception;//아이디 찾기 

	public String findPwd(MemberDTO vo)throws Exception;//비밀번호 찾기
	
}

package com.cdm.web.service;

import com.cdm.web.dto.MemberDTO;

public interface MemberService {

	public void join(MemberDTO vo) throws Exception;
	
	public MemberDTO login(MemberDTO vo) throws Exception;
	
	public int idCheck(String member_id) throws Exception;

	public String findId(MemberDTO vo) throws Exception;//아이디 찾기 

	public String findpwd(MemberDTO vo)throws Exception;//비밀번호 찾기 

	
}

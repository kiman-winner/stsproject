package com.cdm.web.dao;

import com.cdm.web.vo.MemberVO;

public interface MemberDAO {
	
	//회원가입
	public void join(MemberVO vo) throws Exception ;
	
	//로그인
	public MemberVO login(MemberVO vo) throws Exception ;

	
	public int idCheck(String member_id) throws Exception;
	
}

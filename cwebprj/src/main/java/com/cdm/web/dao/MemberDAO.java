package com.cdm.web.dao;

import com.cdm.web.vo.MemberVO;

public interface MemberDAO {
	
	//회원가입
	public void register(MemberVO vo) throws Exception ;
	
	//로그인
	public MemberVO login(MemberVO vo) throws Exception ;
	
}

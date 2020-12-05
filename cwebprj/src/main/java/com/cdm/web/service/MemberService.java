package com.cdm.web.service;

import com.cdm.web.vo.MemberVO;

public interface MemberService {

	public void join(MemberVO vo) throws Exception;
	
	public MemberVO login(MemberVO vo) throws Exception;
	
	public int idCheck(String member_id) throws Exception;
}

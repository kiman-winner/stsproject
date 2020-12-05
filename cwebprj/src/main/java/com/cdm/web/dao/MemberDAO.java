package com.cdm.web.dao;

import com.cdm.web.vo.MemberVO;

public interface MemberDAO {
	
	//ȸ������
	public void join(MemberVO vo) throws Exception ;
	
	//�α���
	public MemberVO login(MemberVO vo) throws Exception ;

	
	public int idCheck(String member_id) throws Exception;
	
}

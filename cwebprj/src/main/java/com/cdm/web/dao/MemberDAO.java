package com.cdm.web.dao;

import com.cdm.web.dto.MemberDTO;

public interface MemberDAO {
	
	//ȸ������
	public void join(MemberDTO vo) throws Exception ;
	
	//�α���
	public MemberDTO login(MemberDTO vo) throws Exception ;

	
	public int idCheck(String member_id) throws Exception;
	
}

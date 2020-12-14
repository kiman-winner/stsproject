package com.cdm.web.dao;

import com.cdm.web.dto.MemberDTO;

public interface MemberDAO {
	
	//ȸ������
	public void join(MemberDTO vo) throws Exception ;
	
	//�α���
	public MemberDTO login(MemberDTO vo) throws Exception ;

	
	public int idCheck(String member_id) throws Exception;

	public String findId(MemberDTO vo) throws Exception;//���̵� ã�� 

	public String findPwd(MemberDTO vo)throws Exception;//��й�ȣ ã��
	
}

package com.cdm.web.dao;

import java.util.List;
import java.util.Map;

import com.cdm.web.dto.MemberDTO;

public interface MemberDAO {
	
	//ȸ������
	public void join(MemberDTO memberDTO) throws Exception ;
	//�α���
	public MemberDTO login(MemberDTO memberDTO) throws Exception ;
	
	public int idCheck(String member_id) throws Exception;

	public List<String> findId(MemberDTO memberDTO) throws Exception;//���̵� ã�� 

	public String findPwd(MemberDTO memberDTO)throws Exception;//��й�ȣ ã��

	public void updateMember(MemberDTO memberDTO)throws Exception;//�������� ����

	public void updatepwd(MemberDTO memberDTO)throws Exception;//��й�ȣ ����

	public void deleteMember(String member_id)throws Exception;//ȸ�� Ż��
	
}

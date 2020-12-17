package com.cdm.web.service;

import java.util.List;
import java.util.Map;

import com.cdm.web.dto.MemberDTO;

public interface MemberService {

	public void join(MemberDTO memberDTO) throws Exception;	//ȸ������
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception;	//�α���
	
	public int idCheck(String member_id) throws Exception;	//���̵� �ߺ� Ȯ��

	public List<String> findId(MemberDTO memberDTO) throws Exception;//���̵� ã�� 

	public void updateMember(MemberDTO memberDTO)throws Exception;// �������� ����

	public void deleteMember(String member_id)throws Exception;// ȸ�� Ż��

	public void updatepwd(MemberDTO memberDTO, String newpassword) throws Exception;// ��й�ȣ ����

	public void resetPwd(MemberDTO memberDTO)throws Exception ;// ��й�ȣ ����

	String pwdcheck(MemberDTO memberDTO) throws Exception;// ��й�ȣ üũ
}

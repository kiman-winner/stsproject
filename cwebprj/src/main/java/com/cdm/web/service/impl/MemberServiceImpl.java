package com.cdm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdm.web.dao.CommunityDAO;
import com.cdm.web.dao.MemberDAO;
import com.cdm.web.dao.ReplyDAO;
import com.cdm.web.dto.MemberDTO;
import com.cdm.web.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private CommunityDAO communityDAO;
	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public void join(MemberDTO memberDTO) throws Exception {	//ȸ������ DAO�� ���� ������ ����
		// TODO Auto-generated method stub
		memberDAO.join(memberDTO);	//DAO�� �޼��带 ���� sqlsession ����
	}

	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception { //�α��� 
		
		return memberDAO.login(memberDTO);
	}

	@Override
	public int idCheck(String member_id) throws Exception {	//���̵� �ߺ� üũ 
		// TODO Auto-generated method stub
		
		return memberDAO.idCheck(member_id);
	}

	@Override
	public String findId(MemberDTO memberDTO) throws Exception {	//���̵� ã�� 
		// TODO Auto-generated method stub
		return memberDAO.findId(memberDTO);
	}

	@Override
	public String findpwd(MemberDTO memberDTO) throws Exception {	//��й�ȣ ã��
		return memberDAO.findPwd(memberDTO);
	}

	@Override
	public void updateMember(MemberDTO memberDTO) throws Exception {	//�������� ����
		memberDAO.updateMember(memberDTO);
	}

	@Override
	public void updatepwd(MemberDTO memberDTO) throws Exception {
		memberDAO.updatepwd(memberDTO);
	}

	@Override
	public void deleteMember(String member_id) throws Exception {//ȸ�� Ż�� 
		memberDAO.deleteMember(member_id);
	}

	

}

package com.cdm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdm.web.dao.MemberDAO;
import com.cdm.web.dto.MemberDTO;
import com.cdm.web.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void join(MemberDTO vo) throws Exception {	//ȸ������ DAO�� ���� ������ ����
		// TODO Auto-generated method stub
		memberDAO.join(vo);	//DAO�� �޼��带 ���� sqlsession ����
	}

	@Override
	public MemberDTO login(MemberDTO vo) throws Exception { //�α��� 
		
		return memberDAO.login(vo);
	}

	@Override
	public int idCheck(String member_id) throws Exception {	//���̵� �ߺ� üũ 
		// TODO Auto-generated method stub
		
		return memberDAO.idCheck(member_id);
	}

	@Override
	public String findId(MemberDTO vo) throws Exception {	//���̵� ã�� 
		// TODO Auto-generated method stub
		return memberDAO.findId(vo);
	}

	@Override
	public String findpwd(MemberDTO vo) throws Exception {	//��й�ȣ ã��
		return memberDAO.findPwd(vo);
	}

	

}

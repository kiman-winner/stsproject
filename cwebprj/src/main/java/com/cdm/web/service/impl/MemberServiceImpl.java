package com.cdm.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdm.web.commons.util.MailUtil;
import com.cdm.web.dao.MemberDAO;
import com.cdm.web.dto.MemberDTO;
import com.cdm.web.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Override
	public void join(MemberDTO memberDTO) throws Exception {	//ȸ������ DAO�� ���� ������ ����
		
		String inputPass = memberDTO.getPassword();
		String pass = passEncoder.encode(inputPass);	//��ȣȭ 
		memberDTO.setPassword(pass);
		
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
	public List<String> findId(MemberDTO memberDTO) throws Exception {	//���̵� ã�� 
		return memberDAO.findId(memberDTO);
	}

	@Override
	public void updateMember(MemberDTO memberDTO) throws Exception {	//�������� ����
		
		memberDAO.updateMember(memberDTO);
	}

	@Override
	public void deleteMember(String member_id) throws Exception {//ȸ�� Ż�� 
		memberDAO.deleteMember(member_id);
	}

	@Override
	public void updatepwd(MemberDTO memberDTO, String newpassword) throws Exception {	//��й�ȣ ����
		
		String inputPass = newpassword;
		String pass = passEncoder.encode(inputPass);	//��ȣȭ 
		memberDTO.setPassword(pass);	
		
		memberDAO.updatepwd(memberDTO);
	}

	@Override
	public String pwdcheck(MemberDTO memberDTO) throws Exception {
		return memberDAO.pwdcheck(memberDTO);
	}

	@Override
	public void resetPwd(MemberDTO memberDTO) throws Exception {
		   // �ӽ� ��й�ȣ ����
			String pw = getRamdomPassword(10);	//�ӽ� ��й�ȣ ����
		
		MailUtil.sendMail(memberDTO.getEmail(), memberDTO.getMember_id(),pw);	//�ʱ�ȭ ���� �߼� 
		
		String inputPass = pw;
		String pass = passEncoder.encode(inputPass);	//��ȣȭ 
		memberDTO.setPassword(pass);	
		
		memberDAO.updatepwd(memberDTO);	//��й�ȣ ������Ʈ
	}
	
	public String getRamdomPassword(int len) {
		  char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
				'U', 'V', 'W', 'X', 'Y', 'Z' };

		  int idx = 0;
		  StringBuffer sb = new StringBuffer();
		  
		  for (int i = 0; i < len; i++) {
			
			  idx = (int) (charSet.length * Math.random()); // 36 * ������ ������  Int�� ���� (�Ҽ�������)
			  sb.append(charSet[idx]);
		  }

		  return sb.toString();
		}
}

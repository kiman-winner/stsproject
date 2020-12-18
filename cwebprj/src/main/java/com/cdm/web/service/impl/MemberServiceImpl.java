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
	public void join(MemberDTO memberDTO) throws Exception {	//회원가입 DAO를 통한 데이터 접근
		
		String inputPass = memberDTO.getPassword();
		String pass = passEncoder.encode(inputPass);	//암호화 
		memberDTO.setPassword(pass);
		
		memberDAO.join(memberDTO);	//DAO의 메서드를 통해 sqlsession 실행
	}

	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception { //로그인 
		
		return memberDAO.login(memberDTO);
	}

	@Override
	public int idCheck(String member_id) throws Exception {	//아이디 중복 체크 
		// TODO Auto-generated method stub
		
		return memberDAO.idCheck(member_id);
	}

	@Override
	public List<String> findId(MemberDTO memberDTO) throws Exception {	//아이디 찾기 
		return memberDAO.findId(memberDTO);
	}

	@Override
	public void updateMember(MemberDTO memberDTO) throws Exception {	//개인정보 수정
		
		memberDAO.updateMember(memberDTO);
	}

	@Override
	public void deleteMember(String member_id) throws Exception {//회원 탈퇴 
		memberDAO.deleteMember(member_id);
	}

	@Override
	public void updatepwd(MemberDTO memberDTO, String newpassword) throws Exception {	//비밀번호 변경
		
		String inputPass = newpassword;
		String pass = passEncoder.encode(inputPass);	//암호화 
		memberDTO.setPassword(pass);	
		
		memberDAO.updatepwd(memberDTO);
	}

	@Override
	public String pwdcheck(MemberDTO memberDTO) throws Exception {
		return memberDAO.pwdcheck(memberDTO);
	}

	@Override
	public void resetPwd(MemberDTO memberDTO) throws Exception {
		   // 임시 비밀번호 생성
			String pw = getRamdomPassword(10);	//임시 비밀번호 생성
		
		MailUtil.sendMail(memberDTO.getEmail(), memberDTO.getMember_id(),pw);	//초기화 메일 발송 
		
		String inputPass = pw;
		String pass = passEncoder.encode(inputPass);	//암호화 
		memberDTO.setPassword(pass);	
		
		memberDAO.updatepwd(memberDTO);	//비밀번호 업데이트
	}
	
	public String getRamdomPassword(int len) {
		  char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
				'U', 'V', 'W', 'X', 'Y', 'Z' };

		  int idx = 0;
		  StringBuffer sb = new StringBuffer();
		  
		  for (int i = 0; i < len; i++) {
			
			  idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를  Int로 추출 (소숫점제거)
			  sb.append(charSet[idx]);
		  }

		  return sb.toString();
		}
}

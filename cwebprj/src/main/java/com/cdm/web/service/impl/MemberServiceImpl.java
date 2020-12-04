package com.cdm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdm.web.dao.MemberDAO;
import com.cdm.web.service.MemberService;
import com.cdm.web.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void register(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception { //·Î±×ÀÎ 
		
		return memberDAO.login(vo);
	}

}

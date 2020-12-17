package com.cdm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.service.CommunityService;
import com.cdm.web.service.NoticeService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private CommunityService communityService;
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("index")
	public ModelAndView index() throws Exception {	//���� Ȩ������ 

		ModelAndView mv = new ModelAndView();
		
		List<CommunityDTO> communitylist = communityService.listSearch(); //���ο� ����Ʈ ��� �����ε�
		
		mv.addObject("communitylist", communitylist); 
		
		List<NoticeDTO> noticelist = noticeService.listSearch(); //�������� ���
		mv.addObject("noticelist",noticelist);
		mv.setViewName("index"); 
		
		
		return mv;
	}
}
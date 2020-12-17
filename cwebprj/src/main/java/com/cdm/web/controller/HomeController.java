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
	public ModelAndView index() throws Exception {	//메인 홈페이지 

		ModelAndView mv = new ModelAndView();
		
		List<CommunityDTO> communitylist = communityService.listSearch(); //메인에 리스트 출력 오버로딩
		
		mv.addObject("communitylist", communitylist); 
		
		List<NoticeDTO> noticelist = noticeService.listSearch(); //공지사항 출력
		mv.addObject("noticelist",noticelist);
		mv.setViewName("index"); 
		
		
		return mv;
	}
}
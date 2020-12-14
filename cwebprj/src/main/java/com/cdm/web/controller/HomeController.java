package com.cdm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.service.CommunityService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private CommunityService communityService;
	
	@RequestMapping("index")
	public ModelAndView index() throws Exception {

		ModelAndView mv = new ModelAndView();
		
		List<CommunityDTO> list = communityService.listSearch(); //오버로딩

		mv.setViewName("index"); 
		mv.addObject("list", list); 
		
		return mv;
	}
}
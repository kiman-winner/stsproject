package com.cdm.web.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class MainController {

	@RequestMapping("intro")	//홈페이지 소개
	public String intro() {
		return "main/intro";
	}
	@RequestMapping("study") //강좌 선택
	public String study() {
		return "main/study";
	}
	@RequestMapping("community/list") //커뮤니티 게시판
	public String communitylist() {
		return "main/community/list";
	}
	
	@RequestMapping("community/register")  //커뮤니티 게시판 등록 
	public String communityRegister() {
		return "main/community/register";
	}
	
	@RequestMapping("community/write")  //커뮤니티 게시판 등록 
	public String communityWrite() {
		
		
		
		
		
		
		
		return "main/intro";	//임시 
	}
	
}

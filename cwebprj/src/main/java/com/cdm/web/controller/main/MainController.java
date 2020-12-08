package com.cdm.web.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class MainController {

	@RequestMapping("intro")	//Ȩ������ �Ұ�
	public String intro() {
		return "main/intro";
	}
	@RequestMapping("study") //���� ����
	public String study() {
		return "main/study";
	}
	@RequestMapping("community/list") //Ŀ�´�Ƽ �Խ���
	public String communitylist() {
		return "main/community/list";
	}
	
	@RequestMapping("community/register")  //Ŀ�´�Ƽ �Խ��� ��� 
	public String communityRegister() {
		return "main/community/register";
	}
	
	@RequestMapping("community/write")  //Ŀ�´�Ƽ �Խ��� ��� 
	public String communityWrite() {
		
		
		
		
		
		
		
		return "main/intro";	//�ӽ� 
	}
	
}

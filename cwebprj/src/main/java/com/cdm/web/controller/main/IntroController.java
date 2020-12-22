package com.cdm.web.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class IntroController {//인트로 컨트롤러

	@RequestMapping("intro") // 홈페이지 소개
	public String intro() {
		return "main/intro";
	}
}

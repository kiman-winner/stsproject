package com.cdm.web.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class MainController {

	@RequestMapping("intro")
	public String intro() {
		return "main/intro";
	}
	@RequestMapping("study")
	public String study() {
		return "main/study";
	}
	@RequestMapping("qna")
	public String qna() {
		return "main/qna";
	}
}

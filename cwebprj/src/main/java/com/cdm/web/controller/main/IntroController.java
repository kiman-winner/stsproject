package com.cdm.web.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class IntroController {//��Ʈ�� ��Ʈ�ѷ�

	@RequestMapping("intro") // Ȩ������ �Ұ�
	public String intro() {
		return "main/intro";
	}
}

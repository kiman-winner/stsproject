package com.cdm.web.controller.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/study")
public class StudyController { //���� ��Ʈ�ѷ�

	@RequestMapping("menu") // ���� ����
	public void study(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<script>alert('�غ����� �޴��Դϴ�.'); " + "location.href = '/index'</script>");
	}
}

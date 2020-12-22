package com.cdm.web.controller.student;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.MemberDTO;
import com.cdm.web.page.PageMaker;
import com.cdm.web.page.SearchCriteria;
import com.cdm.web.service.CommunityService;
import com.cdm.web.service.MemberService;

@Controller
@RequestMapping("/student/")
public class StudentController {	//���������� ��Ʈ�ѷ�
	@Autowired
	private CommunityService communityService;
	@Autowired
	private MemberService memberservice;
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@RequestMapping("mycommunity") // ���������� ���� �ۼ��� ��
	public ModelAndView mycommunity(SearchCriteria searchCriteria) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(communityService.countSearched(searchCriteria));// ��ü �Խñ�

		ModelAndView mv = new ModelAndView();

		List<CommunityDTO> list = communityService.listSearch(searchCriteria);

		mv.setViewName("student/mycommunity");
		mv.addObject("list", list); // �Խñ� ����Ʈ
		mv.addObject("pageMaker", pageMaker); // ������ ����

		return mv;
	}

	@RequestMapping("updatemember") // ���������� �������� ����
	public String updatemember() {
		return "student/updatemember";
	}

	@RequestMapping(value = "updatemember.do", method = RequestMethod.POST) // ���������� �������� ���� ó��
	public void updatememberPost(HttpServletRequest request,MemberDTO memberDTO, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession httpSession = request.getSession();
		memberservice.updateMember(memberDTO);
		httpSession.setAttribute("member", memberDTO);	//���� �ʱ�ȭ 
		
		out.println("<script>alert('���� �Ǿ����ϴ�.'); " + "location.href = '/index'</script>");

	}

	@RequestMapping("updatepwd") // ��й�ȣ ���� ������
	public String updatepwd() {
		return "student/updatepwd";
	}

	@RequestMapping(value = "updatepwd.do", method = RequestMethod.POST) // ��й�ȣ ���� ó��
	public void updatepwdPost(@RequestParam("newpassword") String newpassword, MemberDTO memberDTO,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		MemberDTO login = memberservice.login(memberDTO);

		if (login != null && passEncoder.matches(memberDTO.getPassword(), login.getPassword()))// ���� ���̵� ��� Ȯ��
		{
			memberservice.updatepwd(memberDTO, newpassword);// ��й�ȣ ���� ����
			out.println("<script>alert('���� �Ǿ����ϴ�.'); " + "location.href = '/index'</script>");
		} else {
			out.println("<script>alert('���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); " + "location.href = 'updatepwd'</script>");
		}

	}

	@RequestMapping("deletemember") // ȸ��Ż��
	public String deletemember() {
		return "student/deletemember";
	}

	@RequestMapping("deletemember.do") // ȸ��Ż��
	public void deletememberPost(HttpServletRequest request,@RequestParam("member_id") String member_id, HttpSession httpSession,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		memberservice.deleteMember(member_id);
		httpSession.invalidate();
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");	//��Ű�� �ִٸ� ����
		if (loginCookie != null) { 
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);	
		}

		out.println("<script>alert('Ż�� �Ϸ�Ǿ����ϴ�.'); " + "location.href = '/index'</script>");
	}
}

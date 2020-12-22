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
public class StudentController {	//마이페이지 컨트롤러
	@Autowired
	private CommunityService communityService;
	@Autowired
	private MemberService memberservice;
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@RequestMapping("mycommunity") // 마이페이지 내가 작성한 글
	public ModelAndView mycommunity(SearchCriteria searchCriteria) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(communityService.countSearched(searchCriteria));// 전체 게시글

		ModelAndView mv = new ModelAndView();

		List<CommunityDTO> list = communityService.listSearch(searchCriteria);

		mv.setViewName("student/mycommunity");
		mv.addObject("list", list); // 게시글 리스트
		mv.addObject("pageMaker", pageMaker); // 페이지 관리

		return mv;
	}

	@RequestMapping("updatemember") // 마이페이지 개인정보 수정
	public String updatemember() {
		return "student/updatemember";
	}

	@RequestMapping(value = "updatemember.do", method = RequestMethod.POST) // 마이페이지 개인정보 수정 처리
	public void updatememberPost(HttpServletRequest request,MemberDTO memberDTO, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession httpSession = request.getSession();
		memberservice.updateMember(memberDTO);
		httpSession.setAttribute("member", memberDTO);	//세션 초기화 
		
		out.println("<script>alert('수정 되었습니다.'); " + "location.href = '/index'</script>");

	}

	@RequestMapping("updatepwd") // 비밀번호 변경 페이지
	public String updatepwd() {
		return "student/updatepwd";
	}

	@RequestMapping(value = "updatepwd.do", method = RequestMethod.POST) // 비밀번호 변경 처리
	public void updatepwdPost(@RequestParam("newpassword") String newpassword, MemberDTO memberDTO,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		MemberDTO login = memberservice.login(memberDTO);

		if (login != null && passEncoder.matches(memberDTO.getPassword(), login.getPassword()))// 기존 아이디 비번 확인
		{
			memberservice.updatepwd(memberDTO, newpassword);// 비밀번호 변경 서비스
			out.println("<script>alert('변경 되었습니다.'); " + "location.href = '/index'</script>");
		} else {
			out.println("<script>alert('기존 비밀번호와 일치하지 않습니다.'); " + "location.href = 'updatepwd'</script>");
		}

	}

	@RequestMapping("deletemember") // 회원탈퇴
	public String deletemember() {
		return "student/deletemember";
	}

	@RequestMapping("deletemember.do") // 회원탈퇴
	public void deletememberPost(HttpServletRequest request,@RequestParam("member_id") String member_id, HttpSession httpSession,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		memberservice.deleteMember(member_id);
		httpSession.invalidate();
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");	//쿠키가 있다면 제거
		if (loginCookie != null) { 
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);	
		}

		out.println("<script>alert('탈퇴가 완료되었습니다.'); " + "location.href = '/index'</script>");
	}
}

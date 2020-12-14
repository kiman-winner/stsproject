package com.cdm.web.controller.student;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.MemberDTO;
import com.cdm.web.page.PageMaker;
import com.cdm.web.page.SearchCriteria;
import com.cdm.web.service.CommunityService;
import com.cdm.web.service.MemberService;

@Controller
@RequestMapping("/student/")
public class StudentController {
	@Autowired
	private CommunityService communityService;
	@Autowired
	private MemberService memberservice;

	@RequestMapping("mycommunity") // 마이페이지 나의 커뮤니티 
	public ModelAndView mycommunity(SearchCriteria searchCriteria) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(communityService.countSearched(searchCriteria));// 전체 게시글
		
		ModelAndView mv = new ModelAndView();
		
		List<CommunityDTO> list = communityService.listSearch(searchCriteria);

		mv.setViewName("student/mycommunity"); // list뷰
		mv.addObject("list", list); // 뷰로 보낼 데이터
		mv.addObject("pageMaker", pageMaker);

		return mv;
	}
	@RequestMapping("updatemember") // 마이페이지 개인정보 수정
	public String updatemember() {
		return "student/updatemember";
	}
	@RequestMapping(value="updatememberPost",method = RequestMethod.POST) // 마이페이지 개인정보 수정 처리
	public void updatememberPost(MemberDTO memberDTO, HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체

		memberservice.updateMember(memberDTO);//업데이트 서비스 작성 

		out.println("<script>alert('수정 되었습니다.'); " + "location.href = '/index'</script>");

	}
	@RequestMapping("updatepwd") // 비밀번호 변경 페이지
	public String updatepwd() {
		return "student/updatepwd";
	}
	
	@RequestMapping(value="updatepwdPost",method = RequestMethod.POST) //비밀번호 변경
	public void updatepwdPost(@RequestParam("newpassword") String newpassword,MemberDTO memberDTO, HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체

		if(memberservice.login(memberDTO)!=null)//기존 아이디 비번 확인 
		{	
			memberDTO.setPassword(newpassword);
			memberservice.updatepwd(memberDTO);//비밀번호 변경 서비스 
			out.println("<script>alert('변경 되었습니다.'); " + "location.href = '/index'</script>");}
		else {	out.println("<script>alert('기존 비밀번호와 일치하지 않습니다.'); " + "location.href = 'updatepwd'</script>");}
	
	}
	@RequestMapping("deletemember") // 회원탈퇴 
	public String deletemember() {
		return "student/deletemember";
	}
	@RequestMapping("deletememberPost") // 회원탈퇴 
	public void deletememberPost(@RequestParam("member_id") String member_id,HttpSession session, HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체
		
		memberservice.deleteMember(member_id);//탈퇴 서비스 
		session.invalidate();
		
		out.println("<script>alert('탈퇴가 완료되었습니다.'); " + "location.href = '/index'</script>");
	}
}

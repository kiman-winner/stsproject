package com.cdm.web.controller.member;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.cdm.web.dto.MemberDTO;
import com.cdm.web.service.CommunityService;
import com.cdm.web.service.MemberService;
import com.cdm.web.util.MailUtil;

@Controller
@RequestMapping("/member/")
public class MemberController { // 멤버 관련 컨트롤러

	@Autowired
	private MemberService memberservice;
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@RequestMapping("login") // 로그인 페이지
	public String login() {
		return "member/login";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST) // 로그인 처리
	public ModelAndView loginPost(MemberDTO memberDTO, HttpServletRequest req, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체

		RedirectView rv = new RedirectView("/index");

		ModelAndView mav = new ModelAndView();

		HttpSession session = req.getSession();
		MemberDTO login = memberservice.login(memberDTO);
		
		boolean passMatch = passEncoder.matches(memberDTO.getPassword(),login.getPassword());

		if (login != null && passMatch) { // 로그인 성공
			session.setAttribute("member", login); // 세션에 저장.
			mav.setView(rv);
		} else { // 로그인  실패
			session.setAttribute("member", null);
			out.println("<script>alert('아이디 혹은 비밀번호가 일치하지 않습니다.'); " + "location.href = '/member/login'</script>"); // 실패시 다시 로그인 페이지 복귀
			out.close();
		}
		return mav;
	}
	
	// 로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/member/login";
	}

	/* 회원가입 */

	@RequestMapping("join") // 회원가입 페이지
	public String join() {
		return "member/join";
	}

	@RequestMapping("idCheck") // 아이디 중복 체크
	@ResponseBody
	public int idCheck(@RequestParam("member_id") String member_id) throws Exception {

		System.out.println(member_id);
		return memberservice.idCheck(member_id);
	}

	@RequestMapping(value = "join.do", method = RequestMethod.POST) // 회원가입 페이지
	public String joinPost(MemberDTO memberDTO) throws Exception {
		memberservice.join(memberDTO);
		return "redirect:/member/confirm"; // 회원가입 완료 페이지
	}

	@RequestMapping("confirm") // 회원가입 페이지
	public String confirm() {
		return "member/confirm";
	}

	@RequestMapping("find-id") // 아이디 찾기 페이지
	public String findid() {
		return "member/find-id";
	}

	@RequestMapping(value = "find-id-confirm", method = RequestMethod.POST) // 아이디 찾기 post
	public String findid_confirm(MemberDTO memberDTO, RedirectAttributes redirectAttributes) throws Exception {

		redirectAttributes.addFlashAttribute("member_idList", memberservice.findId(memberDTO));

		return "redirect:find-id-confirm";
	}

	@RequestMapping("find-id-confirm") // 아이디 찾기 결과 페이지
	public String find_idconfirm() {
		return "member/find-id-confirm";
	}

	@RequestMapping("pwd-reset") // 비밀번호 찾기 페이지
	public String findpwd() {
		return "member/pwd-reset";
	}

	@RequestMapping(value = "pwd-reset.do", method = RequestMethod.POST) // 비밀번호 찾기 메일 발송
	public void findpwdPost(MemberDTO memberDTO, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체
		
		String pwdcheck = memberservice.pwdcheck(memberDTO); // 비밀번호 찾기

		if (pwdcheck == null)
			out.println("<script>alert('정보가 올바르지 않습니다.'); " + "location.href = '/member/pwd-reset'</script>");

		memberservice.resetPwd(memberDTO);	//비밀번호 초기화

		out.println("<script>alert('임시 비밀번호 발송이 완료되었습니다.'); " + "location.href = '/member/login'</script>");
	}

}

package com.cdm.web.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cdm.web.service.MemberService;
import com.cdm.web.vo.MemberVO;

@Controller
@RequestMapping("/member/")
public class MemberController { // 멤버 관련 컨트롤러

	@Autowired
	private MemberService memberservice;

//	private UserSignUpService userSignUpService;

	@RequestMapping("login") // 로그인 페이지
	public String login() {
		return "member.login";
	}

	@RequestMapping(value = "login/loginPost", method = RequestMethod.POST) // 로그인 처리
	public ModelAndView loginPost(MemberVO vo, HttpServletRequest req) throws Exception {

		RedirectView rv = new RedirectView("/index");
		
		ModelAndView mav = new ModelAndView();

		HttpSession session = req.getSession();
		MemberVO login = memberservice.login(vo);

		if (login == null) { // 로그인 실패
			session.setAttribute("member", null);
			mav.addObject("msg", "false");
			mav.setViewName("member.login");
			return mav;
		} else { // 로그인 성공시
			session.setAttribute("member", login); // 세션에 저장.
			mav.setView(rv);
		}
		return mav;
	}
	
	// 로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "member.login";
	}

	@RequestMapping("join") // 회원가입 페이지
	public String join() {
		return "member.join";
	}

	/*
	 * @RequestMapping(value = "confirm", method = RequestMethod.POST) // 회원가입 완료
	 * 페이지 public String confirm(UserVO userVO) {
	 * 
	 * userSignUpService.userSign_service(user VO); return "member.confirm"; }
	 */

	@RequestMapping("find-id") // 아이디 찾기 페이지
	public String findid() {
		return "member.find-id";
	}

	@RequestMapping("pwd-reset") // 비밀번호 찾기 페이지
	public String pwdreset() {
		return "member.pwd-reset";
	}

}

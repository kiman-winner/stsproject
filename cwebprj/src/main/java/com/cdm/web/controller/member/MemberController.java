package com.cdm.web.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		return "member/login";
	}

	@RequestMapping(value = "login/loginPost", method = RequestMethod.POST) // 로그인 처리
	public ModelAndView loginPost(MemberVO vo, HttpServletRequest req, HttpServletResponse response) throws Exception {

		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); //한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체
		
		RedirectView rv = new RedirectView("/index");

		ModelAndView mav = new ModelAndView();

		HttpSession session = req.getSession();
		MemberVO login = memberservice.login(vo);

		if (login == null) { // 로그인 실패
			session.setAttribute("member", null);
			out.println("<script>alert('아이디 혹은 비밀번호가 일치하지 않습니다.'); "
					+ "location.href = '/member/login'</script>");	//실패 시 다시 로그인 페이지 복귀
			out.close();
			return null;
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
		return "redirect:/member/login";
	}
	
	/*								회원가입								*/
	
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
	
	@RequestMapping(value="join/joinPost",method=RequestMethod.POST) // 회원가입 페이지
	public String joinPost(MemberVO vo) throws Exception {
		
		memberservice.join(vo);
		return "redirect:/member/confirm";	//회원가입 완료 페이지 
	}
	
	@RequestMapping("confirm") //  회원가입 페이지
	public String confirm() {
		return "member/confirm";
	}

	@RequestMapping("find-id") // 아이디 찾기 페이지
	public String findid() {
		return "member/find-id";
	}

	@RequestMapping("pwd-reset") // 비밀번호 찾기 페이지
	public String pwdreset() {
		return "member/pwd-reset";
	}

}

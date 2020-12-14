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

//	private UserSignUpService userSignUpService;

	@RequestMapping("login") // 로그인 페이지
	public String login() {
		return "member/login";
	}

	@RequestMapping(value = "login/loginPost", method = RequestMethod.POST) // 로그인 처리
	public ModelAndView loginPost(MemberDTO vo, HttpServletRequest req, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체

		RedirectView rv = new RedirectView("/index");

		ModelAndView mav = new ModelAndView();

		HttpSession session = req.getSession();
		MemberDTO login = memberservice.login(vo);

		if (login == null) { // 로그인 실패
			session.setAttribute("member", null);
			out.println("<script>alert('아이디 혹은 비밀번호가 일치하지 않습니다.'); " + "location.href = '/member/login'</script>"); // 실패
																													// 시
																													// 다시
																													// 로그인
																													// 페이지
																													// 복귀
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

	@RequestMapping(value = "join/joinPost", method = RequestMethod.POST) // 회원가입 페이지
	public String joinPost(MemberDTO vo) throws Exception {

		memberservice.join(vo);
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

	@RequestMapping(value = "find_idPost", method = RequestMethod.POST) // 아이디 찾기 post
	public String findid_confirm(MemberDTO vo, RedirectAttributes redirectAttributes) throws Exception {

		redirectAttributes.addFlashAttribute("member_id", memberservice.findId(vo));

		System.out.print("컨트롤단 결과 " + memberservice.findId(vo));

		return "redirect:find-id-confirm";
	}

	@RequestMapping("find-id-confirm") // 아이디 찾기 결과 페이지
	public String find_idconfirm() {
		return "member/find-id-confirm";
	}

	@RequestMapping("find-pwd") // 비밀번호 찾기 페이지
	public String findpwd() {
		return "member/find-pwd";
	}

	@RequestMapping(value = "find-pwdPost", method = RequestMethod.POST) // 비밀번호 찾기 메일 발송
	public void findpwdPost(MemberDTO vo, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체
		String pwd = memberservice.findpwd(vo); // 비밀번호 찾기

		if (pwd == null)
			out.println("<script>alert('정보가 올바르지 않습니다.'); " + "location.href = '/member/find-pwd'</script>");

		String subject = "[CDMlucture] 비밀번호 찾기";

		String msg = "";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'><strong>" + vo.getMember_id();
		msg += "님</strong>의 비밀번호 입니다. 로그인 후 비밀번호를 변경하세요.</h3>";
		msg += "<p>비밀번호 : <strong>" + pwd + "</strong></p></div>";

		MailUtil.sendMail(vo.getEmail(), subject, msg);

		out.println("<script>alert('비밀번호 발송이 완료되었습니다.'); " + "location.href = '/member/login'</script>");
	}

}

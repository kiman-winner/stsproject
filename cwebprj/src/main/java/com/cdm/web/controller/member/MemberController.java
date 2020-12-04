package com.cdm.web.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdm.web.service.MemberService;
import com.cdm.web.vo.MemberVO;

@Controller
@RequestMapping("/member/")
public class MemberController { // ��� ���� ��Ʈ�ѷ�

	@Autowired
	private MemberService memberservice;

//	private UserSignUpService userSignUpService;

	@RequestMapping("login") // �α��� ������
	public String login() {
		return "member.login";
	}

	@RequestMapping(value = "login/loginPost", method = RequestMethod.POST) // �α��� ó��
	public ModelAndView loginPost(MemberVO vo, HttpServletRequest req) throws Exception {

		ModelAndView mav = new ModelAndView();

		HttpSession session = req.getSession();
		MemberVO login = memberservice.login(vo);

		if (login == null) { // �α��� ����
			session.setAttribute("member", null);
			mav.addObject("msg", "false");
			
		} else { // �α��� ������
			session.setAttribute("member", login); // ���ǿ� ����.
			mav.addObject("msg", "true");
			
		}
		mav.setViewName("member.login");
		return mav;
	}

	// �α׾ƿ�
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "index";
	}

	@RequestMapping("join") // ȸ������ ������
	public String join() {
		return "member.join";
	}

	/*
	 * @RequestMapping(value = "confirm", method = RequestMethod.POST) // ȸ������ �Ϸ�
	 * ������ public String confirm(UserVO userVO) {
	 * 
	 * userSignUpService.userSign_service(user VO); return "member.confirm"; }
	 */

	@RequestMapping("find-id") // ���̵� ã�� ������
	public String findid() {
		return "member.find-id";
	}

	@RequestMapping("pwd-reset") // ��й�ȣ ã�� ������
	public String pwdreset() {
		return "member.pwd-reset";
	}

}

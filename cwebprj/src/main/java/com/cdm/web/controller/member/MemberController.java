package com.cdm.web.controller.member;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.cdm.web.dto.MemberDTO;
import com.cdm.web.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController { // ��� ���� ��Ʈ�ѷ�

	@Autowired
	private MemberService memberservice;
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@RequestMapping("login") // �α��� ������
	public String login() {
		return "member/login";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST) // �α��� ó��
	public void loginPost(MemberDTO memberDTO, HttpSession httpSession, Model model) throws Exception {

		MemberDTO login = memberservice.login(memberDTO);

		if (login != null && passEncoder.matches(memberDTO.getPassword(), login.getPassword())) { // �α��� ����
			model.addAttribute("member", login);
		} else {
			model.addAttribute("member", null);
		}

		// �α��� ������ ������ ���
		if (memberDTO.isUseCookie()) {
			memberservice.keepLogin(memberDTO.getMember_id(), httpSession.getId());
		}
	}

	// �α׾ƿ�
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
			throws Exception { // �α׾ƿ�

		Object object = httpSession.getAttribute("member");
		if (object != null) {
			MemberDTO memberDTO = (MemberDTO) object;
			httpSession.removeAttribute("member");
			httpSession.invalidate();

			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) { // �ڵ��α��� ���� ���� �� �ʱ�ȭ
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				memberservice.keepLogin(memberDTO.getMember_id(), "none");
			}
		}

		return "/member/login";
	}

	/* ȸ������ */

	@RequestMapping("join") // ȸ������ ������
	public String join() {
		return "member/join";
	}

	@RequestMapping("idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("member_id") String member_id) throws Exception { // ���̵� �ߺ� üũ
		return memberservice.idCheck(member_id);
	}

	@RequestMapping(value = "join.do", method = RequestMethod.POST) // ȸ������ ó��
	public String joinPost(MemberDTO memberDTO) throws Exception {
		memberservice.join(memberDTO);
		return "redirect:/member/confirm"; // ȸ������ �Ϸ� ������
	}

	@RequestMapping("confirm") // ȸ������ ������
	public String confirm() {
		return "member/confirm";
	}

	@RequestMapping("find-id") // ���̵� ã�� ������
	public String findid() {
		return "member/find-id";
	}

	@RequestMapping(value = "find-id.do", method = RequestMethod.POST) // ���̵� ã�� ó��
	public String findid_confirm(MemberDTO memberDTO, RedirectAttributes redirectAttributes,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		List<String> member_idList = memberservice.findId(memberDTO);
		System.out.println(member_idList.size());
		if (member_idList.size()==0) {	//����� ���ٸ� 
			System.out.println("��� ����");
			out.println("<script>alert('�ش� ������ ȸ���� �����ϴ�.'); " + "location.href = '/member/find-id'</script>");
			return null;
		}
			redirectAttributes.addFlashAttribute("member_idList", member_idList);
			return "redirect:/member/find-id-confirm";
	}

	@RequestMapping("find-id-confirm") // ���̵� ã�� ��� ������
	public String find_idconfirm() {
		return "member/find-id-confirm";
	}

	@RequestMapping("pwd-reset") // ��й�ȣ ã�� ������
	public String findpwd() {
		return "member/pwd-reset";
	}

	@RequestMapping(value = "pwd-reset.do", method = RequestMethod.POST) // ��й�ȣ ã�� ���� �߼�
	public void findpwdPost(MemberDTO memberDTO, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String pwdcheck = memberservice.pwdcheck(memberDTO); // ��й�ȣ ã��

		if (pwdcheck == null)
			out.println("<script>alert('������ �ùٸ��� �ʽ��ϴ�.'); " + "location.href = '/member/pwd-reset'</script>");

		memberservice.resetPwd(memberDTO); // ��й�ȣ �ʱ�ȭ

		out.println("<script>alert('�ӽ� ��й�ȣ �߼��� �Ϸ�Ǿ����ϴ�.'); " + "location.href = '/member/login'</script>");
	}

}

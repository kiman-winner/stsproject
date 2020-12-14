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
public class MemberController { // ��� ���� ��Ʈ�ѷ�

	@Autowired
	private MemberService memberservice;

//	private UserSignUpService userSignUpService;

	@RequestMapping("login") // �α��� ������
	public String login() {
		return "member/login";
	}

	@RequestMapping(value = "login/loginPost", method = RequestMethod.POST) // �α��� ó��
	public ModelAndView loginPost(MemberDTO vo, HttpServletRequest req, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		RedirectView rv = new RedirectView("/index");

		ModelAndView mav = new ModelAndView();

		HttpSession session = req.getSession();
		MemberDTO login = memberservice.login(vo);

		if (login == null) { // �α��� ����
			session.setAttribute("member", null);
			out.println("<script>alert('���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); " + "location.href = '/member/login'</script>"); // ����
																													// ��
																													// �ٽ�
																													// �α���
																													// ������
																													// ����
			out.close();
			return null;
		} else { // �α��� ������
			session.setAttribute("member", login); // ���ǿ� ����.
			mav.setView(rv);
		}
		return mav;
	}

	// �α׾ƿ�
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/member/login";
	}

	/* ȸ������ */

	@RequestMapping("join") // ȸ������ ������
	public String join() {
		return "member/join";
	}

	@RequestMapping("idCheck") // ���̵� �ߺ� üũ
	@ResponseBody
	public int idCheck(@RequestParam("member_id") String member_id) throws Exception {

		System.out.println(member_id);
		return memberservice.idCheck(member_id);
	}

	@RequestMapping(value = "join/joinPost", method = RequestMethod.POST) // ȸ������ ������
	public String joinPost(MemberDTO vo) throws Exception {

		memberservice.join(vo);
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

	@RequestMapping(value = "find_idPost", method = RequestMethod.POST) // ���̵� ã�� post
	public String findid_confirm(MemberDTO vo, RedirectAttributes redirectAttributes) throws Exception {

		redirectAttributes.addFlashAttribute("member_id", memberservice.findId(vo));

		System.out.print("��Ʈ�Ѵ� ��� " + memberservice.findId(vo));

		return "redirect:find-id-confirm";
	}

	@RequestMapping("find-id-confirm") // ���̵� ã�� ��� ������
	public String find_idconfirm() {
		return "member/find-id-confirm";
	}

	@RequestMapping("find-pwd") // ��й�ȣ ã�� ������
	public String findpwd() {
		return "member/find-pwd";
	}

	@RequestMapping(value = "find-pwdPost", method = RequestMethod.POST) // ��й�ȣ ã�� ���� �߼�
	public void findpwdPost(MemberDTO vo, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü
		String pwd = memberservice.findpwd(vo); // ��й�ȣ ã��

		if (pwd == null)
			out.println("<script>alert('������ �ùٸ��� �ʽ��ϴ�.'); " + "location.href = '/member/find-pwd'</script>");

		String subject = "[CDMlucture] ��й�ȣ ã��";

		String msg = "";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'><strong>" + vo.getMember_id();
		msg += "��</strong>�� ��й�ȣ �Դϴ�. �α��� �� ��й�ȣ�� �����ϼ���.</h3>";
		msg += "<p>��й�ȣ : <strong>" + pwd + "</strong></p></div>";

		MailUtil.sendMail(vo.getEmail(), subject, msg);

		out.println("<script>alert('��й�ȣ �߼��� �Ϸ�Ǿ����ϴ�.'); " + "location.href = '/member/login'</script>");
	}

}

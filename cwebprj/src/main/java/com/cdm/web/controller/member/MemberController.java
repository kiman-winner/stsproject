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
public class MemberController { // ��� ���� ��Ʈ�ѷ�

	@Autowired
	private MemberService memberservice;

//	private UserSignUpService userSignUpService;

	@RequestMapping("login") // �α��� ������
	public String login() {
		return "member/login";
	}

	@RequestMapping(value = "login/loginPost", method = RequestMethod.POST) // �α��� ó��
	public ModelAndView loginPost(MemberVO vo, HttpServletRequest req, HttpServletResponse response) throws Exception {

		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); //�ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü
		
		RedirectView rv = new RedirectView("/index");

		ModelAndView mav = new ModelAndView();

		HttpSession session = req.getSession();
		MemberVO login = memberservice.login(vo);

		if (login == null) { // �α��� ����
			session.setAttribute("member", null);
			out.println("<script>alert('���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); "
					+ "location.href = '/member/login'</script>");	//���� �� �ٽ� �α��� ������ ����
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
	
	/*								ȸ������								*/
	
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
	
	@RequestMapping(value="join/joinPost",method=RequestMethod.POST) // ȸ������ ������
	public String joinPost(MemberVO vo) throws Exception {
		
		memberservice.join(vo);
		return "redirect:/member/confirm";	//ȸ������ �Ϸ� ������ 
	}
	
	@RequestMapping("confirm") //  ȸ������ ������
	public String confirm() {
		return "member/confirm";
	}

	@RequestMapping("find-id") // ���̵� ã�� ������
	public String findid() {
		return "member/find-id";
	}

	@RequestMapping("pwd-reset") // ��й�ȣ ã�� ������
	public String pwdreset() {
		return "member/pwd-reset";
	}

}

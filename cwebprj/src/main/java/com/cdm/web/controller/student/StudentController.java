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

	@RequestMapping("mycommunity") // ���������� ���� Ŀ�´�Ƽ 
	public ModelAndView mycommunity(SearchCriteria searchCriteria) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(communityService.countSearched(searchCriteria));// ��ü �Խñ�
		
		ModelAndView mv = new ModelAndView();
		
		List<CommunityDTO> list = communityService.listSearch(searchCriteria);

		mv.setViewName("student/mycommunity"); // list��
		mv.addObject("list", list); // ��� ���� ������
		mv.addObject("pageMaker", pageMaker);

		return mv;
	}
	@RequestMapping("updatemember") // ���������� �������� ����
	public String updatemember() {
		return "student/updatemember";
	}
	@RequestMapping(value="updatememberPost",method = RequestMethod.POST) // ���������� �������� ���� ó��
	public void updatememberPost(MemberDTO memberDTO, HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		memberservice.updateMember(memberDTO);//������Ʈ ���� �ۼ� 

		out.println("<script>alert('���� �Ǿ����ϴ�.'); " + "location.href = '/index'</script>");

	}
	@RequestMapping("updatepwd") // ��й�ȣ ���� ������
	public String updatepwd() {
		return "student/updatepwd";
	}
	
	@RequestMapping(value="updatepwdPost",method = RequestMethod.POST) //��й�ȣ ����
	public void updatepwdPost(@RequestParam("newpassword") String newpassword,MemberDTO memberDTO, HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		if(memberservice.login(memberDTO)!=null)//���� ���̵� ��� Ȯ�� 
		{	
			memberDTO.setPassword(newpassword);
			memberservice.updatepwd(memberDTO);//��й�ȣ ���� ���� 
			out.println("<script>alert('���� �Ǿ����ϴ�.'); " + "location.href = '/index'</script>");}
		else {	out.println("<script>alert('���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); " + "location.href = 'updatepwd'</script>");}
	
	}
	@RequestMapping("deletemember") // ȸ��Ż�� 
	public String deletemember() {
		return "student/deletemember";
	}
	@RequestMapping("deletememberPost") // ȸ��Ż�� 
	public void deletememberPost(@RequestParam("member_id") String member_id,HttpSession session, HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü
		
		memberservice.deleteMember(member_id);//Ż�� ���� 
		session.invalidate();
		
		out.println("<script>alert('Ż�� �Ϸ�Ǿ����ϴ�.'); " + "location.href = '/index'</script>");
	}
}

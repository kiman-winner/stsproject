package com.cdm.web.controller.main;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.service.CommunityService;

@Controller
@RequestMapping("/main/")
public class MainController {

	@Autowired
	private CommunityService communityService;

	@RequestMapping("intro") // Ȩ������ �Ұ�
	public String intro() {
		return "main/intro";
	}

	@RequestMapping("study") // ���� ����
	public String study() {
		return "main/study";
	}

	@RequestMapping(value = "community/list", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ���
	public ModelAndView communitylist() throws Exception {

		ModelAndView mv = new ModelAndView();

		List<CommunityDTO> list = communityService.read();

		mv.setViewName("/main/community/list"); // list��
		mv.addObject("list", list); // ��� ���� ������

		return mv;
	}

	@RequestMapping("community/register") // Ŀ�´�Ƽ �Խ��� ���
	public String communityRegister() {
		return "main/community/register";
	}

	@RequestMapping(value = "community/write", method = RequestMethod.POST) // Ŀ�´�Ƽ �Խ��� ���
	public void communityWrite(CommunityDTO vo, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		communityService.write(vo);

		out.println("<script>alert('��� �Ǿ����ϴ�.'); " + "location.href = '/main/community/list'</script>");

		// �ӽ�
	}

	@RequestMapping(value = "community/detail", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ��� ������
	public ModelAndView detail(@RequestParam("community_num") int community_num) throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.addObject("detail", communityService.detail(community_num)); // �󼼺��� ���񽺸� ���� �ش� �Խñ� �ҷ�����
		mv.setViewName("main/community/detail");
		return mv;
	}

	@RequestMapping(value = "community/delete", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ��� ���� Ŭ�� ��
	public void delete(@RequestParam("community_num") int community_num, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		communityService.delete(community_num);

		out.println("<script>alert('���� �Ǿ����ϴ�.'); " + "location.href = '/main/community/list'</script>");
	}

	@RequestMapping(value = "community/modify", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ��� ���� ������
	public ModelAndView communityModify(@RequestParam("title") String title, @RequestParam("content") String content,@RequestParam("community_num") int community_num) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("title", title); // ����� �������� ���� �������� �Ѱ��ش�.
		mv.addObject("content", content);
		mv.addObject("community_num", community_num);
		mv.setViewName("main/community/modify");

		return mv;
	}

	@RequestMapping(value = "community/modify/modifyPost", method = RequestMethod.POST) // Ŀ�´�Ƽ ���� ���������� ���� Ŭ�� ��
	public void modify(CommunityDTO vo, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		communityService.modify(vo);

		out.println("<script>alert('���� �Ǿ����ϴ�.'); " + "location.href = '/main/community/list'</script>");
	}

}

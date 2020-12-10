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
import com.cdm.web.dto.Criteria;
import com.cdm.web.dto.PageMaker;
import com.cdm.web.dto.ReplyDTO;
import com.cdm.web.service.CommunityService;
import com.cdm.web.service.ReplyService;

@Controller
@RequestMapping("/main/")
public class MainController {

	@Autowired
	private CommunityService communityService;
	@Autowired
	private ReplyService replyService;

	@RequestMapping("intro") // Ȩ������ �Ұ�
	public String intro() {
		return "main/intro";
	}

	@RequestMapping("study") // ���� ����
	public String study() {
		return "main/study";
	}

	@RequestMapping(value = "community/list", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ��� ����Ʈ �ҷ�����
	public ModelAndView communitylist(Criteria criteria) throws Exception {

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(communityService.listCount());// ��ü �Խñ�

		ModelAndView mv = new ModelAndView();

		List<CommunityDTO> list = communityService.read(criteria);

		mv.setViewName("/main/community/list"); // list��
		mv.addObject("list", list); // ��� ���� ������
		mv.addObject("pageMaker", pageMaker);

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

		communityService.updateViewCount(community_num); // ��ȸ�� ����
		mv.addObject("detail", communityService.detail(community_num)); // �󼼺��� ���񽺸� ���� �ش� �Խñ� �ҷ�����

		List<ReplyDTO> replyList = replyService.readReply(community_num); // ��� �ҷ�����
		mv.addObject("replyList", replyList);
		mv.setViewName("main/community/detail");
		return mv;
	}

	@RequestMapping(value = "community/delete", method = RequestMethod.POST) // Ŀ�´�Ƽ �Խ��� ���� Ŭ�� ��
	public void delete(@RequestParam("community_num") int community_num, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		communityService.delete(community_num);

		out.println("<script>alert('���� �Ǿ����ϴ�.'); " + "location.href = '/main/community/list'</script>");
	}

	@RequestMapping(value = "community/modify", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ��� ���� ������
	public ModelAndView communityModify(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("community_num") int community_num) {
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

	@RequestMapping(value = "community/detail/replyPost", method = RequestMethod.POST)//  ��� �ۼ� 
	public void replypost(ReplyDTO replyDTO,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		replyService.writeReply(replyDTO);

		out.println("<script>alert('����� ��� �Ǿ����ϴ�.'); " + "location.href = '/main/community/detail?community_num="+replyDTO.getCommunity_num()+"'</script>");
	}
}

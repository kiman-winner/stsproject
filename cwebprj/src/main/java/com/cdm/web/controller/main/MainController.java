package com.cdm.web.controller.main;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.PageMaker;
import com.cdm.web.dto.ReplyDTO;
import com.cdm.web.dto.SearchCriteria;
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
	public ModelAndView communitylist(SearchCriteria searchCriteria) throws Exception {

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(communityService.countSearched(searchCriteria));// ��ü �Խñ�

		ModelAndView mv = new ModelAndView();

		List<CommunityDTO> list = communityService.listSearch(searchCriteria);

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
	public ModelAndView detail(@RequestParam("community_num") int community_num,
			@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		ModelAndView mv = new ModelAndView();

		communityService.updateViewCount(community_num); // ��ȸ�� ����
		mv.addObject("detail", communityService.detail(community_num)); // �󼼺��� ���񽺸� ���� �ش� �Խñ� �ҷ�����

		List<ReplyDTO> replyList = replyService.readReply(community_num); // ��� �ҷ�����
		mv.addObject("replyList", replyList);
		mv.setViewName("main/community/detail");
		return mv;
	}

	@RequestMapping(value = "community/delete", method = RequestMethod.POST) // Ŀ�´�Ƽ �Խ��� ���� Ŭ�� ��
	public String delete(@RequestParam("community_num") int community_num, SearchCriteria searchCriteria,
            RedirectAttributes redirectAttributes)
			throws Exception {
		
		replyService.deleteAll(community_num);//�ش� ��� ��� ���� 
		communityService.delete(community_num);
		
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
	    redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword());	//�˻� ���� ����

		return "redirect:/main/community/list";
	}

	@RequestMapping(value = "community/modify", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ��� ���� ������
	public ModelAndView communityModify(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("community_num") int community_num, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("title", title); // ����� �������� ���� �������� �Ѱ��ش�.
		mv.addObject("content", content);
		mv.addObject("community_num", community_num);
		mv.setViewName("main/community/modify");

		return mv;
	}

	@RequestMapping(value = "community/modify/modifyPost", method = RequestMethod.POST) // Ŀ�´�Ƽ ���� ���������� ���� Ŭ�� ��
	public String modify(CommunityDTO vo
			, SearchCriteria searchCriteria,
            RedirectAttributes redirectAttributes) throws Exception {

		communityService.modify(vo);
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
	    redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword());	//�˻� ���� ����

		return "redirect:/main/community/list";
	}

	
}

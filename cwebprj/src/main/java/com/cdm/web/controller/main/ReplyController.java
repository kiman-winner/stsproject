package com.cdm.web.controller.main;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdm.web.dto.ReplyDTO;
import com.cdm.web.service.CommunityService;
import com.cdm.web.service.ReplyService;

@Controller
@RequestMapping("/main/community/detail/")
public class ReplyController {		//��� ���� 

	@Autowired
	private CommunityService communityService;
	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "replyWrite", method = RequestMethod.POST) // ��� �ۼ�
	public void replyWrite(ReplyDTO replyDTO, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü
		
		replyService.writeReply(replyDTO);

		out.println("<script>alert('����� ��� �Ǿ����ϴ�.'); " + "location.href = '/main/community/detail?community_num="
				+ replyDTO.getCommunity_num() + "'</script>");
	}
	
	@RequestMapping(value = "replyDelete", method = RequestMethod.POST) // ��� ����
	public void replyDelete(@RequestParam("reply_num") int reply_num,@RequestParam("community_num") int community_num, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		replyService.deleteReply(reply_num,community_num);	//��� ���� 

		out.println("<script>alert('����� ���� �Ǿ����ϴ�.'); " + "location.href = '/main/community/detail?community_num="
				+ community_num + "'</script>");
	}
	
	@RequestMapping(value = "replyUpdateForm", method = RequestMethod.GET) // ��� ���� â 
	public ModelAndView replyupdateForm(@RequestParam("reply_num") int reply_num,@RequestParam("content") String content, @RequestParam("writer_id") String writer_id) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("reply_num",reply_num);
		mv.addObject("content",content);
		mv.addObject("writer_id",writer_id);
		

		mv.setViewName("/main/community/replyUpdateForm");
		return mv;
	}
	@RequestMapping(value = "replyUpdate", method = RequestMethod.POST) // ��� ���� â 
	public void replyupdate(ReplyDTO replyDTO, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü
		
		replyService.modifyReply(replyDTO);
		
		out.println("<script> self.close(); alert('����� ���� �Ǿ����ϴ�.'); window.opener.document.location.reload(); </script>");	//�θ�â ���ε� 
	}
	
}

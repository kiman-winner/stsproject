package com.cdm.web.controller.main;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdm.web.dto.ReplyDTO;
import com.cdm.web.service.ReplyService;

@Controller
@RequestMapping("/main/community/detail/")
public class ReplyController {		//��� ���� 

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

		replyService.deleteReply(reply_num);	//��� ���� 

		out.println("<script>alert('����� ���� �Ǿ����ϴ�.'); " + "location.href = '/main/community/detail?community_num="
				+ community_num + "'</script>");
	}
	
	
	
	
}

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
public class ReplyController {		//댓글 관리 

	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "replyWrite", method = RequestMethod.POST) // 댓글 작성
	public void replyWrite(ReplyDTO replyDTO, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체

		replyService.writeReply(replyDTO);

		out.println("<script>alert('댓글이 등록 되었습니다.'); " + "location.href = '/main/community/detail?community_num="
				+ replyDTO.getCommunity_num() + "'</script>");
	}
	
	@RequestMapping(value = "replyDelete", method = RequestMethod.POST) // 댓글 삭제
	public void replyDelete(@RequestParam("reply_num") int reply_num,@RequestParam("community_num") int community_num, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체

		replyService.deleteReply(reply_num);	//댓글 삭제 

		out.println("<script>alert('댓글이 삭제 되었습니다.'); " + "location.href = '/main/community/detail?community_num="
				+ community_num + "'</script>");
	}
	
	
	
	
}

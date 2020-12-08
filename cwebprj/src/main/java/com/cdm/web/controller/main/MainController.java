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

	@RequestMapping("intro") // 홈페이지 소개
	public String intro() {
		return "main/intro";
	}

	@RequestMapping("study") // 강좌 선택
	public String study() {
		return "main/study";
	}

	
	@RequestMapping(value="community/list",method=RequestMethod.GET) // 커뮤니티 게시판
	public ModelAndView communitylist() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		List<CommunityDTO> list = communityService.read();
		
		
		mv.setViewName("/main/community/list"); //list뷰 
		mv.addObject("list", list); //뷰로 보낼 데이터
		
		return mv;
	}

	@RequestMapping("community/register") // 커뮤니티 게시판 등록
	public String communityRegister() {
		return "main/community/register";
	}

	@RequestMapping(value = "community/write", method = RequestMethod.POST) // 커뮤니티 게시판 등록
	public void communityWrite(CommunityDTO vo, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체

		communityService.write(vo);

		out.println("<script>alert('등록 되었습니다.'); " + "location.href = '/main/community/list'</script>");

		// 임시
	}
	
	@RequestMapping(value="community/detail",method =RequestMethod.GET) //커뮤니티 게시판 디테일
	public ModelAndView detail(@RequestParam("community_num") int community_num) throws Exception {
		ModelAndView mv= new ModelAndView();
		
		mv.addObject("detail",communityService.detail(community_num));	//상세보기 서비스를 통해 해당 게시글 불러오기 
		mv.setViewName("main/community/detail");
		return  mv;
	}
}

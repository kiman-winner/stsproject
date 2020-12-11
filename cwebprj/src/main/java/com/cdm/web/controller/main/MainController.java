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

	@RequestMapping("intro") // 홈페이지 소개
	public String intro() {
		return "main/intro";
	}

	@RequestMapping("study") // 강좌 선택
	public String study() {
		return "main/study";
	}

	@RequestMapping(value = "community/list", method = RequestMethod.GET) // 커뮤니티 게시판 리스트 불러오기
	public ModelAndView communitylist(SearchCriteria searchCriteria) throws Exception {

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(communityService.countSearched(searchCriteria));// 전체 게시글

		ModelAndView mv = new ModelAndView();

		List<CommunityDTO> list = communityService.listSearch(searchCriteria);

		mv.setViewName("/main/community/list"); // list뷰
		mv.addObject("list", list); // 뷰로 보낼 데이터
		mv.addObject("pageMaker", pageMaker);

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

	@RequestMapping(value = "community/detail", method = RequestMethod.GET) // 커뮤니티 게시판 디테일
	public ModelAndView detail(@RequestParam("community_num") int community_num,
			@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		ModelAndView mv = new ModelAndView();

		communityService.updateViewCount(community_num); // 조회수 증가
		mv.addObject("detail", communityService.detail(community_num)); // 상세보기 서비스를 통해 해당 게시글 불러오기

		List<ReplyDTO> replyList = replyService.readReply(community_num); // 댓글 불러오기
		mv.addObject("replyList", replyList);
		mv.setViewName("main/community/detail");
		return mv;
	}

	@RequestMapping(value = "community/delete", method = RequestMethod.POST) // 커뮤니티 게시판 삭제 클릭 시
	public String delete(@RequestParam("community_num") int community_num, SearchCriteria searchCriteria,
            RedirectAttributes redirectAttributes)
			throws Exception {
		
		replyService.deleteAll(community_num);//해당 댓글 모두 삭제 
		communityService.delete(community_num);
		
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
	    redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword());	//검색 정보 유지

		return "redirect:/main/community/list";
	}

	@RequestMapping(value = "community/modify", method = RequestMethod.GET) // 커뮤니티 게시판 수정 페이지
	public ModelAndView communityModify(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("community_num") int community_num, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("title", title); // 제목과 컨텐츠를 수정 페이지에 넘겨준다.
		mv.addObject("content", content);
		mv.addObject("community_num", community_num);
		mv.setViewName("main/community/modify");

		return mv;
	}

	@RequestMapping(value = "community/modify/modifyPost", method = RequestMethod.POST) // 커뮤니티 수정 페이지에서 수정 클릭 시
	public String modify(CommunityDTO vo
			, SearchCriteria searchCriteria,
            RedirectAttributes redirectAttributes) throws Exception {

		communityService.modify(vo);
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
	    redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword());	//검색 정보 유지

		return "redirect:/main/community/list";
	}

	
}

package com.cdm.web.controller.main;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.ReplyDTO;
import com.cdm.web.page.PageMaker;
import com.cdm.web.page.SearchCriteria;
import com.cdm.web.service.CommunityService;
import com.cdm.web.service.ReplyService;

@Controller
@RequestMapping("/main/community")
public class CommunityController {//커뮤니티 컨트롤러

	@Autowired
	private CommunityService communityService;
	@Autowired
	private ReplyService replyService;

	@RequestMapping("list") // 커뮤니티 게시판 리스트 불러오기
	public ModelAndView communitylist(SearchCriteria searchCriteria) throws Exception {

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(communityService.countSearched(searchCriteria));// 검색 결과 전체 게시글 개수 등록

		ModelAndView mv = new ModelAndView();

		List<CommunityDTO> list = communityService.listSearch(searchCriteria);

		mv.setViewName("/main/community/list");
		mv.addObject("list", list); // 리스트 결과
		mv.addObject("pageMaker", pageMaker); // 페이지 관리

		return mv;
	}

	@RequestMapping("register") // 커뮤니티 게시판 등록 페이지
	public String communityRegister() {
		return "main/community/register";
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST) // 커뮤니티 게시판 등록 처리
	public void communityWrite(CommunityDTO communityDTO, HttpServletResponse response,
			MultipartHttpServletRequest mpRequest) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		communityService.register(communityDTO, mpRequest);
		out.println("<script>alert('등록 되었습니다.'); " + "location.href = '/main/community/list'</script>");

	}

	@RequestMapping("detail")
	public ModelAndView detail(@RequestParam("community_num") int community_num,
			@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {// 커뮤니티 게시판 상세보기
		ModelAndView mv = new ModelAndView();

		mv.addObject("detail", communityService.detail(community_num)); // 상세보기 서비스를 통해 해당 게시글 불러오기

		List<ReplyDTO> replyList = replyService.readReply(community_num); // 댓글 불러오기
		mv.addObject("replyList", replyList);

		List<Map<String, Object>> fileList = communityService.selectFileList(community_num); // 파일 불러오기
		mv.addObject("file", fileList);

		mv.setViewName("main/community/detail");
		return mv;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam("community_num") int community_num, SearchCriteria searchCriteria,
			RedirectAttributes redirectAttributes) throws Exception { // 커뮤니티 게시판 삭제 처리

		communityService.delete(community_num);

		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword()); // 검색 정보 유지

		return "redirect:/main/community/list";
	}

	@RequestMapping("modify")
	public ModelAndView communityModify(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("community_num") int community_num,
			@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception { // 커뮤니티 게시판 수정 페이지
		ModelAndView mv = new ModelAndView();

		mv.addObject("title", title); // 제목과 컨텐츠를 수정 페이지에 넘겨준다.
		mv.addObject("content", content);
		mv.addObject("community_num", community_num);

		List<Map<String, Object>> fileList = communityService.selectFileList(community_num); // 파일 리스트 불러오기
		mv.addObject("file", fileList);

		mv.setViewName("main/community/modify");

		return mv;
	}

	@RequestMapping(value = "modify.do", method = RequestMethod.POST) // 커뮤니티 수정 페이지에서 수정 처리
	public String modify(CommunityDTO communityDTO, SearchCriteria searchCriteria,
			RedirectAttributes redirectAttributes, @RequestParam(value = "fileNoDel[]") String[] files,
			@RequestParam(value = "fileNameDel[]") String[] fileNames, MultipartHttpServletRequest mpRequest)
			throws Exception {

		communityService.modify(communityDTO, files, fileNames, mpRequest);
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword()); // 검색 정보 유지

		return "redirect:/main/community/list";
	}

	@RequestMapping(value = "detail/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception { // 파일
																													// 다운로드
		Map<String, Object> resultMap = communityService.selectFileInfo(map);

		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");

		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils
				.readFileToByteArray(new File("D:\\Tools\\cwebprj\\file\\" + storedFileName));

		response.setContentType("application/octet-stream"); // mime 이진 파일 타입
		response.setContentLength(fileByte.length); // 크기 지정
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		// 첨부 파일 정보 헤더 셋팅

		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

}

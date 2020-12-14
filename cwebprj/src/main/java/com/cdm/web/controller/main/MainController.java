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
	public void communityWrite(CommunityDTO vo, HttpServletResponse response, MultipartHttpServletRequest mpRequest)
			throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체

		communityService.write(vo, mpRequest);

		out.println("<script>alert('등록 되었습니다.'); " + "location.href = '/main/community/list'</script>");

		// 임시
	}

	@RequestMapping(value = "community/detail", method = RequestMethod.GET) // 커뮤니티 게시판 디테일
	public ModelAndView detail(@RequestParam("community_num") int community_num,
			@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		ModelAndView mv = new ModelAndView();
	
		mv.addObject("detail", communityService.detail(community_num)); // 상세보기 서비스를 통해 해당 게시글 불러오기

		List<ReplyDTO> replyList = replyService.readReply(community_num); // 댓글 불러오기
		mv.addObject("replyList", replyList);
		
		List<Map<String, Object>> fileList = communityService.selectFileList(community_num);	//파일 불러오기
		mv.addObject("file", fileList);
		
		mv.setViewName("main/community/detail");
		return mv;
	}

	@RequestMapping(value = "community/delete", method = RequestMethod.POST) // 커뮤니티 게시판 삭제 클릭 시
	public String delete(@RequestParam("community_num") int community_num, SearchCriteria searchCriteria,
			RedirectAttributes redirectAttributes) throws Exception {

		communityService.delete(community_num);

		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword()); // 검색 정보 유지

		return "redirect:/main/community/list";
	}

	@RequestMapping(value = "community/modify", method = RequestMethod.GET) // 커뮤니티 게시판 수정 페이지
	public ModelAndView communityModify(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("community_num") int community_num,
			@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.addObject("title", title); // 제목과 컨텐츠를 수정 페이지에 넘겨준다.
		mv.addObject("content", content);
		mv.addObject("community_num", community_num);
		
		List<Map<String,Object>> fileList = communityService.selectFileList(community_num);	//파일 리스트 불러오기 
		mv.addObject("file",fileList);
		
		mv.setViewName("main/community/modify");

		return mv;
	}

	@RequestMapping(value = "community/modify/modifyPost", method = RequestMethod.POST) // 커뮤니티 수정 페이지에서 수정 클릭 시
	public String modify(CommunityDTO vo, SearchCriteria searchCriteria, RedirectAttributes redirectAttributes
			, @RequestParam(value="fileNoDel[]") String[] files,
			 @RequestParam(value="fileNameDel[]") String[] fileNames,
			 MultipartHttpServletRequest mpRequest)
			throws Exception {

		communityService.modify(vo, files, fileNames, mpRequest);
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword()); // 검색 정보 유지

		return "redirect:/main/community/list";
	}
	
	@RequestMapping(value="community/detail/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{ //파일 다운로드
		Map<String, Object> resultMap = communityService.selectFileInfo(map);
		
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("D:\\Tools\\cwebprj\\file\\"+storedFileName));
		
		response.setContentType("application/octet-stream");	//mime 이진 파일 기본값
		response.setContentLength(fileByte.length); //크기 지정
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		//첨부 파일 헤더 셋
		
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}

}

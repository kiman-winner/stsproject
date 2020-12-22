package com.cdm.web.controller.customer;

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

import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.page.PageMaker;
import com.cdm.web.page.SearchCriteria;
import com.cdm.web.service.NoticeService;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController { //공지사항 컨트롤러

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("register") // 커뮤니티 게시판 등록 페이지
	public String communityRegister() {
		return "customer/notice/register";
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST) // 커뮤니티 게시판 등록 처리
	public void communityWrite(NoticeDTO noticeDTO, HttpServletResponse response, MultipartHttpServletRequest mpRequest)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		noticeService.register(noticeDTO, mpRequest);

		out.println("<script>alert('등록 되었습니다.'); " + "location.href = '/customer/notice/list'</script>");
	}

	@RequestMapping("list")
	public ModelAndView list(SearchCriteria searchCriteria) throws Exception { // 공지사항 리스트

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(noticeService.countSearched(searchCriteria));// 검색 결과 전체 게시글 갯수 등록

		ModelAndView mv = new ModelAndView();

		List<NoticeDTO> list = noticeService.listSearch(searchCriteria);

		mv.setViewName("/customer/notice/list");

		mv.addObject("list", list); // 공지사항 리스트
		mv.addObject("pageMaker", pageMaker); // 페이지 관리
		return mv;
	}

	@RequestMapping("detail")
	public ModelAndView detail(@RequestParam("notice_num") int notice_num,
			@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception { // 공지사항 상세보기

		ModelAndView mv = new ModelAndView();

		mv.addObject("detail", noticeService.detail(notice_num)); // 해당 게시글 불러오기

		List<Map<String, Object>> fileList = noticeService.selectFileList(notice_num); // 파일 불러오기
		mv.addObject("file", fileList);

		mv.setViewName("/customer/notice/detail");
		return mv;
	}

	@RequestMapping(value = "detail/fileDown")
	public void fileDown(@RequestParam("fileNo") String fileNo, HttpServletResponse response) throws Exception { // 파일
																													// 다운로드
		Map<String, Object> resultMap = noticeService.selectFileInfo(fileNo);

		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");

		// byte[]형식으로 변환
		byte fileByte[] = org.apache.commons.io.FileUtils
				.readFileToByteArray(new File("D:\\Tools\\cwebprj\\file\\" + storedFileName));

		response.setContentType("application/octet-stream"); // mime 이진 파일
		response.setContentLength(fileByte.length); // 크기 지정
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		// 첨부 파일 정보 헤더 등록

		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST) // 공지사항 삭제 클릭 시
	public String delete(@RequestParam("notice_num") int notice_num, SearchCriteria searchCriteria,
			RedirectAttributes redirectAttributes) throws Exception {

		noticeService.delete(notice_num);

		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword()); // 검색 정보 유지

		return "redirect:/customer/notice/list";
	}

	@RequestMapping(value = "modify.do", method = RequestMethod.POST) // 커뮤니티 수정 페이지에서 수정 처리
	public String modify(NoticeDTO noticeDTO, SearchCriteria searchCriteria, RedirectAttributes redirectAttributes,
			@RequestParam(value = "fileNoDel[]") String[] files,
			@RequestParam(value = "fileNameDel[]") String[] fileNames, MultipartHttpServletRequest mpRequest)
			throws Exception {

		noticeService.modify(noticeDTO, files, fileNames, mpRequest);
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword()); // 검색 정보 유지

		return "redirect:/customer/notice/list";
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET) // 커뮤니티 게시판 수정 페이지
	public ModelAndView communityModify(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("notice_num") int notice_num, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria)
			throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.addObject("title", title); // 제목과 컨텐츠를 수정 페이지에 넘겨준다.
		mv.addObject("content", content);
		mv.addObject("notice_num", notice_num);

		List<Map<String, Object>> fileList = noticeService.selectFileList(notice_num); // 파일 리스트 불러오기
		mv.addObject("file", fileList);

		mv.setViewName("/customer/notice/modify");

		return mv;
	}

}

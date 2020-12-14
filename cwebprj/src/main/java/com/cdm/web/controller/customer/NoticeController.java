package com.cdm.web.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.page.PageMaker;
import com.cdm.web.page.SearchCriteria;
import com.cdm.web.service.NoticeService;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("list")
	public ModelAndView list(SearchCriteria searchCriteria) throws Exception {	//공지사항 리스트 
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(noticeService.countSearched(searchCriteria));// 전체 게시글 갯수

		ModelAndView mv = new ModelAndView();

		List<NoticeDTO> list = noticeService.listSearch(searchCriteria);
		System.out.println(list.get(0).getRegdate());
		System.out.println(list.get(0).getTitle());
		System.out.println(list.get(0).getContent());
		System.out.println(list.get(0).getNotice_num());
		System.out.println(list.get(0).getViewcount());

		mv.setViewName("/customer/notice/list"); // list뷰
		mv.addObject("list", list); // 뷰로 보낼 데이터
		mv.addObject("pageMaker", pageMaker);

		return mv;
	}

	@RequestMapping("detail")
	public String detail() {
		return "/customer/notice/detail";
	}
}

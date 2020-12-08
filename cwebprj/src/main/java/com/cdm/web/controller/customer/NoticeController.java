package com.cdm.web.controller.customer;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdm.web.dto.NoticeDTO;
import com.cdm.web.service.NoticeService;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("list")
	public ModelAndView list() throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		List<NoticeDTO> list = noticeService.getList(1, "TITLE", "");
		mv.setViewName("/customer/notice/list"); //list뷰 
		mv.addObject("list", list); //뷰로 보낼 데이터

		return mv;
	}

	@RequestMapping("detail")
	public String detail() {
		return "/customer/notice/detail";
	}
}

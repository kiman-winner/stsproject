package com.cdm.web.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.cdm.web.dto.MemberDTO;
import com.cdm.web.service.MemberService;

public class RememberMeInterceptor extends HandlerInterceptorAdapter { // 자동로그인 인터셉터
	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession httpSession = request.getSession();

		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if (loginCookie != null) { // 자동로그인 쿠키 존재 시
			MemberDTO memberDTO = memberService.checkLoginBefore(loginCookie.getValue());

			if (memberDTO != null) // 해당 쿠키의 사용자 정보가 있다면
				httpSession.setAttribute("member", memberDTO);
		}

		return true;
	}
}

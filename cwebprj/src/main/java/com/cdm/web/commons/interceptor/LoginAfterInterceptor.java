package com.cdm.web.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginAfterInterceptor extends HandlerInterceptorAdapter { // 로그인 후 로그인관련 페이지 인터셉터
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("member") != null) { // 로그인 후 로그인페이지 or 회원가입 페이지로 이동할 경우
			response.sendRedirect(request.getContextPath() + "/index");
			return false;
		}
		return true;
	}
}

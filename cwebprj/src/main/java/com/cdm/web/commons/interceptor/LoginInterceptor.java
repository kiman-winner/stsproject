package com.cdm.web.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter { // 로그인 처리 인터셉터
	private static final String LOGIN = "member";

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, // 화면 출력 전
			ModelAndView modelAndView) throws Exception {

		HttpSession httpSession = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object memberDTO = modelMap.get("member");

		if (memberDTO != null) { // 로그인 성공 시
			httpSession.setAttribute(LOGIN, memberDTO);
			Object destination = httpSession.getAttribute("destination"); // 로그인 요청전 url

			if (request.getParameter("useCookie") != null) { // 자동 로그인 체크 시
				Cookie loginCookie = new Cookie("loginCookie", httpSession.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(loginCookie); // 쿠키 생성, 등록
			}
			response.sendRedirect(destination != null ? (String) destination : "/index");

		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
			PrintWriter out = response.getWriter(); // 응답을 위한 객체

			out.println("<script>alert('아이디 혹은 비밀번호가 일치하지 않습니다.'); " + "location.href = '/member/login'</script>"); // 실패시
																													// 로그인
																													// 페이지
																													// 복귀
			out.close();
		}

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute(LOGIN) != null) {// 기존의 로그인 정보 제거
			httpSession.removeAttribute(LOGIN);
		}

		return true;
	}
}

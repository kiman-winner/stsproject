package com.cdm.web.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN = "member";

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession httpSession = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object memberDTO = modelMap.get("member");

		if (memberDTO != null) {
			httpSession.setAttribute(LOGIN, memberDTO);
			Object destination = httpSession.getAttribute("destination");
			
			if (request.getParameter("useCookie") != null) {
	            // 쿠키 생성
	            Cookie loginCookie = new Cookie("loginCookie", httpSession.getId());
	            loginCookie.setPath("/");
	            loginCookie.setMaxAge(60*60*24*7);
	            // 전송
	            response.addCookie(loginCookie);
	        }
			
			response.sendRedirect(destination != null ? (String) destination : "/index");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
			PrintWriter out = response.getWriter(); // 응답을 위한 객체

			out.println("<script>alert('아이디 혹은 비밀번호가 일치하지 않습니다.'); " + "location.href = '/member/login'</script>"); // 실패시 로그인 페이지 복귀
			out.close();
		}

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession httpSession = request.getSession();
		// 기존의 로그인 정보 제거
		if (httpSession.getAttribute(LOGIN) != null) {
			httpSession.removeAttribute(LOGIN);
		}

		return true;
	}
}

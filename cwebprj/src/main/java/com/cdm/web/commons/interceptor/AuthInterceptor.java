package com.cdm.web.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter { // 로그인 권한 확인 인터셉터

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) // 컨트롤러 진입전
			throws Exception {

		HttpSession httpSession = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (httpSession.getAttribute("member") == null) { // 로그인 세션이 없다면
			saveDestination(request);
			out.println("<script>alert('로그인이 필요한 서비스 입니다.'); " + "location.href = '/member/login'</script>");
			out.close();
			return false;
		}
		return true;
	}

	// 페이지 요청 정보 저장
	private void saveDestination(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}

		if (request.getMethod().equals("GET")) { // GET 방식일 때만 세션에 저장
			request.getSession().setAttribute("destination", uri + query);
		}
	}

}

package com.cdm.web.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession httpSession = request.getSession();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 한글 인코딩 설정
		PrintWriter out = response.getWriter(); // 응답을 위한 객체

		if (httpSession.getAttribute("member") == null) {
			saveDestination(request);
			out.println("<script>alert('로그인이 필요한 서비스 입니다.'); " + "location.href = '/member/login'</script>"); // 실패시 로그인
																												// 페이지
																												// 복귀
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

		if (request.getMethod().equals("GET")) {
			request.getSession().setAttribute("destination", uri + query);
		}
	}

}

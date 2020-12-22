package com.cdm.web.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter { // �α��� ���� Ȯ�� ���ͼ���

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) // ��Ʈ�ѷ� ������
			throws Exception {

		HttpSession httpSession = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (httpSession.getAttribute("member") == null) { // �α��� ������ ���ٸ�
			saveDestination(request);
			out.println("<script>alert('�α����� �ʿ��� ���� �Դϴ�.'); " + "location.href = '/member/login'</script>");
			out.close();
			return false;
		}
		return true;
	}

	// ������ ��û ���� ����
	private void saveDestination(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}

		if (request.getMethod().equals("GET")) { // GET ����� ���� ���ǿ� ����
			request.getSession().setAttribute("destination", uri + query);
		}
	}

}

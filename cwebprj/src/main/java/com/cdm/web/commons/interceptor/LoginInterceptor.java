package com.cdm.web.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter { // �α��� ó�� ���ͼ���
	private static final String LOGIN = "member";

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, // ȭ�� ��� ��
			ModelAndView modelAndView) throws Exception {

		HttpSession httpSession = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object memberDTO = modelMap.get("member");

		if (memberDTO != null) { // �α��� ���� ��
			httpSession.setAttribute(LOGIN, memberDTO);
			Object destination = httpSession.getAttribute("destination"); // �α��� ��û�� url

			if (request.getParameter("useCookie") != null) { // �ڵ� �α��� üũ ��
				Cookie loginCookie = new Cookie("loginCookie", httpSession.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(loginCookie); // ��Ű ����, ���
			}
			response.sendRedirect(destination != null ? (String) destination : "/index");

		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
			PrintWriter out = response.getWriter(); // ������ ���� ��ü

			out.println("<script>alert('���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); " + "location.href = '/member/login'</script>"); // ���н�
																													// �α���
																													// ������
																													// ����
			out.close();
		}

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute(LOGIN) != null) {// ������ �α��� ���� ����
			httpSession.removeAttribute(LOGIN);
		}

		return true;
	}
}

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
	            // ��Ű ����
	            Cookie loginCookie = new Cookie("loginCookie", httpSession.getId());
	            loginCookie.setPath("/");
	            loginCookie.setMaxAge(60*60*24*7);
	            // ����
	            response.addCookie(loginCookie);
	        }
			
			response.sendRedirect(destination != null ? (String) destination : "/index");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
			PrintWriter out = response.getWriter(); // ������ ���� ��ü

			out.println("<script>alert('���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.'); " + "location.href = '/member/login'</script>"); // ���н� �α��� ������ ����
			out.close();
		}

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession httpSession = request.getSession();
		// ������ �α��� ���� ����
		if (httpSession.getAttribute(LOGIN) != null) {
			httpSession.removeAttribute(LOGIN);
		}

		return true;
	}
}

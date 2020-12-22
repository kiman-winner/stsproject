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

public class RememberMeInterceptor extends HandlerInterceptorAdapter { // �ڵ��α��� ���ͼ���
	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession httpSession = request.getSession();

		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if (loginCookie != null) { // �ڵ��α��� ��Ű ���� ��
			MemberDTO memberDTO = memberService.checkLoginBefore(loginCookie.getValue());

			if (memberDTO != null) // �ش� ��Ű�� ����� ������ �ִٸ�
				httpSession.setAttribute("member", memberDTO);
		}

		return true;
	}
}

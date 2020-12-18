package com.cdm.web.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginAfterInterceptor extends HandlerInterceptorAdapter  {
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	        // �α��� ó���� �α��������� or ȸ������ �������� �̵��� ���
	        HttpSession session = request.getSession();
	        if (session.getAttribute("member") != null) {
	            response.sendRedirect(request.getContextPath() + "/index");	//���� ������ �̵�
	            return false;
	        }
	        return true;
	    }
}

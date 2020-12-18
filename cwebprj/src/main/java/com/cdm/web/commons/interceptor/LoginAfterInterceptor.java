package com.cdm.web.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginAfterInterceptor extends HandlerInterceptorAdapter  {
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	        // 로그인 처리후 로그인페이지 or 회원가입 페이지로 이동할 경우
	        HttpSession session = request.getSession();
	        if (session.getAttribute("member") != null) {
	            response.sendRedirect(request.getContextPath() + "/index");	//메인 페이지 이동
	            return false;
	        }
	        return true;
	    }
}

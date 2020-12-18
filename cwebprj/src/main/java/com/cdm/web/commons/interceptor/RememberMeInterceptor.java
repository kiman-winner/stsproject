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

public class RememberMeInterceptor extends HandlerInterceptorAdapter{
	 @Autowired
	    private MemberService memberService;

	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        HttpSession httpSession = request.getSession();
	        
	        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
	        if (loginCookie != null) {	//쿠키 존재 시 
	            MemberDTO memberDTO = memberService.checkLoginBefore(loginCookie.getValue());
	            if (memberDTO != null)
	                httpSession.setAttribute("member", memberDTO);	//로그인 
	        }

	        return true;
	    }
}

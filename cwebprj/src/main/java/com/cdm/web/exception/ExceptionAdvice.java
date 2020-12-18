package com.cdm.web.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {
	private static final Log logger = LogFactory.getLog(ExceptionAdvice.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView commonException(Exception e) {

		logger.info(e.toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", e);
		modelAndView.setViewName("/exception/error");

		return modelAndView;
	}
}

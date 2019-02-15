package com.myapp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception ex) {

		ModelAndView mv = new ModelAndView();

		mv.addObject("error", ex.getLocalizedMessage());
		mv.addObject("url", request.getRequestURL());

		mv.setViewName("error");

		return mv;
	}

}

package com.myapp.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(ModelMap modelMap) {
		modelMap.put("name", getLoggedInUser());

		return "welcome";
	}

	/**
	 * 
	 * @return
	 */
	public String getLoggedInUser() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;

			return userDetails.getUsername();
		} else {
			return principal.toString();
		}

	}

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.POST) public String
	 * welcome(@RequestParam String name, @RequestParam String password, ModelMap
	 * modelMap) {
	 * 
	 * if (!(loginService.validateUser(name, password))) { modelMap.put("errMsg",
	 * "Invalid credentials.");
	 * 
	 * return "login";
	 * 
	 * }
	 * 
	 * modelMap.put("name", name); modelMap.put("pwd", password);
	 * 
	 * return "welcome"; }
	 */

}

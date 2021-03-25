/**
 * Copyright(C) 2019 Luvina Software Company
 *
 * HomeController.java, Oct 8, 2019 thaopm
 * 
 */
package com.pmt.adminportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmt.adminportal.domain.User;
import com.pmt.adminportal.service.UserService;

/**
 * @author thaopm
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	
}

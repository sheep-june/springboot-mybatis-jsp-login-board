package com.canesblack.spring_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.entity.User;
//import com.canesblack.spring_project1.service.MenuRestService;
import com.canesblack.spring_project1.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PageController {
	//@Autowired
	//private MenuRestService menuRestService;
	
	@Autowired
	private UserService userService;
	
//	@PostMapping()
//	@PutMapping()
//	@DeleteMapping()
	//  / => localhost:8080
	@GetMapping("/")
	public String home() {
		return "index";
	}	
	
	
	@GetMapping("/registerPage")
	public String registerPage(HttpServletRequest request,org.springframework.ui.Model model) {
		CsrfToken csrfToken = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
		model.addAttribute("_csrf",csrfToken);
		return "register/index";
	}
	
	@GetMapping("/loginPage")
	public String loginPage(HttpServletRequest request,org.springframework.ui.Model model) {
		CsrfToken csrfToken = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
		model.addAttribute("_csrf",csrfToken);
		return "login/index";
	}
	
	/*
	@GetMapping("/noticeAddPage")
	public String noticeAddPage(Model model,Authentication authentication) {
		String writer = userService.findWriter(authentication.getName());
		model.addAttribute("writer", writer);
		return "noticeAdd/index";
	}
	
	@GetMapping("/noticeCheckPage")
	public String showNoticeCheckPage(@RequestParam("idx") int idx,Model model) {
		Menu menu = menuRestService.boardContent(idx);
		model.addAttribute("menu",menu);
		return "noticeCheck/index";
	}
	@GetMapping("/noticeModifyPage")
	public String showNoticeModifyPage(@RequestParam("idx") int idx,Model model) {
		Menu menu = menuRestService.boardContent(idx);
		model.addAttribute("menu",menu);
		return "noticeModify/index";
	}*/
	
}

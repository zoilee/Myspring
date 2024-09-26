package com.zoile.kdtcom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String main() {
		return "kdtcom.index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "kdtcom.register";
	}
}

package com.skmproject.chatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-08
 */
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
}

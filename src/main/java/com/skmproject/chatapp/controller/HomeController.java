package com.skmproject.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.skmproject.chatapp.model.JoinRoom;
import com.skmproject.chatapp.service.RoomService;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-08
 */
@Controller
public class HomeController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("joinRoom", new JoinRoom());
		return "index";
	}
	
	@PostMapping("/join")
	public String joinRoom(@ModelAttribute JoinRoom payload,Model model) {
		boolean result = roomService.verifyRoom(payload);
		if(result) {
			model.addAttribute("data", payload);
			return "chat";
		} else {
			model.addAttribute("error",true);
			String message = "Invalid Room ID or Password";
			model.addAttribute("message", message);
			return "index";
		}
	}
}

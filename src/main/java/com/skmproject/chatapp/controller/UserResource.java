package com.skmproject.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skmproject.chatapp.model.User;
import com.skmproject.chatapp.service.OnlineTrackerService;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-09
 */
@Controller
@RequestMapping("/api")
public class UserResource {
	
	@Autowired
	private OnlineTrackerService trackerService;
	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getUser(@PathVariable("username") String username) {
		User user = trackerService.getUser(username);
		if(user == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(user);
		}
	}
	
}

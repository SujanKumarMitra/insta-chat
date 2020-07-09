package com.skmproject.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skmproject.chatapp.model.Event;
import com.skmproject.chatapp.model.EventType;
import com.skmproject.chatapp.model.User;
import com.skmproject.chatapp.service.OnlineTrackerService;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-09
 */
@RestController
public class EventsController {
	
	@Autowired
	private OnlineTrackerService trackerService;
	
	@SubscribeMapping("/events/{roomId}")
	public ResponseEntity<Event> onSubscribe(@Header("simpSessionId") String sessionId) {
		
		User user = trackerService.getUserFromSession(sessionId);
		if(user != null) {
			return ResponseEntity
					.ok()
					.body(new Event(EventType.INFO, user));
		}
		else {
			return ResponseEntity
					.notFound()
					.build();
		}
	}
}

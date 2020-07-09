package com.skmproject.chatapp.controller.advice;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.skmproject.chatapp.model.Event;
import com.skmproject.chatapp.util.DestinationConstants;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-09
 */
@Controller
public class EventController {
	
	@MessageMapping("/event/{roomId}")
	@SendTo(DestinationConstants.EVENT_DESTINATION+"{roomId}")
	public Event onEvent(@Payload Event event) {
		return event;
	}
}

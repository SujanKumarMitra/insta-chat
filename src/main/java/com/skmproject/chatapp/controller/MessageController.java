package com.skmproject.chatapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.skmproject.chatapp.model.Message;
import com.skmproject.chatapp.util.DestinationConstants;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-08
 */
@RestController
public class MessageController {
	
	@MessageMapping("/message/{roomId}")
	@SendTo(DestinationConstants.MESSAGE_DESTINATION+"{roomId}")
	public Message onNewMessage(@Payload Message message) {
		return message;
	}
}

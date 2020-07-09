package com.skmproject.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-08
 */
@Service
public class SocketInterceptor implements ChannelInterceptor {
	
	@Autowired
	private OnlineTrackerService trackerService;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		if(accessor.getCommand() == StompCommand.SUBSCRIBE && trackerService.isSubscribeToMessageDestination(message)) {
			authenticate(accessor);
		}
		return message;
	}

	private void authenticate(StompHeaderAccessor accessor) {
		String username = accessor.getFirstNativeHeader("username");
		if (username == null) {
			throw new RuntimeException("missing username header");
		}
	}

}

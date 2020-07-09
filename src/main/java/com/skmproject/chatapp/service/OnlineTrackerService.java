package com.skmproject.chatapp.service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.skmproject.chatapp.model.Event;
import com.skmproject.chatapp.model.EventType;
import com.skmproject.chatapp.model.Room;
import com.skmproject.chatapp.model.User;
import com.skmproject.chatapp.util.DestinationConstants;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-08
 */
@Service
public class OnlineTrackerService {

	private Map<String, Set<User>> onlineUsers;
	
	@Autowired
	private RoomService roomService;

	@Autowired
	@Lazy
	SimpMessagingTemplate messagingTemplate;

	/**
	 * @param onlineUsers
	 */
	public OnlineTrackerService() {
		this.onlineUsers = new HashMap<>();
	}

	/**
	 * @return the onlineUsers
	 */
	public Set<User> getOnlineUsers(String roomId) {
		return onlineUsers.get(roomId);
	}

	public User addOnlineUser(String roomId, String sessionId, String username) {
		User user = getUser(sessionId, username,roomId);
		Set<User> users = onlineUsers.get(roomId);
		if (users == null) {
			users = new LinkedHashSet<>();
			onlineUsers.put(roomId, users);
		}
		return (users.add(user)) ? user : null;
	}

	public User removeOnlineUser(String sessionId) {
		User userToRemove = null;
		for (Entry<String, Set<User>> entry : onlineUsers.entrySet()) {
			Set<User> users = entry.getValue();
			for (User user : users) {
				if (user.getSessionId().equals(sessionId)) {
					userToRemove = user;
					break;
				}
			}
			if (userToRemove != null) {
				users.remove(userToRemove);
				break;
			}
		}

		return userToRemove;
	}

	private User getUser(String sessionId, String username,String roomId) {
		User user = new User(sessionId, username);
		Room room = roomService.getRoom(roomId);
		user.addRoom(room);
		return user;
	}

	@EventListener(classes = SessionSubscribeEvent.class)
	public void onSubscribe(SessionSubscribeEvent event) {
		Message<byte[]> message = event.getMessage();
		if (!isSubscribeToMessageDestination(message)) {
			return;
		}
		String sessionId = getSessionId(message);
		String roomId = getRoomId(message);
		String username = getUsername(message);
		User user = addOnlineUser(roomId, sessionId, username);
		messagingTemplate.convertAndSend(DestinationConstants.EVENT_DESTINATION + roomId, new Event(EventType.JOIN, user));
	}

	@EventListener(classes = SessionDisconnectEvent.class)
	public void onDisconnect(SessionDisconnectEvent event) {
		String sessionId = event.getSessionId();
		User user = removeOnlineUser(sessionId);
		Set<Room> rooms = user.getRooms();
		rooms.forEach(room -> {
			messagingTemplate.convertAndSend(DestinationConstants.EVENT_DESTINATION + room.getId(), new Event(EventType.LEAVE, user));			
		});
	}

	public String getSessionId(Message<?> message) {
		MessageHeaders headers = message.getHeaders();
		Object sessionId = headers.get("simpSessionId");
		return (String) sessionId;
	}

	public String getRoomId(Message<?> message) {
		MessageHeaders headers = message.getHeaders();
		String destination = (String) headers.get("simpDestination");
		return destination.substring(DestinationConstants.MESSAGE_DESTINATION.length());

	}

	public String getUsername(Message<?> message) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		String username = accessor.getFirstNativeHeader("username");
		return username;

	}

	public boolean isSubscribeToMessageDestination(Message<?> message) {
		MessageHeaders headers = message.getHeaders();
		String destination = (String) headers.get("simpDestination");
		return destination.startsWith(DestinationConstants.MESSAGE_DESTINATION);
	}

	/**
	 * @param username
	 */
	public User getUser(String username) {
		for (Entry<String, Set<User>> entry : onlineUsers.entrySet()) {
			Set<User> users = entry.getValue();
			for (User user : users) {
				if (user.getUsername().equals(username)) {
					return user;
				}
			}
		}
		return null;
	}

}

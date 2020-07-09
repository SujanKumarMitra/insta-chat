package com.skmproject.chatapp.service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

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
	
	private boolean txFlag;
	
	@Autowired
	private RoomService roomService;

	@Autowired
	@Lazy
	private SimpMessagingTemplate messagingTemplate;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @param onlineUsers
	 */
	public OnlineTrackerService() {
		this.onlineUsers = new HashMap<>();
	}

	/**
	 * @return the onlineUsers
	 */
	public synchronized Set<User> getOnlineUsers(String roomId) {
		if(txFlag) {
			try {
				wait();
			} catch (InterruptedException e) {
				logger.warn(e.getMessage());
			}
		}
		return onlineUsers.get(roomId);
	}

	public synchronized User addOnlineUser(String roomId, String sessionId, String username) {
		txFlag = true;
		User user = getUser(sessionId, username,roomId);
		Set<User> users = onlineUsers.get(roomId);
		if (users == null) {
			users = new LinkedHashSet<>();
			onlineUsers.put(roomId, users);
		}
		boolean result = users.add(user);
		txFlag = false;
		notifyAll();
		if(result) {
			notifyOnline(user);
			return user;
		}
		return null;
	}

	public synchronized User removeOnlineUser(String sessionId) {
		txFlag = true;
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
		txFlag = false;
		notifyAll();
		return userToRemove;
	}

	private User getUser(String sessionId, String username,String roomId) {
		User user = new User(sessionId, username);
		Room room = roomService.getRoom(roomId);
		user.setRoom(room);
		return user;
	}

	public void notifyOnline(User user) {
		messagingTemplate.convertAndSend(DestinationConstants.EVENT_DESTINATION + user.getRoom().getId(), new Event(EventType.JOIN, user));			
	}

	@EventListener(classes = SessionDisconnectEvent.class)
	public void notifyOffline(SessionDisconnectEvent event) {
		String sessionId = event.getSessionId();
		User user = removeOnlineUser(sessionId);
		messagingTemplate.convertAndSend(DestinationConstants.EVENT_DESTINATION + user.getRoom().getId(), new Event(EventType.LEAVE, user));			
	}

	public boolean isSubscribeToMessageDestination(Message<?> message) {
		MessageHeaders headers = message.getHeaders();
		String destination = (String) headers.get("simpDestination");
		return destination.startsWith(DestinationConstants.MESSAGE_DESTINATION);
	}

	/**
	 * @param username
	 */
	public synchronized User getUser(String username) {
		if (txFlag) {
			try {
				wait();
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
		}
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

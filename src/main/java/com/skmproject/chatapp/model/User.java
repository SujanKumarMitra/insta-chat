package com.skmproject.chatapp.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-08
 */
public class User {
	String sessionId;
	String username;
	@JsonIgnore
	Set<Room> rooms;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param username
	 */
	public User(String username) {
		super();
		this.username = username;
	}
	
	public User(String sessionId, String username) {
		this.sessionId = sessionId;
		this.username = username;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void addRoom(Room room) {
		if(rooms == null) {
			rooms = new LinkedHashSet<>();
		}
		rooms.add(room);
	}
	
	public Set<Room> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "User [sessionId=" + sessionId + ", username=" + username + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	

}

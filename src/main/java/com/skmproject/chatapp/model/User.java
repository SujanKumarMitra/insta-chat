package com.skmproject.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-08
 */
public class User {
	String sessionId;
	String username;
	@JsonIgnore
	Room room;

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

	/**
	 * @param rooms the rooms to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
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

package com.skmproject.chatapp.model;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-09
 */

public class Event {
	EventType type;
	User user;

	/**
	 * 
	 */
	public Event() {
		super();
	}

	/**
	 * @param type
	 * @param user
	 */
	public Event(EventType type, User user) {
		super();
		this.type = type;
		this.user = user;
	}

	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Event [type=" + type + ", user=" + user + "]";
	}
}

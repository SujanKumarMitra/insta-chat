package com.skmproject.chatapp.model;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-09
 */
public enum EventType {
	JOIN("JOIN"),
	LEAVE("LEAVE"),
	TYPING("TYPING");

	String event;
	/**
	 * @param string
	 */
	EventType(String string) {
		this.event = string;
	}
	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}
	
}

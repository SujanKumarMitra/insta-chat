package com.skmproject.chatapp.model;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-08
 */
public class Message {

	User from;
	String content;

	/**
	 * 
	 */
	public Message() {
	}

	/**
	 * @param to
	 * @param from
	 * @param content
	 */
	public Message(User from, String content) {
		this.from = from;
		this.content = content;
	}

	/**
	 * @return the from
	 */
	public User getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(User from) {
		this.from = from;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [from=" + from + ", content=" + content + "]";
	}

}

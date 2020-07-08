package com.skmproject.chatapp.model;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-08
 */
public class JoinRoom {
	String id;
	String password;
	String username;

	/**
	 * 
	 */
	public JoinRoom() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "JoinRoom [id=" + id + ", password=" + password + ", username=" + username + "]";
	}

}

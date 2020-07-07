package com.skmproject.chatapp.model;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
public class DefaultRoom implements Room {
	
	public String roomId;
	public String password;

	/**
	 * 
	 */
	public DefaultRoom() {
	}

	/**
	 * @param roomId the roomId to set
	 * @param password the password to set
	 */
	public DefaultRoom(String roomId, String password) {
		this.roomId = roomId;
		this.password = password;
	}



	/**
	 * @return the roomId
	 */
	@Override
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}

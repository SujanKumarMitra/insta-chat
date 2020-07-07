package com.skmproject.chatapp.model;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
public class CreateRoom {

	private String roomId;
	private String password;
	private boolean autoGenerate;

	/**
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
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
	 * @return the autoGenerate
	 */
	public boolean isAutoGenerate() {
		return autoGenerate;
	}

	/**
	 * @param autoGenerate the autoGenerate to set
	 */
	public void setAutoGenerate(boolean autoGenerate) {
		this.autoGenerate = autoGenerate;
	}

	@Override
	public String toString() {
		return "CreateRoom [roomId=" + roomId + ", password=" + password + ", autoGenerate=" + autoGenerate + "]";
	}
	
}

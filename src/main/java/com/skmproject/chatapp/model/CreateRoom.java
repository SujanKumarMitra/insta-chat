package com.skmproject.chatapp.model;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
public class CreateRoom {

	private String id;
	private String password;
	private boolean autoGenerate;

	/**
	 * @return the roomId
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setId(String roomId) {
		this.id = roomId;
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
		return "CreateRoom [id=" + id + ", password=" + password + ", autoGenerate=" + autoGenerate + "]";
	}
	
}

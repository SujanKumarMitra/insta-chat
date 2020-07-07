package com.skmproject.chatapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
@Entity
@Table(name = "rooms")
public class DefaultRoom implements Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	int id;
	
	@Column(name = "room_id",unique = true)
	public String roomId;

	@Column(name = "password")
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

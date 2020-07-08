package com.skmproject.chatapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
@Entity
@Table(name = "rooms")
public class DefaultRoom implements Room,Serializable {
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 8622193784528944845L;

	@Id
	@Column(name = "room_id",unique = true)
	public String id;

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
	public DefaultRoom(String id, String password) {
		this.id = id;
		this.password = password;
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
	@Override
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}

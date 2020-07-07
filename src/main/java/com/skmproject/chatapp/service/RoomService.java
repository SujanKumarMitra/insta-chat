package com.skmproject.chatapp.service;

import com.skmproject.chatapp.exception.RoomAlreadyExistsException;
import com.skmproject.chatapp.exception.RoomNotFoundException;
import com.skmproject.chatapp.model.CreateRoom;
import com.skmproject.chatapp.model.Room;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
public interface RoomService {
		
	public Room createRoom(CreateRoom createRoom) throws RoomAlreadyExistsException;

	public Room getRoom(String roomId) throws RoomNotFoundException;
}

package com.skmproject.chatapp.service;

import com.skmproject.chatapp.exception.RoomAlreadyExistsException;
import com.skmproject.chatapp.model.CreateRoom;
import com.skmproject.chatapp.model.Room;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
public interface RoomService {
	
	public boolean existsRoom(String roomId);
	
	public Room createRoom(CreateRoom createRoom) throws RoomAlreadyExistsException;
}

package com.skmproject.chatapp.dao;

import com.skmproject.chatapp.model.Room;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
public interface RoomDao {
	boolean existsRoom(String roomId);

	Room save(Room room);

	Room getRoom(String roomId);
}

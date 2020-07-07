package com.skmproject.chatapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skmproject.chatapp.dao.repository.RoomRepository;
import com.skmproject.chatapp.exception.RoomNotFoundException;
import com.skmproject.chatapp.model.DefaultRoom;
import com.skmproject.chatapp.model.Room;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
@Repository
public class DefaultRoomDao implements RoomDao {

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public boolean existsRoom(String roomId) {
		return roomRepository.existsByRoomId(roomId);
	}

	@Override
	public Room getRoom(String roomId) {
		return roomRepository.findByRoomId(roomId)
				.orElseThrow(() -> new RoomNotFoundException("room not found with id " + roomId));
	}

	@Override
	public Room save(Room room) {
		DefaultRoom roomToSave = (DefaultRoom) room;
		return roomRepository.save(roomToSave);
	}


}

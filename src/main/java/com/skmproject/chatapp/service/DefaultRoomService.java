package com.skmproject.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.skmproject.chatapp.dao.RoomDao;
import com.skmproject.chatapp.exception.RoomAlreadyExistsException;
import com.skmproject.chatapp.model.CreateRoom;
import com.skmproject.chatapp.model.DefaultRoom;
import com.skmproject.chatapp.model.Room;
import com.skmproject.chatapp.util.RandomGenerator;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
public class DefaultRoomService implements RoomService {
	
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private RandomGenerator randomGenerator;

	@Override
	public boolean existsRoom(String roomId) {
		return roomDao.existsByRoomId(roomId);
	}

	@Override
	public Room createRoom(CreateRoom createRoom) throws RoomAlreadyExistsException {
		String roomId;
		String password = createRoom.getPassword();
		if(createRoom.isAutoGenerate()) {
			roomId = randomGenerator.generateRandom();
		} else {
			roomId = createRoom.getRoomId();
			if (roomDao.existsByRoomId(roomId)) {
				throw new RoomAlreadyExistsException(roomId + " already exists!");
			}
		}
		Room room = new DefaultRoom(roomId,password);
		room = roomDao.save(room);
		return room;
	}

}

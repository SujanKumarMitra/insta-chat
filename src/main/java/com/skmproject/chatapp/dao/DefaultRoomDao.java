package com.skmproject.chatapp.dao;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

	List<DefaultRoom> cachedRoomRepository;

	@Autowired
	private RoomRepository roomRepository;

	public DefaultRoomDao() {
	}

	@Override
	public boolean existsRoom(String roomId) {

		Optional<DefaultRoom> optionalRoom = cachedRoomRepository
				.parallelStream()
				.filter(room -> room.getId().equals(roomId)).findFirst();

		return optionalRoom.isPresent();
	}

	@Override
	public Room getRoom(String roomId) {
		
		return cachedRoomRepository
				.parallelStream()
				.filter(room -> room.getId().equals(roomId))
				.findFirst()
				.orElseThrow(() -> new RoomNotFoundException("room not found with id " + roomId));
	}

	@Override
	public Room save(Room room) {
		DefaultRoom roomToSave = (DefaultRoom) room;
		cachedRoomRepository.add(roomToSave);
		return roomToSave;
	}

	@Override
	public boolean existsRoom(String id, String password) {
		return cachedRoomRepository
				.parallelStream()
				.filter(room-> room.getId().equals(id) && room.getPassword().equals(password))
				.findFirst()
				.isPresent();
	}

	@PostConstruct
	private void init() {
		cachedRoomRepository = roomRepository.findAll();
	}
	
	@PreDestroy
	private void destroy() {
		roomRepository.saveAll(cachedRoomRepository);
	}
	
	@Scheduled(cron = "0 */30 * ? * *")
	protected void cacheRefresh() {
		cachedRoomRepository = roomRepository.saveAll(cachedRoomRepository);
	}

}

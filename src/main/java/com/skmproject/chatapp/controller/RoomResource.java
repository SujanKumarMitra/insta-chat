package com.skmproject.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skmproject.chatapp.model.CreateRoom;
import com.skmproject.chatapp.model.Room;
import com.skmproject.chatapp.service.RoomService;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/api")
public class RoomResource {

	@Autowired
	private RoomService roomService;

	@PostMapping("/room")
	public ResponseEntity<Room> createRoom(@RequestBody CreateRoom payload) {
		Room room = roomService.createRoom(payload);
		return ResponseEntity.status(HttpStatus.CREATED).body(room);
	}

	@GetMapping("/room/{roomId}")
	public ResponseEntity<Room> getRoom(@PathVariable("roomId") String roomId) {
		Room room = roomService.getRoom(roomId);
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(room);
	}

}

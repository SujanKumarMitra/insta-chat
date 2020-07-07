package com.skmproject.chatapp.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skmproject.chatapp.model.DefaultRoom;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
@Repository
public interface RoomRepository extends JpaRepository<DefaultRoom, Integer> {
	boolean existsByRoomId(String roomId);

	Optional<DefaultRoom> findByRoomId(String roomId);

}

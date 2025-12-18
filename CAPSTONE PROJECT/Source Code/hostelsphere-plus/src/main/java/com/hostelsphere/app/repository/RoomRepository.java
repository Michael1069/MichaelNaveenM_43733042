package com.hostelsphere.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelsphere.app.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}

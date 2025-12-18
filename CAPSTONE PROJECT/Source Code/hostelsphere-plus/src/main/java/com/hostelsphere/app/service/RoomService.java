package com.hostelsphere.app.service;

import java.util.List;
import com.hostelsphere.app.entity.Room;

public interface RoomService {

    Room saveRoom(Room room);

    List<Room> getAllRooms();

    Room getRoomById(Long id);
}

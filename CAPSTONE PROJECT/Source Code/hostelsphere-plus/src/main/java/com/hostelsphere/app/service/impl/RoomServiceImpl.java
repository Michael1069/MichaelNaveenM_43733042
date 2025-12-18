package com.hostelsphere.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelsphere.app.entity.Room;
import com.hostelsphere.app.repository.RoomRepository;
import com.hostelsphere.app.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }
}

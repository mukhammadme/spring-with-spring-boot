package com.linc.lil.learningspring.business;

import com.linc.lil.learningspring.data.Room;
import com.linc.lil.learningspring.data.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        Iterable<Room> rooms = this.roomRepository.findAll();
        List<Room> roomList = new ArrayList<>();
        rooms.forEach(room -> roomList.add(room));
        roomList.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }
        });

        return roomList;
    }
}

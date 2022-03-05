package com.pet.hotel.controller;

import com.pet.hotel.exception.ResourceNotFoundException;
import com.pet.hotel.model.Room;
import com.pet.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/api")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    // Get All Rooms
    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Create a new Room
    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room) {
        return (Room) roomRepository.save(room);
    }

    // Get a Single Room
    @GetMapping("/rooms/{id}")
    public Room getRoomById(@PathVariable(value = "id") Long roomId) throws Throwable {
        return (Room) roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
    }

    // Update a Room
    @PutMapping("/rooms/{id}")
    public Room updateRoom(@PathVariable(value = "id") Long roomId,
                           @Valid @RequestBody Room roomDetails) throws Throwable {

        Room room = (Room) roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setFloor(roomDetails.getFloor());
        room.setPeople(roomDetails.getPeople());
        room.setPrice(roomDetails.getPrice());
        room.setIs_available(roomDetails.getIs_available());

        Room updatedRoom = (Room) roomRepository.save(room);
        return updatedRoom;
    }

    // Delete a Room
    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable(value = "id") Long roomId) throws Throwable {
        Room room = (Room) roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        roomRepository.delete(room);

        return ResponseEntity.ok().build();
    }
}

package com.ravan.hotel_reservation.controller;

import com.ravan.hotel_reservation.DTOs.RoomRequestDTO;
import com.ravan.hotel_reservation.DTOs.RoomResponseDTO;
import com.ravan.hotel_reservation.serviceImpl.RoomServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomServiceImpl roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAll() {
        List<RoomResponseDTO> dtos = roomService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getById(@PathVariable Long id) {
        RoomResponseDTO dto = roomService.getRoomById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RoomResponseDTO> create(@RequestBody @Valid RoomRequestDTO dto) {
        RoomResponseDTO responseDTO = roomService.createRoom(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> update(@PathVariable Long id,@RequestBody RoomRequestDTO dto ) {
        RoomResponseDTO responseDTO = roomService.updateRoomDetails(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.deleteRoomById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

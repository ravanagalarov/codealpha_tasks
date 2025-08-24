package com.ravan.hotel_reservation.service;

import com.ravan.hotel_reservation.DTOs.RoomRequestDTO;
import com.ravan.hotel_reservation.DTOs.RoomResponseDTO;

import java.util.List;

public interface RoomService {
    List<RoomResponseDTO> getAll();

    RoomResponseDTO getRoomById(Long id);

    RoomResponseDTO updateRoomDetails(Long id, RoomRequestDTO dto);

    RoomResponseDTO createRoom(RoomRequestDTO dto);

    void deleteRoomById(Long id);

}

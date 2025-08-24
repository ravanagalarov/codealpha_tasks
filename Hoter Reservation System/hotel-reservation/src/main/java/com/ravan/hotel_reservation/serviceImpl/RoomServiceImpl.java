package com.ravan.hotel_reservation.serviceImpl;

import com.ravan.hotel_reservation.DTOs.RoomRequestDTO;
import com.ravan.hotel_reservation.DTOs.RoomResponseDTO;
import com.ravan.hotel_reservation.ExceptionHandler.ResourceNotFoundException;
import com.ravan.hotel_reservation.entity.Room;
import com.ravan.hotel_reservation.repository.RoomRepository;
import com.ravan.hotel_reservation.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper mapper;

    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper mapper) {
        this.mapper = mapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomResponseDTO> getAll() {
        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        List<RoomResponseDTO> dtos = rooms.stream().
                map(room -> mapper.map(room,RoomResponseDTO.class)).toList();
        
        return dtos;
    }

    @Override
    public RoomResponseDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);
        return mapper.map(room,RoomResponseDTO.class);
    }

    @Override
    @Transactional
    public RoomResponseDTO updateRoomDetails(Long id, RoomRequestDTO dto) {
        Room room = roomRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);

        mapper.map(dto,room);
        Room updated  = roomRepository.save(room);

        return mapper.map(updated, RoomResponseDTO.class);
    }

    @Override
    @Transactional
    public RoomResponseDTO createRoom(RoomRequestDTO dto) {
        Room room = mapper.map(dto,Room.class);
        Room created = roomRepository.save(room);
        return mapper.map(created, RoomResponseDTO.class);
    }

    @Override
    public void deleteRoomById(Long id) {
        Room room = roomRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);

        roomRepository.deleteById(id);

    }
}

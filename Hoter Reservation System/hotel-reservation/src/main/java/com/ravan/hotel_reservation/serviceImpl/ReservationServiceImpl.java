package com.ravan.hotel_reservation.serviceImpl;

import com.ravan.hotel_reservation.DTOs.ReservationRequestDTO;
import com.ravan.hotel_reservation.DTOs.ReservationResponseDTO;
import com.ravan.hotel_reservation.ExceptionHandler.ResourceNotFoundException;
import com.ravan.hotel_reservation.ExceptionHandler.RoomNotAvailableException;
import com.ravan.hotel_reservation.ExceptionHandler.UserNotFoundException;
import com.ravan.hotel_reservation.entity.Reservation;
import com.ravan.hotel_reservation.entity.Room;
import com.ravan.hotel_reservation.entity.User;
import com.ravan.hotel_reservation.repository.ReservationRepository;
import com.ravan.hotel_reservation.repository.RoomRepository;
import com.ravan.hotel_reservation.repository.UserRepository;
import com.ravan.hotel_reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ModelMapper mapper;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;


    @Override
    @Transactional
    public ReservationResponseDTO createReservation(ReservationRequestDTO dto) {

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(ResourceNotFoundException::new);

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(UserNotFoundException::new);


        boolean isBooked = reservationRepository.existsBookingForRoomBetweenDates(
                dto.getRoomId(), dto.getCheckInDate(), dto.getCheckOutDate());

        if (isBooked) {
            throw new RoomNotAvailableException();
        }
        Reservation reservation = mapper.map(dto, Reservation.class);
        reservation.setRoom(room);
        reservation.setUser(user);

        Reservation created = reservationRepository.save(reservation);

        return mapper.map(created, ReservationResponseDTO.class);

    }

    @Override
    public List<ReservationResponseDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        List<ReservationResponseDTO> dtos = reservations.stream().
                map(reservation -> mapper.map(reservation, ReservationResponseDTO.class)).toList();

        return dtos;
    }

    @Override
    public ReservationResponseDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);

        return mapper.map(reservation, ReservationResponseDTO.class);
    }

    @Override
    public void deleteReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);

        reservationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ReservationResponseDTO updateReservationDetails(Long id, ReservationRequestDTO dto) {
        Reservation reservation = reservationRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);

        mapper.map(dto,reservation);
        Reservation updated = reservationRepository.save(reservation);

        return mapper.map(updated, ReservationResponseDTO.class );
    }

    @Override
    public ReservationResponseDTO getReservationByUserId(Long userId) {
        Reservation reservation = reservationRepository.findReservationByUserId(userId)
                .orElseThrow(ResourceNotFoundException::new);

        return mapper.map(reservation, ReservationResponseDTO.class);
    }

}

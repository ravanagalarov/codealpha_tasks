package com.ravan.hotel_reservation.service;

import com.ravan.hotel_reservation.DTOs.ReservationRequestDTO;
import com.ravan.hotel_reservation.DTOs.ReservationResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationResponseDTO createReservation(ReservationRequestDTO dto);

    List<ReservationResponseDTO> getAllReservations();

    ReservationResponseDTO getReservationById(Long id);

    void deleteReservationById(Long id);

    ReservationResponseDTO updateReservationDetails(Long id, ReservationRequestDTO dto);

    ReservationResponseDTO getReservationByUserId(Long userId);


}

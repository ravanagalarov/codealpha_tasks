package com.ravan.hotel_reservation.DTOs;

import com.ravan.hotel_reservation.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private UserResponseDTO userResponseDTO;
    private RoomResponseDTO roomResponseDTO;
    private ReservationStatus status;
    private PaymentResponseDTO paymentResponseDTO;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}

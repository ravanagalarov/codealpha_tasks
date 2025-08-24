package com.ravan.hotel_reservation.DTOs;

import com.ravan.hotel_reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

    private List<ReservationResponseDTO> reservations;
}

package com.ravan.hotel_reservation.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {
    @NotNull(message = "User id must be filled")
    private Long userId;

    @NotNull(message = "Room id must be filled")
    private Long roomId;

    @NotBlank
    private LocalDate checkInDate;

    @NotNull
    private LocalDate checkOutDate;
}

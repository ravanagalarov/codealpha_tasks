package com.ravan.hotel_reservation.DTOs;

import com.ravan.hotel_reservation.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {
    @NotBlank(message = "Room number must be provided")
    private String number;

    @NotNull(message = "Room type must be specified")
    private RoomType roomType;

    @NotNull(message = "Price must be specified")
    private Double price;
}

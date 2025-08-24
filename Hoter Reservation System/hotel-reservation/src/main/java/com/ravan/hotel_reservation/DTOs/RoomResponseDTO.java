package com.ravan.hotel_reservation.DTOs;

import com.ravan.hotel_reservation.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {
    private Long id;
    private String number;
    private RoomType roomType;
    private Double price;
    private Boolean available;
}


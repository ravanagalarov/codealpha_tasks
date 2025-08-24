package com.ravan.hotel_reservation.entity;

import com.ravan.hotel_reservation.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Enumerated(EnumType.STRING)
    RoomType roomType;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    List<Reservation> reservations = new ArrayList<>();

    private double price;

    private boolean available;
}

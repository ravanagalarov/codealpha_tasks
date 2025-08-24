package com.ravan.hotel_reservation.entity;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    private String name;
    private String email;
    private String phone;



//-------Security field-leri--------
//    private boolean enabled = true;
//    @Enumerated(EnumType.STRING)
//    private Role role; // ADMIN, USER
//    private String username;
    //  private String password;
}


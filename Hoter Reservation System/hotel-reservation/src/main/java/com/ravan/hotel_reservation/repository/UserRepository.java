package com.ravan.hotel_reservation.repository;

import com.ravan.hotel_reservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

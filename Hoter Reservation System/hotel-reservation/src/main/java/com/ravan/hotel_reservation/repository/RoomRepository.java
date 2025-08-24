package com.ravan.hotel_reservation.repository;

import com.ravan.hotel_reservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}

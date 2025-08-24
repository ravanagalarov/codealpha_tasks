package com.ravan.hotel_reservation.repository;

import com.ravan.hotel_reservation.DTOs.ReservationResponseDTO;
import com.ravan.hotel_reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reservation r " +
            "WHERE r.room.id = :roomId AND r.status = 'CONFIRMED' " +
            "AND r.checkInDate < :checkOut AND r.checkOutDate > :checkIn")
    boolean existsBookingForRoomBetweenDates(@Param("roomId") Long roomId,
                                             @Param("checkIn") LocalDate checkIn,
                                             @Param("checkOut") LocalDate checkOut);

    Optional<Reservation> findReservationByUserId(Long userId);

}

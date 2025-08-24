package com.ravan.hotel_reservation.repository;

import com.ravan.hotel_reservation.entity.Payment;
import com.ravan.hotel_reservation.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findPaymentByReservationId(Long reservationId);

    List<Payment> findByStatus(PaymentStatus status);
}

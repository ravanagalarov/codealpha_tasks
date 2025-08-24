package com.ravan.hotel_reservation.service;

import com.ravan.hotel_reservation.DTOs.PaymentRequestDTO;
import com.ravan.hotel_reservation.DTOs.PaymentResponseDTO;
import com.ravan.hotel_reservation.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentRequestDTO dto);

    PaymentResponseDTO getPaymentById(Long id);

    List<PaymentResponseDTO> getAllPayments();

    PaymentResponseDTO updatePaymentMethod(Long id, String newMethod);

    void deletePayment(Long id);

    PaymentResponseDTO getPaymentByReservationId(Long reservationId);

    List<PaymentResponseDTO> getPaymentByStatus(PaymentStatus status);

}

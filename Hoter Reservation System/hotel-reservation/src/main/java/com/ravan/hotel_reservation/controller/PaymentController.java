package com.ravan.hotel_reservation.controller;

import com.ravan.hotel_reservation.DTOs.PaymentRequestDTO;
import com.ravan.hotel_reservation.DTOs.PaymentResponseDTO;
import com.ravan.hotel_reservation.enums.PaymentStatus;
import com.ravan.hotel_reservation.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }


    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByReservationId(@PathVariable Long reservationId) {
        return ResponseEntity.ok(paymentService.getPaymentByReservationId(reservationId));
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        return ResponseEntity.ok(paymentService.getPaymentByStatus(status));
    }


    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO dto) {
        PaymentResponseDTO created = paymentService.createPayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/method")
    public ResponseEntity<PaymentResponseDTO> updatePaymentMethod(@PathVariable Long id,
                                                                  @RequestParam String newMethod) {
        PaymentResponseDTO updated = paymentService.updatePaymentMethod(id, newMethod);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}

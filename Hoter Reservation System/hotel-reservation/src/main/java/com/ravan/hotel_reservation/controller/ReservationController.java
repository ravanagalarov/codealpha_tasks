package com.ravan.hotel_reservation.controller;

import com.ravan.hotel_reservation.DTOs.ReservationRequestDTO;
import com.ravan.hotel_reservation.DTOs.ReservationResponseDTO;
import com.ravan.hotel_reservation.serviceImpl.ReservationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAll() {
        List<ReservationResponseDTO> dtos = reservationService.getAllReservations();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationResponseDTO> getById(@PathVariable Long id) {
        ReservationResponseDTO dto = reservationService.getReservationById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ReservationResponseDTO> getByUserId(@PathVariable Long userId) {
        ReservationResponseDTO dto = reservationService.getReservationByUserId(userId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody @Valid ReservationRequestDTO dto) {
        ReservationResponseDTO responseDTO = reservationService.createReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ReservationRequestDTO dto) {
        ReservationResponseDTO responseDTO = reservationService.updateReservationDetails(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

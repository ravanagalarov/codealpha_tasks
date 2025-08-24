package com.ravan.hotel_reservation.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    @NotNull(message = "Reservation id must be provided")
    private Long reservationId;

    @NotBlank(message = "Can't be blank")
    private String paymentMethod;

    @NotNull(message = "Amount must be provided")
    private Double amount;
}

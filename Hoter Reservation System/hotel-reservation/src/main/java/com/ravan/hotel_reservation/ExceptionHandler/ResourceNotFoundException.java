package com.ravan.hotel_reservation.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Not found");
    }
}

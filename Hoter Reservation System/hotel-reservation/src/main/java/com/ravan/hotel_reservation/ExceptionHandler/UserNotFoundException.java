package com.ravan.hotel_reservation.ExceptionHandler;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}

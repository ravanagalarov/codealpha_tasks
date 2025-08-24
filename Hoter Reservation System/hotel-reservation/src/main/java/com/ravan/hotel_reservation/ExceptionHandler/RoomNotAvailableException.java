package com.ravan.hotel_reservation.ExceptionHandler;

public class RoomNotAvailableException extends RuntimeException {
    public RoomNotAvailableException() {
        super("Room is already booked for the selected dates");
    }
}

package com.ravan.hotel_reservation.mapper;

public interface GenericMapper<E, D> {
    E toEntity(D dto);
    D toDTO(E entity);
}

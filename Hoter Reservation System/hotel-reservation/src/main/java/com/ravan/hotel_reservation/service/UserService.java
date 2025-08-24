package com.ravan.hotel_reservation.service;

import com.ravan.hotel_reservation.DTOs.UserRequestDTO;
import com.ravan.hotel_reservation.DTOs.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO dto);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUserDetails(Long id, UserRequestDTO dto);
    void deleteUserById(Long id);
}

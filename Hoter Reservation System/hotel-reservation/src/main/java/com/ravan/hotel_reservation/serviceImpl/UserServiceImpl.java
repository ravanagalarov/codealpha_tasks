package com.ravan.hotel_reservation.serviceImpl;

import com.ravan.hotel_reservation.DTOs.UserRequestDTO;
import com.ravan.hotel_reservation.DTOs.UserResponseDTO;
import com.ravan.hotel_reservation.ExceptionHandler.ResourceNotFoundException;
import com.ravan.hotel_reservation.ExceptionHandler.UserNotFoundException;
import com.ravan.hotel_reservation.entity.User;
import com.ravan.hotel_reservation.repository.UserRepository;
import com.ravan.hotel_reservation.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = mapper.map(dto, User.class);
        User savedUser = userRepository.save(user);
        UserResponseDTO responseDTO = mapper.map(savedUser, UserResponseDTO.class);
        return responseDTO;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        List<UserResponseDTO> dtos = users.stream().
                map(user -> mapper.map(user, UserResponseDTO.class)).toList();

        return dtos;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(UserNotFoundException::new);

        return mapper.map(user, UserResponseDTO.class);

    }

    @Override
    @Transactional
    public UserResponseDTO updateUserDetails(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id).
                orElseThrow(UserNotFoundException::new);

        mapper.map(dto,user);
        User updated = userRepository.save(user);

        return mapper.map(updated, UserResponseDTO.class);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);
    }

}

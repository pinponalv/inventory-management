package com.pinpon.inventory.management.user.service;

import com.pinpon.inventory.management.user.dto.CreateUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UpdatedUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO createUser(CreateUserRequestDTO requestDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UpdatedUserRequestDTO requestDTO);
    void deleteUser(Long id);
    UserResponseDTO findByEmail(String email);
    UserResponseDTO findByName(String name);
    //Method for encripting
    String encriptPassword(String password);
}

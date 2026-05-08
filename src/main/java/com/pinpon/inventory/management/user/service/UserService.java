package com.pinpon.inventory.management.user.service;

import com.pinpon.inventory.management.user.dto.CreateUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UpdatedUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UserResponseDTO;
import com.pinpon.inventory.management.user.entity.User;
import com.pinpon.inventory.management.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        User user = new User();
        user.setName(requestDTO.getName());
        user.setLastName(requestDTO.getLastName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setRole(requestDTO.getRole());

        User savedUser = userRepository.save(user);
        return new  UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();

        for(User user : users){
            UserResponseDTO userResponseDTO = new  UserResponseDTO(
                    user.getId(),
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getRole()
            );
            userResponseDTOS.add(userResponseDTO);
        }
        return userResponseDTOS;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        return new   UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public UserResponseDTO updateUser(Long id, UpdatedUserRequestDTO requestDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));


        if(requestDTO.getName() != null){
            existingUser.setName(requestDTO.getName());
        }

        if(requestDTO.getLastName() != null){
            existingUser.setLastName(requestDTO.getLastName());
        }

        if(requestDTO.getEmail() != null){
            existingUser.setEmail(requestDTO.getEmail());
        }

        if(requestDTO.getPassword() != null){
            existingUser.setPassword(requestDTO.getPassword());
        }


        User savedUser = userRepository.save(existingUser);
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw  new RuntimeException("Email not found");
        }
        return new   UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public UserResponseDTO findByName(String name) {
        User user  = userRepository.findByName(name);
        if(user == null){
            throw  new RuntimeException("User not found");
        }
        return new    UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}

package com.pinpon.inventory.management.user.service;

import com.pinpon.inventory.management.role.dto.RoleResponseDTO;
import com.pinpon.inventory.management.role.entity.Role;
import com.pinpon.inventory.management.role.repository.IRoleRepository;
import com.pinpon.inventory.management.role.service.IRoleService;
import com.pinpon.inventory.management.user.dto.CreateUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UpdatedUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UserResponseDTO;
import com.pinpon.inventory.management.user.entity.UserSec;
import com.pinpon.inventory.management.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        Set<Role> roleList = new HashSet<>();
        Role readRole;

        //encript password
        requestDTO.setPassword(this.encriptPassword(requestDTO.getPassword()));

        //get permission
        for(Long roleId: requestDTO.getRoles()){
            readRole = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("not fount"));

            //readRole = roleService.findById(requestDTO.getRoles()).orElse(null);
            if(readRole != null){
                roleList.add(readRole);
            }
        }

        UserSec user = new UserSec();
        user.setName(requestDTO.getName());
        user.setLastName(requestDTO.getLastName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setRoles(roleList);

        UserSec savedUser = userRepository.save(user);
        return new  UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRoles()
        );
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UserSec> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();

        for(UserSec user : users){
            UserResponseDTO userResponseDTO = new  UserResponseDTO(
                    user.getId(),
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getRoles()
            );
            userResponseDTOS.add(userResponseDTO);
        }
        return userResponseDTOS;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserSec user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        return new   UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles()
        );
    }

    @Override
    public UserResponseDTO updateUser(Long id, UpdatedUserRequestDTO requestDTO) {
        UserSec existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));


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


        UserSec savedUser = userRepository.save(existingUser);
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRoles()
        );
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        UserSec user = userRepository.findByEmail(email);
        if(user == null){
            throw  new RuntimeException("Email not found");
        }
        return new   UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles()
        );
    }

    @Override
    public UserResponseDTO findByName(String name) {
        UserSec user  = userRepository.findByName(name);
        if(user == null){
            throw  new RuntimeException("User not found");
        }
        return new    UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles()
        );
    }
    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}

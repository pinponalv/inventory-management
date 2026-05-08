package com.pinpon.inventory.management.user.controller;

import com.pinpon.inventory.management.user.dto.CreateUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UpdatedUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UserResponseDTO;
import com.pinpon.inventory.management.user.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User API Operations")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO requestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOList = userService.getAllUsers();
        return ResponseEntity.ok(userResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponseDTO> getUserByName(@PathVariable String name) {
        UserResponseDTO user = userService.findByName(name);
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        UserResponseDTO userResponseDTO = userService.findByEmail(email);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UpdatedUserRequestDTO requestDTO) {
        UserResponseDTO userResponseDTO = userService.updateUser(id, requestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

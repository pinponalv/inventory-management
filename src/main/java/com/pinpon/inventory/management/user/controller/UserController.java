package com.pinpon.inventory.management.user.controller;

import com.pinpon.inventory.management.user.dto.CreateUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UpdatedUserRequestDTO;
import com.pinpon.inventory.management.user.dto.UserResponseDTO;
import com.pinpon.inventory.management.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO requestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @Operation(summary = "get all a users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOList = userService.getAllUsers();
        return ResponseEntity.ok(userResponseDTOList);
    }

    @Operation(summary = "get users by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@Parameter(description = "User ID", example = "1")
                                                           @PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @Operation(summary = "get user by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponseDTO> getUserByName(@Parameter(description = "User name", example = "oscar")
                                                             @PathVariable String name) {
        UserResponseDTO user = userService.findByName(name);
        return  ResponseEntity.ok(user);
    }

    @Operation(summary = "get user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@Parameter(description = "User email", example = "email@example.com")
                                                              @PathVariable String email) {
        UserResponseDTO userResponseDTO = userService.findByEmail(email);
        return ResponseEntity.ok(userResponseDTO);
    }

    @Operation(summary = "Update user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok request"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@Parameter(description = "User ID", example = "1")
                                                          @PathVariable Long id,
                                                        @Valid @RequestBody UpdatedUserRequestDTO requestDTO) {
        UserResponseDTO userResponseDTO = userService.updateUser(id, requestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@Parameter(description = "User ID", example = "1")
                                                          @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

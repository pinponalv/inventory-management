package com.pinpon.inventory.management.user.controller;

import com.pinpon.inventory.management.security.service.CustomUserDetailsService;
import com.pinpon.inventory.management.user.dto.AuthLoginRequestDTO;
import com.pinpon.inventory.management.user.dto.AuthResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO requestDTO) {
        return new ResponseEntity<>(customUserDetailsService.loginUser(requestDTO), HttpStatus.OK);
    }
}

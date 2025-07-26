package com.app.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.jwt.dto.AuthRequest;
import com.app.jwt.dto.AuthResponse;
import com.app.jwt.dto.UserDTO;
import com.app.jwt.service.AuthService;

@RestController
@RequestMapping("/auth/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO user) {
    	System.out.println("register--------");
        return ResponseEntity.ok(authService.register(user));
    }
}
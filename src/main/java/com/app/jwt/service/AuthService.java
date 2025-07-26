package com.app.jwt.service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.jwt.dto.AuthRequest;
import com.app.jwt.dto.AuthResponse;
import com.app.jwt.dto.UserDTO;
import com.app.jwt.entity.User;
import com.app.jwt.repository.UserRepository;
import com.app.jwt.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getMobileNumber(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getMobileNumber());
     // 🔍 Find the user and store the token
        Optional<User> optionalUser = userRepository.findByMobileNumber(request.getMobileNumber());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setToken(token);
            userRepository.save(user); // ✅ Save the updated token
        }
        return new AuthResponse(token);
    }

    public String register(UserDTO userDTO) {
        // Optional: check if mobile already exists
        Optional<User> existing = userRepository.findByMobileNumber(userDTO.getMobileNumber());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Mobile number already registered.");
        }

        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .mobileNumber(userDTO.getMobileNumber())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        userRepository.save(user);
        return "User registered successfully";
    }
}
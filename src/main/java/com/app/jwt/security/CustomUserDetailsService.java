package com.app.jwt.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.app.jwt.entity.User;
import com.app.jwt.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        User user = userRepository.findByMobileNumber(mobileNumber)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile number: " + mobileNumber));

        return new org.springframework.security.core.userdetails.User(
                user.getMobileNumber(), user.getPassword(), Collections.emptyList());
    }
}
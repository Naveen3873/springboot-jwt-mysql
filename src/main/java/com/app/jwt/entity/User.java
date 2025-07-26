package com.app.jwt.entity;

import lombok.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String name;
    private String token;
    private String code;
    private String role;
    
    @Column(unique = true, nullable = false)
    private String mobileNumber;
    
    @Column(unique = true)
    private String email;
    private LocalDateTime otpUpdatedAt;
    
    @Builder.Default
    @Column(nullable = false)
    private boolean isEmailVerified = false;
    
    private String password;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

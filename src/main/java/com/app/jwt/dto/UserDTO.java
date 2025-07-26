package com.app.jwt.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	private String id;
	private String name;
	private String code;
	private String role;
	private String mobileNumber;
	private String email;
	private LocalDateTime otpUpdatedAt;
	private String isEmailVerified;
	private String password;
}

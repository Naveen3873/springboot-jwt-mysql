package com.app.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByMobileNumber(String mobileNumber);
	
}

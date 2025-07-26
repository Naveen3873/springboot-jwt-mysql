package com.app.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.app.jwt.dto.UserDTO;
import com.app.jwt.entity.User;
import com.app.jwt.repository.UserRepository;
import com.app.jwt.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
        		.map(user -> UserDTO.builder()
        				.id(user.getId())
        				.name(user.getName())
        				.mobileNumber(user.getMobileNumber())
        				.build())
        		.collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(String id) {
    	Optional<User> user = userRepository.findById(id);
    	return user.map(u -> UserDTO.builder()
    			.id(u.getId())
    			.name(u.getName())
    			.build())
    			.orElse(null);
    }
    
    @Override
    public UserDTO createUser(UserDTO dto) {
        User saved = userRepository.save(User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .mobileNumber(dto.getMobileNumber())
                .build());

        return UserDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .mobileNumber(saved.getMobileNumber())
                .build();
    }
    
    @Override
    public UserDTO updateUser(String id, UserDTO dto) {
    	Optional<User> optionalUser = userRepository.findById(id);
    	if(optionalUser.isPresent()) {
    		User user = optionalUser.get();
    		user.setName(dto.getName());
    		User updated = userRepository.save(user);
    		return UserDTO.builder()
    				.id(updated.getId())
    				.name(updated.getName())
    				.mobileNumber(updated.getMobileNumber())
                    .role(updated.getRole())
    				.build();
    	}
    	return null;
    }
    
    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public Map<String, List<UserDTO>> getGroupByRole() {
    	List<User> users =  userRepository.findAll();
    	
    	List<UserDTO> userDTOs = users.stream()
    			.map(user -> UserDTO.builder()
    					.id(user.getId())
    					.name(user.getName())
    					.mobileNumber(user.getMobileNumber())
    					.build())
    			.collect(Collectors.toList());
    	
    	Map<String, List<UserDTO>> groupedByRole = userDTOs.stream()
    			.collect(Collectors.groupingBy(UserDTO::getRole));
    	
    	return groupedByRole;
    			
    }
}

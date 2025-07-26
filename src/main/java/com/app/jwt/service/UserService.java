package com.app.jwt.service;

import java.util.List;
import java.util.Map;

import com.app.jwt.dto.UserDTO;

public interface UserService {

	List<UserDTO> getAllUsers();
    UserDTO getUserById(String id);
    UserDTO createUser(UserDTO dto);
    UserDTO updateUser(String id, UserDTO dto);
    void deleteUser(String id);
    Map<String, List<UserDTO>> getGroupByRole();

}

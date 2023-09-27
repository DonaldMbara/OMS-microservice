package com.donmba.userservice.service;

import com.donmba.userservice.dto.UserRequest;
import com.donmba.userservice.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(UserRequest userRequest);

    Optional<UserResponse> findUserByEmail(String email);
    List<UserResponse> findUsersByRole(String role);

    List<UserResponse> findAllUser();

    void updateUser(UserRequest userRequest, int id);

}

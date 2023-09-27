package com.donmba.userservice.service;

import com.donmba.userservice.dto.UserRequest;
import com.donmba.userservice.dto.UserResponse;
import com.donmba.userservice.model.User;
import com.donmba.userservice.repository.UserRepository;
import com.donmba.userservice.utils.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequest userRequest) {

        User user = User.builder()

        .first_name(userRequest.getFirst_name())
        .last_name(userRequest.getLast_name())
        .email(userRequest.getEmail())
        .password(userRequest.getPassword())
        .cell_phone(userRequest.getCell_phone())
        .role(userRequest.getRole()).build();

        userRepository.save(user);
        log.info("User created successfully with id: " + user.getUser_id());

    }

    @Override
    public Optional<UserResponse> findUserByEmail(String email) {
        Optional<User> review = Optional.ofNullable(userRepository.findUserByEmail(email));

        return review.map(UserMapper::mapToStaffResponse);
    }

    @Override
    public List<UserResponse> findUsersByRole(String role) {
        List<User> users = userRepository.findUsersByRole(role);
        return users.stream()
                .map(UserMapper::mapToStaffResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> findAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapToStaffResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUser(UserRequest userRequest,int id) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User does not exist with id: "+ id));

        updateUser.setFirst_name(userRequest.getFirst_name());
        updateUser.setLast_name(userRequest.getLast_name());
        updateUser.setCell_phone(userRequest.getCell_phone());
        updateUser.setEmail(userRequest.getEmail());
        updateUser.setPassword(userRequest.getPassword());
        updateUser.setRole(userRequest.getRole());

        userRepository.save(updateUser);
        log.info("User {} is saved", updateUser.getUser_id());
    }
}

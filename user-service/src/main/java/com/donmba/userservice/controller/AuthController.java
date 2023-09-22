package com.donmba.userservice.controller;

import com.donmba.userservice.dto.UserRequest;
import com.donmba.userservice.dto.UserResponse;
import com.donmba.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody UserRequest userRequest) {
        Optional<UserResponse> existingUser = userService.findUserByEmail(userRequest.getEmail());

        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("There is already an account registered with the same email");
        }

        userService.createUser(userRequest);
        return ResponseEntity.ok("Registration successful");
    }

    @GetMapping("/role/{role}")
    public List<UserResponse> getUsersByRole(@PathVariable String role) {
        return userService.findUsersByRole(role);
    }

    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        return userService.findAllUser();
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable("email") String email) {
        Optional<UserResponse> userResponse = userService.findUserByEmail(email);

        return userResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserRequest userRequest) {
        userService.updateUser(userRequest, id);
    }
}

package com.donmba.staffservice.controller;

import com.donmba.staffservice.dto.StaffRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody StaffRequest staffRequest) {
        return null;
    }
}

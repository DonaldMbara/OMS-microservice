package com.donmba.staffservice.controller;

import com.donmba.staffservice.dto.StaffRequest;
import com.donmba.staffservice.model.Staff;
import com.donmba.staffservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegisterControl {

    private final StaffService staffService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> registerUser(@RequestBody StaffRequest staffRequest){
        try {
            Staff user = staffService.createStaff(staffRequest);
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

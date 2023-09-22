package com.donmba.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {

    private int user_id;
    private String first_name;
    private String last_name;
    private String cell_phone;
    private String email;
    private String password;
    private String role;
}

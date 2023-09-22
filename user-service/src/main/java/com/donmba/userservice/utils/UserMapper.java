package com.donmba.userservice.utils;

import com.donmba.userservice.dto.UserResponse;
import com.donmba.userservice.model.User;

public class UserMapper
{

    public static UserResponse mapToStaffResponse(User User) {
        return UserResponse.builder()
                .user_id(User.getUser_id())
                .first_name(User.getFirst_name())
                .last_name(User.getLast_name())
                .cell_phone(User.getCell_phone())
                .email(User.getEmail())
                .password(User.getCell_phone())
                .role(User.getRole())
                .build();
    }
}

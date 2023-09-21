package com.donmba.staffservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
public class StaffRequest {

    private String first_name;
    private String last_name;
    private String cell_phone;
    private String email;
    private String password;
    private String role;
}

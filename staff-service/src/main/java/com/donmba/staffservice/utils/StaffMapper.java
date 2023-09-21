package com.donmba.staffservice.utils;

import com.donmba.staffservice.dto.StaffRequest;
import com.donmba.staffservice.dto.StaffResponse;
import com.donmba.staffservice.model.Staff;

public class StaffMapper
{

    public static StaffResponse mapToStaffResponse(Staff Staff) {
        return StaffResponse.builder()
                .staff_id(Staff.getStaff_id())
                .first_name(Staff.getFirst_name())
                .last_name(Staff.getLast_name())
                .cell_phone(Staff.getCell_phone())
                .email(Staff.getEmail())
                .password(Staff.getCell_phone())
                .role(Staff.getRole())
                .build();
    }
}

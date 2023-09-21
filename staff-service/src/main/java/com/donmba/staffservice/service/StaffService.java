package com.donmba.staffservice.service;


import com.donmba.staffservice.dto.StaffRequest;
import com.donmba.staffservice.dto.StaffResponse;
import com.donmba.staffservice.model.Staff;
import com.donmba.staffservice.repository.StaffRepository;
import com.donmba.staffservice.utils.StaffMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffService {

    private final StaffRepository staffRepository;

    public Staff createStaff(StaffRequest staffRequest){
        Staff staff = Staff.builder()
                .first_name(staffRequest.getFirst_name())
                .last_name(staffRequest.getLast_name())
                .cell_phone(staffRequest.getCell_phone())
                .email(staffRequest.getEmail())
                .password(staffRequest.getPassword())
                .role(staffRequest.getRole())
                .build();

        return staffRepository.save(staff);
    }

    public List<StaffResponse> getAllStaffs() {
        List<Staff> staffs = staffRepository.findAll();

        return staffs.stream()
                .map(StaffMapper::mapToStaffResponse)
                .toList();
    }

    public List<StaffResponse> getStaffsByRole(String role) {
        List<Staff> staffs = staffRepository.findStaffsByRole(role);

        return staffs.stream()
                .map(StaffMapper::mapToStaffResponse)
                .toList();
    }

    public Optional<StaffResponse> getStaffById(int id){
        Optional<Staff> staff = Optional.ofNullable(staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(" User does not exist with id: " + id)));

        return staff.map(StaffMapper::mapToStaffResponse);
    }

    public Optional<StaffResponse> getStaffByEmail(String email){
        Optional<Staff> staff = Optional.ofNullable(staffRepository.findStaffByEmail(email));

        return staff.map(StaffMapper::mapToStaffResponse);
    }

    public void updateProduct(int id, Staff staff){

        Staff updateStaff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff does not exist with id: "+ id));

        updateStaff.setFirst_name(staff.getFirst_name());
        updateStaff.setLast_name(staff.getLast_name());
        updateStaff.setCell_phone(staff.getCell_phone());
        updateStaff.setEmail(staff.getEmail());
        updateStaff.setPassword(staff.getPassword());
        updateStaff.setRole(staff.getRole());

        staffRepository.save(updateStaff);
        log.info("Staff {} is saved", staff.getStaff_id());
    }

}

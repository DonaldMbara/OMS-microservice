package com.donmba.staffservice.repository;

import com.donmba.staffservice.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Integer> {

    @Query("SELECT s FROM Staff s WHERE s.email = :email")
    Staff findStaffByEmail(@Param("Email") String email);
    @Query("SELECT s FROM Staff s WHERE s.role = :role")
    List<Staff> findStaffsByRole(@Param("Role") String role);
}

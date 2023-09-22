package com.donmba.userservice.repository;

import com.donmba.userservice.dto.UserResponse;
import com.donmba.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT s FROM User s WHERE s.email = :email")
    User findUserByEmail(@Param("email") String email);
    @Query("SELECT s FROM User s WHERE s.role = :role")
    List<User> findUsersByRole(@Param("role") String role);
}

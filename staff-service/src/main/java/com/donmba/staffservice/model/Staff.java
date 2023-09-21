package com.donmba.staffservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Staff")
@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StaffId")
    private int staff_id;

    @Column(name = "FirstName")
    private String first_name;

    @Column(name = "LastName")
    private String last_name;

    @Column(name = "CellPhone")
    private String cell_phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Role")
    private String role;
}

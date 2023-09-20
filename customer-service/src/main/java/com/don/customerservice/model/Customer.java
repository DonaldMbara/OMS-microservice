package com.don.customerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "Customer")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "CustomerId")
    private int id;

    @Column(name = "CustomerArea")
    private String customer_area;

    @Column(name = "CustomerStreet")
    private String customer_street;

    @Column(name = "CustomerCity")
    private String customer_city;

    @Column(name = "CustomerPostalCode")
    private String customer_postal_code;

    @Column(name = "CustomerDOB")
    private Date customer_dob;

    @Column(name = "CustomerCellPhone")
    private String customer_cell_phone;

    @Column(name = "CustomerEmail")
    private String customer_email;

    @Column(name = "OrderId")
    private int order_id;

    @Column(name = "WalletBalance")
    private BigDecimal wallet_balance;

    @Column(name = "CustomerFirstName")
    private String customer_firt_name;

    @Column(name = "CustomerLastName")
    private String customer_last_name;



}

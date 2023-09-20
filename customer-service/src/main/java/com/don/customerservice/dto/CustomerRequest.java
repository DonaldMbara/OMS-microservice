package com.don.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerRequest {

    private String customer_area;
    private String customer_street;
    private String customer_city;
    private String customer_postal_code;
    private Date customer_dob;
    private String customer_cell_phone;
    private String customer_email;
    private int order_id;
    private BigDecimal wallet_balance;
    private String customer_first_name;
    private String customer_last_name;

}

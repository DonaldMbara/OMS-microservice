package com.donmba.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private int product_id;
    private byte[] thumbnail;
    private int quantity;
    private int customer_id;
    private Date order_date;
    private Date order_delivery;
    private String status;
}
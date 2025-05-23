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
public class OrderResponse {

    private int order_id;
    private String order_number;
    private int product_id;
    private byte[] thumbnail;
    private int quantity;
    private int customer_id;
    private Date order_date;
    private Date order_delivery;
    private String status;
}

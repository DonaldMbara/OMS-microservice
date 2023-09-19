package com.donmba.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private int order_id;
    @Column(name = "ProductId")
    private int product_id;
    @Column(name = "Thumbnail")
    private byte[] thumbnail;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "StaffId")
    private int staff_id;
    @Column(name = "OrderDate")
    private Date order_date;
    @Column(name = "OrderDelivery")
    private  Date order_delivery;
    @Column(name = "Status")
    private String status;
}

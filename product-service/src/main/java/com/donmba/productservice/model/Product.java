package com.donmba.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Thumbnail")
    private byte[] thumbnail;

    @Column(name = "CategoryID")
    private int category_id;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Details")
    private String details;

}

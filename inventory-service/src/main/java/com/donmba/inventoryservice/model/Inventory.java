package com.donmba.inventoryservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Inventory")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryId")
    private int id;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Location")
    private String location;

    @Column(name = "OrderId")
    private int order_id;
}

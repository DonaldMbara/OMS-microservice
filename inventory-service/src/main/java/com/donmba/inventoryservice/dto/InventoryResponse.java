package com.donmba.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class InventoryResponse {

    private int id;
    private int quantity;
    private String location;
    private int order_id;
}

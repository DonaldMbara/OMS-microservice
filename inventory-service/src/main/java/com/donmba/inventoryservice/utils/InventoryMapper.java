package com.donmba.inventoryservice.utils;

import com.donmba.inventoryservice.dto.InventoryResponse;
import com.donmba.inventoryservice.model.Inventory;

public class InventoryMapper {

    public static InventoryResponse mapToInventoryResponse(Inventory inventory){
        return InventoryResponse.builder()
                .id(inventory.getId())
                .quantity(inventory.getQuantity())
                .location(inventory.getLocation())
                .product_id(inventory.getProduct_id())
                .build();
    }
}

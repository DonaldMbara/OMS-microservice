package com.donmba.inventoryservice.controller;

import com.donmba.inventoryservice.dto.InventoryRequest;
import com.donmba.inventoryservice.dto.InventoryResponse;
import com.donmba.inventoryservice.model.Inventory;
import com.donmba.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/api/auth/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.createInventory(inventoryRequest);
    }

    @GetMapping("/api/inventory/product/productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InventoryResponse> getInventoryByProductId(@PathVariable("productId") int productId) {
        Optional<InventoryResponse> inventoryResponse = inventoryService.getInventoryByProductId(productId);

        return inventoryResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/api/inventory/check-stock/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> checkStockAvailability(@PathVariable("productId") int productId) {
        boolean isStockAvailable = inventoryService.checkStockAvailability(productId);

        if (isStockAvailable) {
            return ResponseEntity.ok(true); // Stock is available
        } else {
            return ResponseEntity.ok(false); // Stock is not available
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> getInventoryDetails(){return inventoryService.getInventoryList();}


    @PutMapping("/api/auth/inventory{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInventoryDetails(@PathVariable("productId") int productId, @RequestBody Inventory inventory) {
        inventoryService.updateInventoryDetails(productId,inventory);
    }
}

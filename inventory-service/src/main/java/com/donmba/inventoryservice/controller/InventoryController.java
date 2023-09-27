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
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.createInventory(inventoryRequest);
    }

    @GetMapping("/{InventoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InventoryResponse> getInventory(@PathVariable("InventoryId") int inventoryId) {
        Optional<InventoryResponse> inventoryResponse = inventoryService.getInventoryById(inventoryId);

        return inventoryResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/check-stock/{productId}")
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


    @PutMapping("/{InventoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInventoryDetails(@PathVariable("InventoryId") int inventoryId, @RequestBody Inventory inventory) {
        inventoryService.updateInventoryDetails(inventoryId,inventory);
    }
}

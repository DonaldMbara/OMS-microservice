package com.donmba.inventoryservice.service;

import com.donmba.inventoryservice.dto.InventoryRequest;
import com.donmba.inventoryservice.dto.InventoryResponse;
import com.donmba.inventoryservice.model.Inventory;
import com.donmba.inventoryservice.repository.InventoryRepository;
import com.donmba.inventoryservice.utils.InventoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void createInventory(InventoryRequest inventoryRequest){

        Inventory inventory = Inventory.builder()
                .quantity(inventoryRequest.getQuantity())
                .quantity(inventoryRequest.getQuantity())
                .order_id(inventoryRequest.getOrder_id())
                .build();

        inventoryRepository.save(inventory);
        log.info("Inventory  has been saved with id: "+ inventory.getId());
    }

    public Optional<InventoryResponse> getInventoryById(int id){
        Optional<Inventory> inventory = Optional.ofNullable(inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory does not exist with id: " + id)));

        return inventory.map(InventoryMapper::mapToInventoryResponse);
    }

    public Optional<InventoryResponse> getInventoryByOrderId(int id) {
        Optional<Inventory> inventory = Optional.ofNullable((Inventory) inventoryRepository.findInventoryByOrderId(id));

        return inventory.map(InventoryMapper::mapToInventoryResponse);
    }

    public List<InventoryResponse> getInventoryList(){
        List<Inventory> customers = inventoryRepository.findAll();

        return  customers.stream()
                .map(InventoryMapper::mapToInventoryResponse)
                .toList();
    }

    public void updateInventoryDetails(int id, Inventory inventory){
        Inventory updateInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory does not exist with id: "+ inventory.getId()));


        updateInventory.setId(inventory.getId());
        updateInventory.setQuantity(inventory.getQuantity());
        updateInventory.setLocation(inventory.getLocation());
        updateInventory.setOrder_id(inventory.getOrder_id());

        inventoryRepository.save(updateInventory);
        log.info("Inventory {} is saved", inventory.getId());
    }
}

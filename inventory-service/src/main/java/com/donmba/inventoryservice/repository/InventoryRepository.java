package com.donmba.inventoryservice.repository;

import com.donmba.inventoryservice.dto.InventoryResponse;
import com.donmba.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT r FROM Inventory r WHERE r.order_id = :id")
    List<Inventory> findInventoryByOrderId(@Param("OrderId") int id);
}

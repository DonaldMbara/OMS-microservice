package com.donmba.inventoryservice.repository;

import com.donmba.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Query("SELECT i FROM Inventory i WHERE i.product_id = :productId")
    Optional<Inventory> findByProductId(@Param("productId") int productId);

}

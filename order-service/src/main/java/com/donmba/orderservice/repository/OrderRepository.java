package com.donmba.orderservice.repository;

import com.donmba.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.customer_id = :customer_id")
    List<Order> findByCustomerId(@Param("customer_id") int customer_id);
}

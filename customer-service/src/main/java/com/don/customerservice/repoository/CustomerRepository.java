package com.don.customerservice.repoository;

import com.don.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("SELECT c FROM Customer c WHERE c.customer_email = :customer_email")
    Optional<Customer> findByEmail(@Param("customer_email") String customer_email);

}

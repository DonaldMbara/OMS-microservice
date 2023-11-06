package com.donmba.productservice.repository;

import com.donmba.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.category_id = :categoryId")
    List<Product> findByCategoryId(@Param("category_id") int category_id);

    @Query("SELECT p FROM Product p WHERE lower(p.name) LIKE lower(concat('%', :name, '%'))")
    List<Product> findByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}

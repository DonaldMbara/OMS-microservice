package com.donmba.reviewservice.repository;

import com.donmba.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.product_id = :id")
    List<Review> findReviewByProductId(@Param("id") int id);
    @Query("SELECT r FROM Review r WHERE r.customer_id = :id")
    List<Review> findReviewByCustomerId(@Param("id") int id);

}

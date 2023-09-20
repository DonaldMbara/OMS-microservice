package com.donmba.reviewservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Table(name = "Review")
@Data
@Entity
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ReviewId")
    private int review_id;

    @Column(name = "ProductId")
    private int product_id;

    @Column(name = "CustomerId")
    private int customer_id;

    @Column(name = "ReviewDescription")
    private String review_description;

    @Column(name = "ReviewStars")
    private int review_stars;
}

package com.donmba.reviewservice.controller;

import com.donmba.reviewservice.dto.ReviewRequest;
import com.donmba.reviewservice.dto.ReviewResponse;
import com.donmba.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createReview(@RequestBody ReviewRequest reviewRequest) {
        try {
            reviewService.createReview(reviewRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable("reviewId") int reviewId) {
        Optional<ReviewResponse> reviewResponse = reviewService.getReviewById(reviewId);
        return reviewResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponse>> getReviewByProductId(@PathVariable("productId") int productId) {
        List<ReviewResponse> reviews = reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/product/rating/{productId}")
    public ResponseEntity<Double> getAverageRatingByProductId(@PathVariable("productId") int productId) {
        List<ReviewResponse> reviews = reviewService.getReviewByProductId(productId);
        double averageRating = reviews.stream()
                .mapToInt(ReviewResponse::getReview_stars)
                .average()
                .orElse(0.0);
        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReviewResponse>> getReviewByCustomerId(@PathVariable("customerId") int customerId) {
        List<ReviewResponse> reviews = reviewService.getReviewByCustomerId(customerId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReview() {
        List<ReviewResponse> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewById(@PathVariable("reviewId") int reviewId) {
        reviewService.deleteReviewById(reviewId);
    }
}
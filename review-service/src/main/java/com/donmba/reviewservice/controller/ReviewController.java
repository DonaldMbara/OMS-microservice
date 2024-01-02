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
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/auth/review")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(reviewRequest);
    }

    @GetMapping("/api/review/{ReviewId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable("ReviewId") int reviewId) {
        Optional<ReviewResponse> reviewResponse = reviewService.getReviewById(reviewId);

        return reviewResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/review/product/{ProductId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviewByProductId(@PathVariable("ProductId") int productId) {
        return reviewService.getReviewByProductId(productId);
    }

    @GetMapping("/api/review/product/rating/{ProductId}")
    @ResponseStatus(HttpStatus.OK)
    public double getAverageRatingByProductId(@PathVariable("ProductId") int productId) {
        List<ReviewResponse> reviews = reviewService.getReviewByProductId(productId);

        double averageRating = reviews.stream()
                .mapToInt(ReviewResponse::getReview_stars)
                .average()
                .orElse(0.0);

        return averageRating;
    }

    @GetMapping("/api/review/customer/{CustomerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviewByCustomerId(@PathVariable("CustomerId") int customerId) {
        return reviewService.getReviewByCustomerId(customerId);
    }

    @GetMapping("/api/review")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getAllReview() {
        return reviewService.getAllReviews();
    }

    @DeleteMapping("/api/review/{ReviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewById(@PathVariable("ReviewId") int reviewId) {
        reviewService.deleteReviewById(reviewId);
    }

}

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

    @PostMapping("/api/review")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createReview(@RequestBody ReviewRequest reviewRequest) {
        try{
            reviewService.createReview(reviewRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/api/review/{ReviewId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable("ReviewId") int reviewId) {
        try {
            Optional<ReviewResponse> reviewResponse = reviewService.getReviewById(reviewId);
            return reviewResponse
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/api/review/product/{ProductId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ReviewResponse>> getReviewByProductId(@PathVariable("ProductId") int productId) {

        try{
            List<ReviewResponse> reviews =  reviewService.getReviewByProductId(productId);
            return ResponseEntity.ok(reviews);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/review/product/rating/{ProductId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> getAverageRatingByProductId(@PathVariable("ProductId") int productId) {

        try{
            List<ReviewResponse> reviews = reviewService.getReviewByProductId(productId);

            double averageRating = reviews.stream()
                    .mapToInt(ReviewResponse::getReview_stars)
                    .average()
                    .orElse(0.0);
            return ResponseEntity.ok(averageRating);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }


    }

    @GetMapping("/api/review/customer/{CustomerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ReviewResponse>> getReviewByCustomerId(@PathVariable("CustomerId") int customerId) {
        try{
            List<ReviewResponse> reviews = reviewService.getReviewByCustomerId(customerId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }

    }

    @GetMapping("/api/review")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ReviewResponse>> getAllReview() {

       try{
           List<ReviewResponse> reviews = reviewService.getAllReviews();
           return  ResponseEntity.ok(reviews);
       } catch (Exception e){
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

       }
    }

    @DeleteMapping("/api/review/{ReviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewById(@PathVariable("ReviewId") int reviewId) {
        reviewService.deleteReviewById(reviewId);
    }

}

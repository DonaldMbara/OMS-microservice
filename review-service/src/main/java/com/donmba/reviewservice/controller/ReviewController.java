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
    public void createReview(ReviewRequest reviewRequest){
        reviewService.createReview(reviewRequest);
    }

    @GetMapping("{ReviewId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable("ReviewId") String reviewId){
        Optional<ReviewResponse> reviewResponse = reviewService.getReviewById(reviewId);

        return reviewResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("product/{ProductId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReviewResponse> getReviewByProductId(@PathVariable("ProductId") String productId){
        Optional<ReviewResponse> reviewResponse = reviewService.getReviewByProductId(productId);

        return reviewResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getAllReview(){
        return reviewService.getAllReviews();
    }

    @DeleteMapping("{ReviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewById(@PathVariable("ReviewId") String reviewId){
        reviewService.deleteReviewById(reviewId);
    }


}

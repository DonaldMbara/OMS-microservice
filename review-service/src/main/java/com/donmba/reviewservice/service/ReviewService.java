package com.donmba.reviewservice.service;

import com.donmba.reviewservice.dto.ReviewRequest;
import com.donmba.reviewservice.dto.ReviewResponse;
import com.donmba.reviewservice.model.Review;
import com.donmba.reviewservice.repository.ReviewRepository;
import com.donmba.reviewservice.utils.ReviewMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void createReview(ReviewRequest reviewRequest) {
        Review review = Review.builder()
                .customer_id(reviewRequest.getCustomer_id())
                .product_id(reviewRequest.getProduct_id())
                .review_description(reviewRequest.getReview_description())
                .review_stars(reviewRequest.getReview_stars())
                .build();

        reviewRepository.save(review);
        log.info("Review {} is saved", review.getReview_id());
    }

    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream()
                .map(ReviewMapper::mapToReviewResponse)
                .toList();
    }

    public Optional<ReviewResponse> getReviewById(int id) {
        Optional<Review> review = Optional.ofNullable(reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review does not exist with id: " + id)));

        return review.map(ReviewMapper::mapToReviewResponse);
    }

    public List<ReviewResponse> getReviewByProductId(int id) {
        List<Review> reviews = reviewRepository.findReviewByProductId(id);

        return reviews.stream()
                .map(ReviewMapper::mapToReviewResponse)
                .toList();
    }

    public List<ReviewResponse> getReviewByCustomerId(int id) {
        List<Review> reviews = reviewRepository.findReviewByCustomerId(id);

        return reviews.stream()
                .map(ReviewMapper::mapToReviewResponse)
                .toList();
    }

    public void deleteReviewById(int id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review does not exist with id: " + id));

        reviewRepository.deleteById(id);
        log.info("Review {} is deleted id: ", review.getReview_id());

    }
}
package com.donmba.reviewservice.utils;

import com.donmba.reviewservice.dto.ReviewResponse;
import com.donmba.reviewservice.model.Review;

public class ReviewMapper {

    public static ReviewResponse mapToReviewResponse(Review review) {
        return ReviewResponse.builder()
                .review_id(review.getReview_id())
                .customer_id(review.getCustomer_id())
                .product_id(review.getProduct_id())
                .review_description(review.getReview_description())
                .review_stars(review.getReview_stars())
                .build();
    }
}
package com.gopang.review.service;

import com.gopang.review.domain.Review;
import com.gopang.review.dto.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    List<ReviewDto> findAll();

    List<ReviewDto> findAllWithUsersDetails();

    ReviewDto findById(final Integer reviewId);
    ReviewDto save(final ReviewDto reviewDto);
    ReviewDto update(final ReviewDto reviewDto);
    ReviewDto update(final Integer cartId, final ReviewDto reviewDto);
    void deleteById(final Integer reviewId);

    Review saveReview(Review review);

    Review getReviewById(Long reviewId);

    Review updateReview(Long reviewId, Review updatedReview);

    void deleteReview(Long reviewId);
}


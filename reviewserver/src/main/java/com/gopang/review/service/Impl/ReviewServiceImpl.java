package com.gopang.review.service.Impl;

import com.gopang.review.domain.Review;
import com.gopang.review.dto.ReviewDto;
import com.gopang.review.repository.ReviewRepository;
import com.gopang.review.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDto> findAll() {

        return null;
    }

    @Override
    public List<ReviewDto> findAllWithUsersDetails() {
        return null;
    }

    @Override
    public ReviewDto findById(Integer reviewId) {
        return null;
    }

    @Override
    public ReviewDto save(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public ReviewDto update(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public ReviewDto update(Integer cartId, ReviewDto reviewDto) {
        return null;
    }

    @Override
    public void deleteById(Integer reviewId) {

    }

    @Override
    public Review saveReview(Review review) {
        return null;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return null;
    }

    @Override
    public Review updateReview(Long reviewId, Review updatedReview) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {

    }
}

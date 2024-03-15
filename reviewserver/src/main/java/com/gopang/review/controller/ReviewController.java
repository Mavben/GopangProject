package com.gopang.review.controller;


import com.gopang.review.domain.Review;
import com.gopang.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 상품별 리뷰 조회
    @GetMapping("/item/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        // 특정 리뷰 조회
        Review review = reviewService.getReviewById(reviewId);
        // OK 상태 코드 응답
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // 마이 페이지 리뷰 작성
    @PostMapping("/mypage/orderlist/reviewpost")
    public ResponseEntity<Review> saveReview(@RequestBody Review review) {
        // 작성한 리뷰 저장
        Review savedReview = reviewService.saveReview(review);
        // CREATED 상태 코드 응답
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    // 수정
    @PutMapping("/mypage/review/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review updatedReview) {
        // 리뷰 수정
        Review review = reviewService.updateReview(reviewId, updatedReview);
        // OK 상태 코드 응답
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/mypage/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        // 리뷰 삭제
        reviewService.deleteReview(reviewId);
        // NO_CONTENT 상태 코드 응답
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}



package com.gopang.review.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Review.class)
@Table(name = "reviews")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;      // 리뷰번호

    @Column(name = "title")
    private String title;       // 제목

    @Column(name = "content")
    private String content;     // 내용

    @Column(name = "star")
    private int star;           // 별점

    @Column(name = "liked")
    private Integer liked;          // 좋아요

    @Id
    @Column(name = "item_id")
    private Long itemId;            // 상품ID

    @Id
    @Column(name = "member_id")
    private String memberId;        // 회원번호

    @Column(name = "member_email")
    private String memberEmail;        // 이메일

//    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<ReviewImage> images = new ArrayList<>();

    public static Object builder() {
        return null;
    }

    public Long getUserId() {
        return null;
    }

    public void setReviewId(Long reviewId) {
    }

    public String getTitle() {
        return null;
    }

    public String getContent() {
        return null;
    }

    public int getStar() {
        return 0;
    }

    public int getLiked() {
        return 0;
    }

    public void setUserId(String userId) {
    }

    public void setTitle(String title) {
    }

    public void setContent(String content) {
    }

    public void setStar(int star) {
    }

    public void setLiked(int liked) {
    }

    public void setCreatedDate(Date createdDate) {
    }

    public void setModifiedDate(Date modifiedDate) {
    }

    @Getter
    public enum Star {
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

        private int value;

        Star(int review) {
            this.value = value;
        }

    }

}

package com.gopang.review.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long reviewId;
    private String title;
    private String content;
    private int star;
    private int liked;
    private Date createdDate;
    private Date modifiedDate;

    public ReviewDto(Long reviewId, String title, String content, int star, int liked, Date createdDate, Date modifiedDate, List<ReviewImageDto> reviewImages) {
        this.reviewId = reviewId;
        this.title = title;
        this.content = content;
        this.star = star;
        this.liked = liked;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

    }

    public void setUserDto(UserDto userDto) {
    }

    public void setUserId(Long userId) {
    }

    public String getUserId() {
        return null;
    }
}

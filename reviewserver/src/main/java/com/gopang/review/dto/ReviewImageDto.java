package com.gopang.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ReviewImageDto {

    private Long reviewImageFileId;
    private String imageType;
    private String originalImageName;
    private String saveImageName;
    private long imageSize;
    private String imageKey;
    private String imagePath;
    private Long reviewId;

    public ReviewImageDto(Long reviewImageFileId, String imageType, String originalImageName, String saveImageName, long imageSize, String imageKey, String imagePath, Long reviewId) {
        this.reviewImageFileId = reviewImageFileId;
        this.imageType = imageType;
        this.originalImageName = originalImageName;
        this.saveImageName = saveImageName;
        this.imageSize = imageSize;
        this.imageKey = imageKey;
        this.imagePath = imagePath;
        this.reviewId = reviewId;

    }
}
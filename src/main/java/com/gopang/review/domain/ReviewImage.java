package com.gopang.review.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ReviewImage.class)
@Embeddable
@Table(name = "review_image")
public class ReviewImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_image_file_id")
    private Long reviewImageFileId;      // 이미지파일ID

    @Column(name = "image_type")
    private Long imageType;             // 이미지파일용도

    @Column(name = "original_image_name")
    private String originalImageName;   // 이미지 파일 원본 이름

    @Column(name = "save_image_name")
    private String saveImageName;       // 이미지 파일 저장 이름

    @Column(name = "image_size")
    private Long imageSize;             // 이미지 파일 크기

    @Column(name = "image_key")
    private String imageKey;            // S3에서 관리할 key

    @Column(name = "image_path")
    private String imagePath;           // S3에 저장된 경로

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;
}
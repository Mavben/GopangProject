package com.gopang.review.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileUploadController {

    // Amazon S3 클라이언트 주입
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 파일 업로드 처리
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("파일 업로드 시작");

        try {
            // 업로드된 파일의 원본 이름을 가져옴
            String fileName = file.getOriginalFilename();
            // S3에 저장할 파일 URL 생성
            String fileUrl = "https://" + bucket + "/test" +fileName;

            // 파일 메타데이터 생성 및 설정
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            // S3에 파일 업로드
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
            // 업로드된 파일의 S3 URL 응답
            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
            // 파일 업로드 중 오류 발생 시 500 서버 에러 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 파일 다운로드 처리
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadImage(@RequestParam("findFileName") String findFileName) throws IOException{
        // S3에서 파일 객체를 가져온다
        S3Object o = amazonS3Client.getObject(new GetObjectRequest(bucket, findFileName));

        // S3 object 객체를 stream으로 변환한다
        S3ObjectInputStream objectInputStream = o.getObjectContent();

        // java byte 배열로 변환한다
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        // 파일 이름을 URLEncoder를 사용하여 UTF-8로 인코딩하고, 공백을 %20으로 치환한다
        String fileName = URLEncoder.encode(findFileName, "UTF-8").replaceAll("\\+", "%20");

        // S3 객체의 메타데이터에서 파일의 Content-Type을 가져온다
        String fileType = o.getObjectMetadata().getContentType();

        // header 응답에 파일과 관련된 정보 설정하여 반환
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(fileType))
                .body(new ByteArrayResource(bytes));

    }
}
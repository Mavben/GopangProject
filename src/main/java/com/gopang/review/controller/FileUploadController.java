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

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("파일 업로드 시작");

        try {
            String fileName = file.getOriginalFilename();
            String fileUrl = "https://" + bucket + "/test" +fileName;

            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadImage(@RequestParam("findFileName") String findFileName) throws IOException{
        // bucket 이름과 파일 이름을 매칭해서 object를 가지고 온다. 여기서 말하는 object는 S3에서 정의한 object이다.
        S3Object o = amazonS3Client.getObject(new GetObjectRequest(bucket, findFileName));

        // object 객체를 stream으로 변환한다
        S3ObjectInputStream objectInputStream = o.getObjectContent();

        // java stream으로 변환한다
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        // 파일 이름을 URLEncoder를 사용하여 UTF-8로 인코딩하고, 공백을 %20으로 치환한다
        String fileName = URLEncoder.encode(findFileName, "UTF-8").replaceAll("\\+", "%20");

        // S3 객체의 메타데이터에서 파일의 Content-Type을 가져온다
        String fileType = o.getObjectMetadata().getContentType();

        // header에 대한 정보를 어떻게 할지 고민해보아야 한다
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(fileType))
                .body(new ByteArrayResource(bytes));

    }
}
package com.swkim.myboard.service.implement;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.swkim.myboard.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class S3ServiceImpl implements S3Service {

    private AmazonS3Client amazonS3Client;
    @Value("${s3.bucket}")
    private String s3Bucket;

    @Override
    public List<String> upload(String folder, MultipartFile[] multipartFileList){
        List<String> imagePathList = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFileList) {
            String originalName = folder + "/" + multipartFile.getOriginalFilename(); // 파일 이름
            long size = multipartFile.getSize(); // 파일 크기

            ObjectMetadata objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(multipartFile.getContentType());
            objectMetaData.setContentLength(size);

            // S3에 업로드
            try {
                amazonS3Client.putObject(
                        new PutObjectRequest(s3Bucket, originalName, multipartFile.getInputStream(), objectMetaData)
                                .withCannedAcl(CannedAccessControlList.PublicRead)
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String imagePath = amazonS3Client.getUrl(s3Bucket, originalName).toString(); // 접근 가능한 URL 가져오기
            imagePathList.add(imagePath);
        }

        return imagePathList;
    }

    @Override
    public ResponseEntity<byte[]> download(String folder, String fileKey) {
        var s3Object = amazonS3Client.getObject(new GetObjectRequest(s3Bucket, folder + "/" + fileKey));
        var objectInputStream = s3Object.getObjectContent();
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(objectInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fileName = null;
        try {
            fileName = URLEncoder.encode(fileKey, StandardCharsets.UTF_8.name()).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
}

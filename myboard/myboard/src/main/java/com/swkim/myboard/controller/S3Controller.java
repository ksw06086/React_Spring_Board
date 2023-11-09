package com.swkim.myboard.controller;

import com.swkim.myboard.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    /** S3 업로드 API **/
    @PostMapping("/upload")
    public ResponseEntity<Void> upload(
            @RequestParam("folder") String folder,
            @RequestParam("fileList") MultipartFile[] fileList) {
        List<String> uploadedFileUri = s3Service.upload(folder, fileList);
        String filePath = folder + "/" + uploadedFileUri.get(0).split("/")[uploadedFileUri.get(0).split("/").length - 1];
        return ResponseEntity.created(URI.create(filePath)).build();
    }

    /** S3 다운로드 API **/
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(
            @RequestParam("folder") String folder,
            @RequestParam("fileKey") String fileKey) {
        return s3Service.download(folder, fileKey);
    }
}

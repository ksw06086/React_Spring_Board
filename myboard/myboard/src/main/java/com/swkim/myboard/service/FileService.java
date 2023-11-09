package com.swkim.myboard.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    // 파일을 받으면 업로드 후 URL을 보내줌
    String upload(MultipartFile file);
    Resource getImage(String fileName);

    ResponseEntity<byte[]> download(String fileName);
}

package com.swkim.myboard.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface S3Service {

    /** S3 파일 업로드
     - (git issues #27 참고) **/
    List<String> upload(String folder, MultipartFile[] multipartFileList);

    /** S3 파일 다운로드 **/
    ResponseEntity<byte[]> download(String folder, String fileKey);
}

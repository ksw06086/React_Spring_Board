package com.swkim.myboard.service.implement;

import com.swkim.myboard.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${file.path}")
    private String filePath;
    @Value("${file.url}")
    private String fileURL;

    @Override
    public String upload(MultipartFile file) {
        if(file.isEmpty()) return null;

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;

        try {
            file.transferTo(new File(savePath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String url = fileURL + saveFileName;
        return url;
    }

    @Override
    public Resource getImage(String fileName) {
        Resource resource = null;

        try {
            resource = new UrlResource("file:" + filePath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return resource;
    }

    @Override
    public ResponseEntity<byte[]> download(String fileName) {
        Resource resource = null;
        byte[] bytes = null;
        HttpHeaders httpHeaders = null;

        try {
            resource = new UrlResource("file:" + filePath + fileName);
            var file = resource.getFile();
            bytes = Files.readAllBytes(Paths.get(file.toURI()));

            // 파일 이름을 URL 인코딩합니다.
            fileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.name()).replace("+", "%20");

            // HTTP 헤더를 설정합니다.
            httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentLength(bytes.length);
            httpHeaders.setContentDispositionFormData("attachment", fileName);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // ResponseEntity 객체를 생성하고 반환합니다.
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

}

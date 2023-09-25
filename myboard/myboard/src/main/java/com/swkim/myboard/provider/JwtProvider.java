package com.swkim.myboard.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    // JWT 생성 및 검증을 위한 키
    @Value("${secret-key}")
    private String SECURITY_KEY ; // 이걸로 암호화/복호화

    // JWT 생성하는 메서드b
    public String create (String email) {
        // 만료날짜를 현재 날짜 + 1시간으로 설정
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        // JWT를 생성
        String jwt = Jwts.builder()
                // 암호화에 사용될 알고리즘, 키
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                // JWT 제목, 생성일, 만료일
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
                // 생성
                .compact();

        return jwt;
    }

    // JWT 검증
    public String validate (String token) {
        // 매개변수 받은 token을 키를 사용해서 복호화 (디코딩)
        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(SECURITY_KEY)
                    .parseClaimsJws(token).getBody();
        } catch (Exception exception){
            exception.printStackTrace();
            return null;
        }

        // 복호화된 토큰의 payload에서 제목을 가져옴
        return claims.getSubject();
    }
}

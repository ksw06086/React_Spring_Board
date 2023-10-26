package com.swkim.myboard.service.implement;

import com.swkim.myboard.dto.request.auth.SignUpRequestDto;
import com.swkim.myboard.dto.response.ResponseDto;
import com.swkim.myboard.dto.response.auth.SignUpResponseDto;
import com.swkim.myboard.entity.UserEntity;
import com.swkim.myboard.repository.UserRepository;
import com.swkim.myboard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor    // 필수 키워드를 생성할 때 넣어주는 것(넣어주는 이유는 이 외에 방법<set이나 @Autowired>은 빈 값일 수 있어서이다.)
public class AuthServicieImpl implements AuthService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {

            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();
            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if(existedNickname) return SignUpResponseDto.duplicateNickname();
            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if(existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto = dto.toBuilder()
                .password(encodedPassword)
                .build();

            UserEntity userEntity = new UserEntity();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }
}

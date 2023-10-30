package com.swkim.myboard.service.implement;

import com.swkim.myboard.dto.request.auth.SignInRequestDto;
import com.swkim.myboard.dto.request.auth.SignUpRequestDto;
import com.swkim.myboard.dto.response.ResponseDto;
import com.swkim.myboard.dto.response.auth.SignInResponseDto;
import com.swkim.myboard.dto.response.auth.SignUpResponseDto;
import com.swkim.myboard.entity.UserEntity;
import com.swkim.myboard.provider.JwtProvider;
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
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {

            boolean existedEmail = userRepository.existsByEmail(dto.getEmail());
            if(existedEmail) return SignUpResponseDto.duplicateEmail();
            boolean existedNickname = userRepository.existsByNickname(dto.getNickname());
            if(existedNickname) return SignUpResponseDto.duplicateNickname();
            boolean existedTelNumber = userRepository.existsByTelNumber(dto.getTelNumber());
            if(existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            UserEntity userEntity = dto.memberEntity(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(userEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null;

        try {
            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return SignInResponseDto.signInFailed();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFailed();

            token = jwtProvider.create(email);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
}

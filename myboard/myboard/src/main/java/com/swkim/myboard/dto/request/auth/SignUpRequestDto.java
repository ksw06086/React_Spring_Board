package com.swkim.myboard.dto.request.auth;

import com.swkim.myboard.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "이메일은 필수입니다.") @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.") @Size(min = 8, max = 20)
    private String password;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotBlank(message = "전화번호는 필수입니다.") @Pattern(regexp = "^[0-9]{11,13}$")
    private String telNumber;

    @NotBlank(message = "주소는 필수입니다.")
    private String address;

    private String addressDetail;

    @NotNull(message = "동의는 필수입니다.") @AssertTrue // True인 값만 받게 해줌
    private Boolean agreedPersonal;

    public UserEntity memberEntity(String encodePassword) {
        return UserEntity.builder()
                .email(this.email)
                .password(encodePassword)
                .nickname(this.nickname)
                .telNumber(this.telNumber)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .agreedPersonal(this.agreedPersonal)
                .build();
    }
}

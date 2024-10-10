package com.swkim.myboard.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private String nickname;
    private String telNumber;
    private String address;
    private String addressDetail;
    private String profileImage;
    private boolean agreedPersonal;

    @Builder
    private UserEntity(String email, String password, String nickname, String telNumber, String address, String addressDetail, Boolean agreedPersonal) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.telNumber = telNumber;
        this.address = address;
        this.addressDetail = addressDetail;
        this.agreedPersonal = agreedPersonal;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}

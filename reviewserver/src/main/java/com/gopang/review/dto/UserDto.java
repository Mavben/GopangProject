package com.gopang.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class UserDto {

    private Long userId;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPhoneNumber;
    private String userZipCode;
    private String userAddress;
    private String userDetailAddress;

    public UserDto(Long userId, String userEmail, String userPassword, String userName, String userPhoneNumber, String userZipCode, String userAddress, String userDetailAddress) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userZipCode = userZipCode;
        this.userAddress = userAddress;
        this.userDetailAddress = userDetailAddress;
    }

    public void setId(Long userId) {
    }

    public void setUsername(String johnDoe) {
    }
}

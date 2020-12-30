package com.example.mybatis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserSaveRequestDto {
    
    private Long userId;
    private String userName;
    private String userPhoneNumber;
    
    @Builder
    public UserSaveRequestDto(String userName, String userPhoneNumber) {
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }

}

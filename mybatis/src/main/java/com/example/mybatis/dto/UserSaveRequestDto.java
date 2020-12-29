package com.example.mybatis.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSaveRequestDto {
    
    private Long id;
    private String name;
    private String phoneNumber;
    
    @Builder
    public UserSaveRequestDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

}

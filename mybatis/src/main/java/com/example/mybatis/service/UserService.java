package com.example.mybatis.service;

import com.example.mybatis.dto.UserResponseDto;
import com.example.mybatis.dto.UserSaveRequestDto;

public interface UserService {
    
    public Long postUser(UserSaveRequestDto userSaveRequestDto);
    public UserResponseDto getUser(Long userId);

}

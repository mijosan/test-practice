package com.example.mybatis.service;

import com.example.mybatis.dto.UserResponseDto;
import com.example.mybatis.dto.UserSaveRequestDto;

public interface UserService {
    
    public Long insertUser(UserSaveRequestDto userSaveRequestDto);
    public UserResponseDto selectUser(Long userId);

}

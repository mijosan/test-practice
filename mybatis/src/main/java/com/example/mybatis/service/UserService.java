package com.example.mybatis.service;

import com.example.mybatis.dto.UserResponseDto;
import com.example.mybatis.dto.UserSaveRequestDto;
import com.example.mybatis.mapper.UserMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserMapper userMapper;

    @Transactional
    public Long insertUser(UserSaveRequestDto userSaveRequestDto) {
        userMapper.insertUser(userSaveRequestDto);
        
        UserResponseDto userResponseDto = userMapper.getUser();

        return userResponseDto.getId();
    }

}

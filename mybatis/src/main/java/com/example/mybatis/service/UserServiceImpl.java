package com.example.mybatis.service;

import com.example.mybatis.dto.UserResponseDto;
import com.example.mybatis.dto.UserSaveRequestDto;
import com.example.mybatis.mapper.UserMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;

    @Override
    public UserResponseDto getUser(Long userId) {
        UserResponseDto userResponseDto = userMapper.selectUser(userId);

        return userResponseDto;
    }

    @Transactional
    @Override
    public Long postUser(UserSaveRequestDto userSaveRequestDto) {
        userMapper.insertUser(userSaveRequestDto);

        Long userId = userMapper.selectMaxUserId();

        return userId;
    }

}

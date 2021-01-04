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

    @Transactional
    @Override
    public Long insertUser(UserSaveRequestDto userSaveRequestDto) {
        Long user_id = userMapper.insertUser(userSaveRequestDto);

        return user_id;
    }

    @Override
    public UserResponseDto selectUser(Long userId) {
        UserResponseDto userResponseDto = userMapper.selectUser(userId);

        return userResponseDto;
    }

}

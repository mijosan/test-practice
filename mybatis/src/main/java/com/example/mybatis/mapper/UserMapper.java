package com.example.mybatis.mapper;

import java.util.List;

import com.example.mybatis.dto.UserResponseDto;
import com.example.mybatis.dto.UserSaveRequestDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    
    public List<UserResponseDto> selectUserList();
    public Long insertUser(UserSaveRequestDto userSaveRequestDto);
    public UserResponseDto selectUser(Long userId);
    
}

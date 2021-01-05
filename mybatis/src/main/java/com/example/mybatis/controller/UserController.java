package com.example.mybatis.controller;

import com.example.mybatis.common.CommonUtil;
import com.example.mybatis.dto.ResponseDto;
import com.example.mybatis.dto.UserResponseDto;
import com.example.mybatis.dto.UserSaveRequestDto;
import com.example.mybatis.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {
    
    private final UserService userService;

    @PostMapping(value="/v1/user")
    public ResponseEntity<ResponseDto> postUser(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        Long userId = userService.insertUser(userSaveRequestDto);

        return CommonUtil.getResponseEntity(UserResponseDto.builder()
                                                                .userId(userId)
                                                                .build()
                                                                , HttpStatus.OK
                                                                , "회원 등록 완료");
    }

}
